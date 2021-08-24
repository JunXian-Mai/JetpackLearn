package org.markensic.learn.jetpack

fun closerFun(): () -> Int {
    println("define count!")
    var count = 0
    return {
        count++
    }
}

fun main() {
    val fun1 = closerFun()
    val fun2 = closerFun()

    println("---defined---")

    println(fun1())
    println(fun1())
    println(fun1())

    println(fun2())
    println(fun2())
    println(fun2())
}