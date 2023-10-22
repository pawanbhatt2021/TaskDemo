package com.example.taskdemo

fun main(args:Array<String>){
    val numberArray = intArrayOf(10,5,7,9,2,60)
    val secondLargest = findSecondLargestElement(numberArray)

    println("Second Largest Elements is $secondLargest")
}

 fun findSecondLargestElement(arr:IntArray):Int{
    if(arr.size<2){
        throw IllegalArgumentException("Array should have atleast two elements")
    }
    var firstMax = Int.MIN_VALUE
    var secondMax = Int.MIN_VALUE

    for(num in arr){
        if(num>firstMax){
            secondMax = firstMax
            firstMax =num
        }
        else if(num > secondMax  && num !=firstMax) {
                secondMax = num
            }
    }
    return secondMax
}