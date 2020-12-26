package edu.sicnu.test02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val spinnerSelect = SpinnerSelect()
    var t = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            textView.text = spinnerSelect.getLanguage(spinner.selectedItem.toString())
        }
        button2.setOnClickListener {

            val textView01 = CreateTextView(this)
            textView01.initTextView(t,this)
            t++
            LinearLayout2.addView(textView01.textView)
        }

    }
}
