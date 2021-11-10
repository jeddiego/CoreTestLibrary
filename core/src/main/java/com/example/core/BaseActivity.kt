package com.example.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T>: AppCompatActivity() where T: ViewBinding {
    lateinit var bind: T
    abstract fun initBind(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = initBind()
        setContentView(bind.root)
//        this.initView(savedInstanceState)
    }

    fun addFragment(container: Int, fragment: Fragment, addToBackStack: Boolean) {
        val transaction = this.supportFragmentManager
            .beginTransaction()
            .add(container, fragment)
        if (addToBackStack) transaction.addToBackStack(fragment::class.java.name)
        transaction.commit()
    }

    fun replaceFragment(
        container: Int,
        fragment: Fragment,
        addToBackStack: Boolean,
        tag: String? = null
    ) {
        val transaction = this.supportFragmentManager
            .beginTransaction()
            .replace(container, fragment)
        if (addToBackStack) transaction.addToBackStack(tag ?: fragment::class.java.name)
        transaction.commit()
    }
}