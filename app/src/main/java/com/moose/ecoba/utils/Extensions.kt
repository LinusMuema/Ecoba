package com.moose.ecoba.utils


import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.toast(message: String?){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.hide() { this.visibility = View.GONE }

fun View.show() { this.visibility = View.VISIBLE }