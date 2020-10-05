package edu.sicnu.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var i =1
        button.setOnClickListener{
        val textView01 = CreateTextView(this)

            textView01.initTextView(i)
            LinearLayout2.addView(textView01.textView01)
            i++
        }
    }
}
