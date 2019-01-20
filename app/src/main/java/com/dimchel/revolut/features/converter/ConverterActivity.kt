package com.dimchel.revolut.features.converter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dimchel.revolut.R
import com.dimchel.revolut.RevolutApp

class ConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        RevolutApp.getDiManager().getConverterComponent().inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_converter)
    }
}
