package com.hans.kotlindemo

/**
 *
 *
 * @Author  hanbo
 * @Since   2018/9/25
 */

public class TT {
    fun sum(a: Int, b: Int): Int {   // Int 参数，返回值 Int
        return a + b
    }

    fun sum2(a: Int, b: Int) = a + b

    public fun su3(a: Int, b: Int) = a + b

    fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size) {

    }

    fun main(args: Array<String>) {
        var dd :Array<Byte>  =  Array(20,{});
        read(null,0)
        print(su3(4, 6))
    }
}

