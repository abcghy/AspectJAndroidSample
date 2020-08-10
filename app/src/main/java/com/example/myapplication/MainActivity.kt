package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.myapplication.annotation.CheckVIP
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var isVIP: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isVIP = swi.isChecked

        swi.setOnCheckedChangeListener { compoundButton, b ->
            isVIP = b
        }

        btn.setOnClickListener {
            enterVipActivity()
        }

        val picUrl = "https://static01.versa-ai.com/upload/099c0ce79dd9/630b1589-d79e-4a62-b2a9-ce5addc0f641.jpg"

        Glide.with(this)
            .load(OSSUtil.addScale(picUrl, 200, 200))
//            .load(picUrl)
            .into(iv)
    }

    @CheckVIP
    private fun enterVipActivity() {
        Toast.makeText(this, "假装进入 VIP 页面", Toast.LENGTH_SHORT).show()
    }
}