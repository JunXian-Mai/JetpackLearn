package org.markensic.learn.jetpack

fun closuerFun(): () -> Int {
  println("define count!")
  var count = 0
  return {
    count++
  }
}

fun main() {
  val fun1 = closuerFun()
  val fun2 = closuerFun()

  println("---defined---")

  println(fun1())
  println(fun1())
  println(fun1())

  println(fun2())
  println(fun2())
  println(fun2())
}