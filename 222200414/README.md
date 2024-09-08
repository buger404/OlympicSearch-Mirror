# OlympicSearch

用于检索 2024 巴黎奥运会 奖牌信息的控制台工具。

作业链接：[软件工程实践第二次作业——个人实战-CSDN社区](https://bbs.csdn.net/topics/619295096)

博客链接：[软件工程实践第二次作业——个人实战-CSDN社区](https://bbs.csdn.net/topics/619302016)

# 使用方法

```
java -jar OlympicSearch.jar <input_file_path> <output_file_path>
```

其中`input_file_path`指向输入文件的路径，输入文件应当包括一个或多个指令。指令之间使用'\n'分隔。

# 指令指南

### 查询已排序的所有国家的奖牌信息

```
total
```

### 查询指定日期的所有运动员的获奖信息

```
result <MMdd>
```

其中，`MM`表示月（两位数），`dd`表示日（两位数）

当指定的日期输入错误、或不在活动范围内时，程序将输出`N/A`

### 对比两个国家的指定项目的奖牌信息

```
PK <国家1> <国家2> <项目名>
```

当国家或项目不存在时，程序将在控制台提示，并输出`Error`
