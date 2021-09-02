package com.example.newsapi

object Color {
    val  arr = arrayOf("#9932CC","#DC143C","#FFEFD5","#ADFF2F","#00FFFF","#808000","#A52A2A","#FFF0F5")
    var index =1
    fun getcolor():String{
        return arr[index++ %arr.size]
    }
}