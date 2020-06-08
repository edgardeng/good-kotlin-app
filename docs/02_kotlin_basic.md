## Basic Kotlin Language

### Variable 变量

```kotlin
var a: String = "initial"  // Declares a mutable variable and initializing it
val b: Int = 1             // Declares an immutable variable and initializing it
val c = 3  
var e: Int  
println(e)                // 不可变量在使用前需要初始化 compiler error:  it must be initialized before the first read. 

```

### Function 函数

默认参数，命名参数 Default Parameter Values and Named Arguments


```kotlin
fun printMessage(message: String): Unit {                               // 1
    println(message)
}

fun printMessageWithPrefix(message: String, prefix: String = "Info") {  // 2
    println("[$prefix] $message")
}

fun sum(x: Int, y: Int): Int {                                          // 3
    return x + y
}

fun multiply(x: Int, y: Int) = x * y     

```

Infix Functions 中缀函数

```kotlin
  infix fun Int.times(str: String) = str.repeat(this)        // 1
  println(2 times "Bye ")                                    // 2

  val pair = "Ferrari" to "Katrina"                          // 3
  println(pair)

  infix fun String.onto(other: String) = Pair(this, other)   // 4
  val myPair = "McLaren" onto "Lucas"
  println(myPair)

    class Person(val name: String) {
      val likedPeople = mutableListOf<Person>()
      infix fun likes(other: Person) { likedPeople.add(other) }  // 6
    }

  val sophia = Person("Sophia")
  val claudia = Person("Claudia")
  sophia likes claudia 

```

Operator Functions 操作符函数

```kotlin
operator fun Int.times(str: String) = str.repeat(this)       // 1
println(2 * "Bye ")                                          // 2

operator fun String.get(range: IntRange) = substring(range)  // 3
val str = "Always forgive your enemies; nothing annoys them so much."
println(str[0..14])    
```

vararg Parameters 可变参数函数

```kotlin
fun printAll(vararg messages: String) {                            // 1
    for (m in messages) println(m)
}
printAll("Hello", "Hallo", "Salut", "Hola", "你好")                 // 2

fun printAllWithPrefix(vararg messages: String, prefix: String) {  // 3
    for (m in messages) println(prefix + m)
}
printAllWithPrefix(
    "Hello", "Hallo", "Salut", "Hola", "你好",
    prefix = "Greeting: "                                          // 4
)

fun log(vararg entries: String) {
    printAll(*entries)                                             // 5
}
```

### Control Flow 控制流

#### When

```kotlin
fun cases(obj: Any) {                                
    when (obj) {                                     // 1   
        1 -> println("One")                          // 2
        "Hello" -> println("Greeting")               // 3
        is Long -> println("Long")                   // 4
        !is String -> println("Not a string")        // 5
        else -> println("Unknown")                   // 6
    }   
}

val result = when (obj) {                   // 1
        1 -> "one"                              // 2
        "Hello" -> 1                            // 3
        is Long -> false                        // 4
        else -> 42                              // 5
    }
```

#### 循环

```kotlin
val cakes = listOf("carrot", "cheese", "chocolate")

for (cake in cakes) {                               // 1
    println("Yummy, it's a $cake cake!")
}

 while (cakesEaten < 5) {                    // 1
        eatACake()
        cakesEaten ++
    }
    
    do {                                        // 2
        bakeACake()
        cakesBaked++
    } while (cakesBaked < cakesEaten)

```

#### Ranges

```kotlin
for(i in 0..3) {             // 1
    print(i)
} 
for(i in 2..8 step 2) {      // 2
    print(i)
}

for (i in 3 downTo 0) {      // 3
    print(i)
}
```

> 以上都是双闭区间

### lambda函数

用Java代码实现一个接口的回调。
```java
 mView.setEventListener(new ExamPlanHomeEventListener(){
    public void onSuccess(Data data){
      //todo
    }
 });
```

用Kotlin实现一个接口的回调,不使用lambda表达式

```kotlin
mView.setEventListener(object: ExamPlanHomeEventListener{
    public void onSuccess(Data data){
      //todo
    } 
});
```
 
只有一个回调的方法，就符合使用lambda函数，简化成这样
```kotlin
mView.setEventListener({
   data: Data ->
   //todo
})
```

或者借助kotlin的智能类型推导直接省略Data

如果以上代码中的data参数没有使用到的话，可以直接把data去掉

```kotlin
mView.setEventListener({
  //todo
})

```

函数最后一个参数是一个函数的话，可以直接把括号的实现提到圆括号外面
```kotlin
mView.setEventListener(){
   //todo
}
```

只有一个参数，可以直接省略圆括号

### 内联函数

#### let扩展函数
> 当你需要去定义一个变量在一个特定的作用域范围内，可选择let函数，它避免写一些判断null的操作。

1、let函数的使用的一般结构

```kotlin
object.let{
   it.todo() // 在函数体内使用it替代object对象去访问其公有的属性和方法
   ...
}
// 另一种用途 判断object为null的操作
object?.let{ // 表示object不为null的条件下，才会去执行let函数体
   it.todo()
}
```

