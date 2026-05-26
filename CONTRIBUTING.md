# 贡献指南



## ⭐前置条件

Java版本：JDK-21.0.8（**强制要求**）



## ⭐快速开始

### ① 下载项目

#### Github

```shell
Fork
```

#### Git（推荐）

```shell
git clone https://github.com/BProbie/DailyPaper.git
```



### ② 导入项目

#### IDEA

```shell
IDEA
```



## ⭐开发规范

### 代码风格

1. 严格遵循 **Google Java Style Guide** 规范
2. 类名使用 **UpperCamelCase** 方式，方法名和变量名使用 **驼峰命名** 方式，常量 **全部大写** 方式
3. 所有公共方法**必须写接口**，尽量在**接口完成方法默认逻辑**，保持**方法简短**，所有**接口必须添加 JavaDocs 注释**
4. 使用 **空格** 缩进，禁用 Tab 缩进

### Commit规范

采用 **Angular** 提交规范

```shell
<type>(<scope>): <subject>
<body>
```

Type类型：

- feat：新增
- fix：修复
- docs：文档
- style：风格
- refactor：重构
- test：测试
- chore：工具



## ⭐提交 PR 流程

### 创建分支

从 `master` 分支创建功能分支

```shell
git checkout -b feat/...
```

### 开发并测试

确保所有现有测试通过，新增功能请添加**单元测试**

### 提交代码

遵循上述 Commit 规范

### 推送分支

```shell
git push origin feat/...
```

### 创建PR

在 Github 上提交 **Pull Request**



## ⭐疑问交流联系

如有疑问请通过提交**Issue**阐述，作者能看到且会经常查看！