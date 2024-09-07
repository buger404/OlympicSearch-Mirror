# 代码规范

### 1. 缩进
- **使用4个空格** 作为缩进，使用 Tab 键缩进，空格数量可以在编辑器中设置。
- **花括号换行**：`{` 和 `}` 必须独立成行。
```java
public class PlayerController 
{
    public void moveForward() 
    {
        if (condition) 
        {
            // code
        }
    }
}
```

### 2. 变量命名
- 变量名采用**小驼峰命名法**，即第一个单词小写，后续单词首字母大写。
- 变量名应清晰表达用途，避免缩写。
```java
int equipmentLevel;
String monsterName;
```

### 3. 每行最多字符数
- **每行最多80个字符**。超过时需要换行，并且换行后的代码需要缩进一个层次。

### 4. 函数最大行数
- 每个函数不超过**50行**，如果超过，应考虑拆分成更小的函数。

### 5. 函数、类命名
- **函数名**使用小驼峰命名法。
- **类名**使用大驼峰命名法，即每个单词首字母大写。
```java
public class SkillManager 
{
    public void addSkill() 
    {
        // code
    }
}
```

### 6. 常量
- 常量名使用**大写蛇形命名法**。
- 常量应该使用`final`关键字并且是`static`。
```java
public static final int HP_DROP_RATE = 10;
```

### 7. 空行规则
- 在**方法之间**、**逻辑块之间**（如 if、for、while）使用空行来提高代码可读性。

### 8. 注释规则
- 使用`//`注释单行，使用`/* */`注释多行。
- 方法和类应使用`/** */`编写Javadoc注释，说明功能、参数和返回值。
```java
/**
 * 生成怪物
 * @param monsterID 怪物的ID
 */
public void createMonster(String monsterID) 
{
    // 生成怪物
}
```

### 9. 操作符前后空格
- **操作符前后**必须有一个空格，如`=`, `+`, `-`, `*`, `/`, `==`, `!=`, `&&`, `||`等。
```java
int result = a + b;
if (a == b) 
{
    // code
}
```

### 10. 其他规则
- **文件命名与类名一致**，一个文件只包含一个public类。
- 每个类应只负责一个逻辑单元，遵循**单一职责原则**。