2、let函数底层的inline扩展函数+lambda结构
```kotlin
@kotlin.internal.InlineOnly
public inline fun <T, R> T.let(block: (T) -> R): R = block(this)
```

3、let函数inline结构的分析

从源码let函数的结构来看它是只有一个lambda函数块block作为参数的函数,调用T类型对象的let函数，则该对象为函数的参数。在函数块内可以通过 it 指代该对象。返回值为函数块的最后一行或指定return表达式。

4、let函数的kotlin和Java转化

```kotlin
 fun main(args: Array<String>) {
    val result = "testLet".let {
        println(it.length)
        1000
    }
    println(result)
 }


```
java代码
 
 ```java
 public final class LetFunctionKt {
   public static final void main(@NotNull String[] args) {
      Intrinsics.checkParameterIsNotNull(args, "args");
      String var2 = "testLet";
      int var4 = var2.length();
      System.out.println(var4);
      int result = 1000;
      System.out.println(result);
   }
}
 ```

5、let函数适用的场景

场景一: 最常用的场景就是使用let函数处理需要针对一个可null的对象统一做判空处理。

场景二: 然后就是需要去明确一个变量所处特定的作用域范围内可以使用

#### with内联函数
1、with函数使用的一般结构

```kotlin
 with(object){
   //todo
 }
```

2、with函数底层的inline扩展函数+lambda结构

```kotlin
@kotlin.internal.InlineOnly
public inline fun <T, R> with(receiver: T, block: T.() -> R): R = receiver.block()
```


3、with函数inline结构的分析

with函数和前面的几个函数使用方式略有不同，因为它不是以扩展的形式存在的。它是将某对象作为函数的参数，在函数块内可以通过 this 指代该对象。返回值为函数块的最后一行或指定return表达式。

可以看出with函数是接收了两个参数，分别为T类型的对象receiver和一个lambda函数块，所以with函数最原始样子如下:

```kotlin

val result = with(user, {
    println("my name is $name, I am $age years old, my phone number is $phoneNum")
    1000
})
```

但是由于with函数最后一个参数是一个函数，可以把函数提到圆括号的外部

4、with函数的kotlin和Java转化
```kotlin
    val user = User("Kotlin", 1, "1111111")
    val result = with(user) {
        println("my name is $name, I am $age years old, my phone number is $phoneNum")
        1000
    }
    println("result: $result")

```
java 代码

```java
      Intrinsics.checkParameterIsNotNull(args, "args");
      User user = new User("Kotlin", 1, "1111111");
      String var4 = "my name is " + user.getName() + ", I am " + user.getAge() + " years old, my phone number is " + user.getPhoneNum();
      System.out.println(var4);
      int result = 1000;
      String var3 = "result: " + result;
      System.out.println(var3);

```

5、with函数的适用的场景

适用于调用同一个类的多个方法时，可以省去类名重复，直接调用类的方法即可，经常用于Android中RecyclerView中onBinderViewHolder中，数据model的属性映射到UI上


#### run 内联扩展函数

1、run函数使用的一般结构

```kotlin
object.run{
  //todo
}
```

2、run函数的inline+lambda结构

```kotlin
@kotlin.internal.InlineOnly
public inline fun <T, R> T.run(block: T.() -> R): R = block()
```

3、run函数的inline结构分析

run函数实际上可以说是let和with两个函数的结合体，run函数只接收一个lambda函数为参数，以闭包形式返回，返回值为最后一行的值或者指定的return的表达式。

4、run函数的kotlin和Java转化


```kotlin
fun main(args: Array<String>) {
    val user = User("Kotlin", 1, "1111111")
    val result = user.run {
        println("my name is $name, I am $age years old, my phone number is $phoneNum")
        1000
    }
    println("result: $result")
}
```

```java
      User user = new User("Kotlin", 1, "1111111");
      String var5 = "my name is " + user.getName() + ", I am " + user.getAge() + " years old, my phone number is " + user.getPhoneNum();
      System.out.println(var5);
      int result = 1000;
      String var3 = "result: " + result;
      System.out.println(var3);
```

5、run函数的适用场景

适用于let,with函数任何场景。因为run函数是let,with两个函数结合体，准确来说它弥补了let函数在函数体内必须使用it参数替代对象，在run函数中可以像with函数一样可以省略，直接访问实例的公有属性和方法，另一方面它弥补了with函数传入对象判空问题，在run函数中可以像let函数一样做判空处理


#### apply 内联扩展函数

1、apply函数使用的一般结构

```kotlin
object.apply{
  //todo
}
```

2、apply函数的inline+lambda结构

```kotlin
@kotlin.internal.InlineOnly
public inline fun <T> T.apply(block: T.() -> Unit): T { block(); return this }
```

3、apply函数的inline结构分析

从结构上来看apply函数和run函数很像，唯一不同点就是它们各自返回的值不一样，run函数是以闭包形式返回最后一行代码的值，而apply函数的返回的是传入对象的本身。

4、apply函数的kotlin和Java转化

