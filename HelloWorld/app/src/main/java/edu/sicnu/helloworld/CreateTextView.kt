    package edu.sicnu.helloworld

    import android.content.Context
    import android.content.ContextWrapper
    import android.renderscript.RenderScript
    import android.view.ViewGroup
    import android.widget.TextView
    import kotlin.coroutines.coroutineContext

    class CreateTextView(appContext: Context) {
        var textView01 = TextView(appContext)

        fun initTextView(i:Int){
            textView01.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            textView01.textSize = 30F
            textView01.text = "this add text view"+i
        }
    }

