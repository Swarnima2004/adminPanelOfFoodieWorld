package com.example.adminpanelfoodieworld.model

data class UserModel(
    val name:String ?= null,
    val nameOfRestaurent:String ?= null,
    val email:String ?= null,
    val password:String ?= null,
    val phone:String ?= null,
    val addess:String ?= null,
)
