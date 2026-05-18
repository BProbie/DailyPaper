#!/bin/bash

cd ..

mvnw surefire-report:report

read -p "..."