```kotlin
fun main(args: Array<String>) {
    val user = User("Kotlin", 1, "1111111")

    val result = user.apply {
        println("my name is $name, I am $age years old, my phone number is $phoneNum")
        1000
    }
    println("result: $result")
}

```

java代码：

```java
  User user = new User("Kotlin", 1, "1111111");
  String var5 = "my name is " + user.getName() + ", I am " + user.getAge() + " years old, my phone number is " + user.getPhoneNum();
  System.out.println(var5);
  String var3 = "result: " + user;
  System.out.println(var3);
```
 
5、apply函数的适用场景

整体作用功能和run函数很像，唯一不同点就是它返回的值是对象本身，而run函数是一个闭包形式返回，返回的是最后一行的值。

正是基于这一点差异它的适用场景稍微与run函数有点不一样。
apply一般用于一个对象实例初始化的时候，需要对对象中的属性进行赋值。
或者动态inflate出一个XML的View的时候需要给View绑定数据也会用到，这种情景非常常见。
特别是在我们开发中会有一些数据model向View model转化实例化的过程中需要用到。

多层级判空问题

```
	if (mSectionMetaData == null || mSectionMetaData.questionnaire == null || mSectionMetaData.section == null) {
			return;
		}
		if (mSectionMetaData.questionnaire.userProject != null) {
			renderAnalysis();
			return;
		}
		if (mSectionMetaData.section != null && !mSectionMetaData.section.sectionArticles.isEmpty()) {
			fetchQuestionData();
			return;
		}
```

kotlin的apply函数优化

```
mSectionMetaData?.apply{

//mSectionMetaData不为空的时候操作mSectionMetaData

}?.questionnaire?.apply{

//questionnaire不为空的时候操作questionnaire

}?.section?.apply{

//section不为空的时候操作section

}?.sectionArticle?.apply{

//sectionArticle不为空的时候操作sectionArticle

}

```

#### also 内联扩展函数
1、also函数使用的一般结构
```kotlin
object.also{
  // todo
}
```

2、also函数的inline+lambda结构

```kotlin
@kotlin.internal.InlineOnly
@SinceKotlin(“1.1”)
public inline fun T.also(block: (T) -> Unit): T { block(this); return this }
```

3、also函数的inline结构分析
also函数的结构实际上和let很像唯一的区别就是返回值的不一样，let是以闭包的形式返回，返回函数体内最后一行的值，如果最后一行为空就返回一个Unit类型的默认值。
而also函数返回的则是传入对象的本身

4、also函数编译后的class文件

```kotlin 

fun main(args: Array<String>) {
    val result = "testLet".also {
        println(it.length)
        1000
    }
    println(result)
}
```
 java
```java
  String var2 = "testLet";
  int var4 = var2.length();
  System.out.println(var4);
  System.out.println(var2);

``` 
5、also函数的适用场景

适用于let函数的任何场景，also函数和let很像，只是唯一的不同点就是let函数最后的返回值是最后一行的返回值而also函数的返回值是返回当前的这个对象。
一般可用于多个扩展函数链式调用

6、also函数使用前后的对比 和let函数类似

七、let,with,run,apply,also函数区别

通过以上几种函数的介绍，可以很方便优化kotlin中代码编写，整体看起来几个函数的作用很相似，但是各自又存在着不同。使用的场景有相同的地方比如run函数就是let和with的结合体。下面一张表格可以清晰对比出他们的不同之处。

|函数名	|定义inline的结构|	函数体内使用的对象	|返回值	|是否是扩展函数|	适用的场景|
|:----|:----|:----|:----|:----|:----|
|let	|fun <T, R> T.let(block: (T) -> R): R = block(this)	|it指代当前对象	|闭包形式返回	|是	|适用于处理不为null的操作场景|
|with	|fun <T, R> with(receiver: T, block: T.() -> R): R = receiver.block()	|this指代当前对象或者省略|	闭包形式返回	|否	|适用于调用同一个类的多个方法时，可以省去类名重复，直接调用类的方法即可，经常用于Android中RecyclerView中onBinderViewHolder中，数据model的属性映射到UI上|
|run	|fun <T, R> T.run(block: T.() -> R): R = block()	|this指代当前对象或者省略	|闭包形式返回	|是	|适用于let,with函数任何场景。|
|apply	|fun T.apply(block: T.() -> Unit): T { block(); return this }	|this指代当前对象或者省略	|返回this	|是	|1、适用于run函数的任何场景，一般用于初始化一个对象实例的时候，操作对象属性，并最终返回这个对象。|
|also	|fun T.also(block: (T) -> Unit): T { block(this); return this }	|it指代当前对象	|返回this	|是	|适用于let函数的任何场景，一般可用于多个扩展函数链式调用|
 
2、动态inflate出一个XML的View的时候需要给View绑定数据也会用到.

3、一般可用于多个扩展函数链式调用

4、数据model多层级包裹判空处理的问题


### Reference 参考

* [kotlin Example](https://play.kotlinlang.org/byExample/01_introduction)

