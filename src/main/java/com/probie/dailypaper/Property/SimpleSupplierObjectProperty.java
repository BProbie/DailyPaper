package com.probie.dailypaper.Property;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.BiConsumer;

public class SimpleSupplierObjectProperty<T> {

    private final Supplier<T> supplier;
    private final Consumer<T> consumer;

    private T lastValue;
    private boolean initialized = false;
    private final List<BiConsumer<T, T>> listeners = new ArrayList<>();

    public SimpleSupplierObjectProperty(Supplier<T> supplier) {
        this(supplier, null);
    }

    public SimpleSupplierObjectProperty(Supplier<T> supplier, Consumer<T> consumer) {
        this.supplier = Objects.requireNonNull(supplier);
        this.consumer = consumer;
    }

    public void set(T newValue) {
        if (consumer == null) {
            throw new UnsupportedOperationException("No consumer provided for write operation");
        }

        consumer.accept(newValue);

        T oldValue = this.lastValue;
        this.lastValue = newValue;

        if (!initialized) {
            initialized = true;
            return;
        }

        if (!Objects.equals(oldValue, newValue)) {
            listeners.forEach(listener -> listener.accept(oldValue, newValue));
        }
    }

    public T get() {
        T currentValue = supplier.get();
        checkAndNotify(currentValue);
        return currentValue;
    }

    public void checkForUpdates() {
        T currentValue = supplier.get();
        checkAndNotify(currentValue);
    }

    private void checkAndNotify(T currentValue) {
        if (!initialized) {
            this.lastValue = currentValue;
            this.initialized = true;
            return;
        }

        if (!Objects.equals(this.lastValue, currentValue)) {
            T oldVal = this.lastValue;
            this.lastValue = currentValue;
            listeners.forEach(listener -> listener.accept(oldVal, currentValue));
        }
    }

    public void addListener(BiConsumer<T, T> listener) {
        listeners.add(listener);
    }

    public void removeListener(BiConsumer<T, T> listener) {
        listeners.remove(listener);
    }

}