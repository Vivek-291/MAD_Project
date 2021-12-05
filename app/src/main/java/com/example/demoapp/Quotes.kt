package com.example.demoapp

class Quotes(var text:String, var author:String) {

    companion object{

        var QuotesArray: List<Quotes> = ArrayList()
    }
}