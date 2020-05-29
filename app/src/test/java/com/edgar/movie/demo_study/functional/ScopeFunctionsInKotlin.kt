package com.edgar.movie.demo_study.functional

import org.junit.Test

class ScopeFunctionsInKotlin {

    // `let` can be used for scoping and null-checks.

    // let executes the given block of code and returns the result of its last expression

    // Calls the given block on the result on the string "test".





//    let函数适用的场景
//    场景一: 最常用的场景就是使用let函数处理需要针对一个可null的对象统一做判空处理。
//    场景二: 然后就是需要去明确一个变量所处特定的作用域范围内可以使用

//    没有使用let函数的代码是这样的，看起来不够优雅
//
//    mVideoPlayer?.setVideoView(activity.course_video_view)
//    mVideoPlayer?.setControllerView(activity.course_video_controller_view)
//    mVideoPlayer?.setCurtainView(activity.course_video_curtain_view)

//    使用let函数后的代码是这样的

//    mVideoPlayer?.let {
//        it.setVideoView(activity.course_video_view)
//        it.setControllerView(activity.course_video_controller_view)
//        it.setCurtainView(activity.course_video_curtain_view)
//    }


    @Test
    fun demo () {
        // let executes the given block of code and returns the result of its last expression
        val empty = "test".let {  // 1
            print(it)             // 2
            it.isEmpty()       // 3 let returns the value of this expression.
        }
        println(" is empty: $empty")


        fun printNonNull(str: String?) {
            println("Printing \"$str\":")
            str?.let {                         // 4
                print("\t")
                print(it)
                println()
            }
        }
        printNonNull(null)
        printNonNull("my string")

        //  The difference is that inside run the object is accessed by this.
        fun getNullableLength(ns: String?) {
            println("for \"$ns\":")
            ns?.run {                                                  // 1
                println("\tis empty? " + isEmpty())                    // 2
                println("\tlength = $length")
                length                                                 // 3
            }
        }
        getNullableLength(null)
        getNullableLength("")
        getNullableLength("some string with Kotlin")


        // with is a non-extension function that can access members of its argument concisely: you can omit the instance name when referring to its members.

//        with(configuration) {
//            println("$host:$port")
//        }

        // instead of:
//        println("${configuration.host}:${configuration.port}")

        // apply executes a block of code on an object and returns the object itself. Inside the block, the object is referenced by this. This function is handy for initializing objects.

//        val jake = Person()                                     // 1
//        val stringDescription = jake.apply {                    // 2
//            name = "Jake"                                       // 3
//            age = 30
//            about = "Android developer"
//        }.toString()

        // like apply: Inside the block, the object is referenced by it, so it's easier to pass it as an argument. This function is handy for embedding additional actions, such as logging in call chains
//        val jake = Person("Jake", 30, "Android developer")   // 1
//            .also {                                          // 2
//                writeCreationLog(it)                         // 3
//            }



    }

}