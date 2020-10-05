package edu.sicnu.test02


class SpinnerSelect {
    fun getLanguage(featurn: String) = when(featurn){
        "快速" -> "C"
        "容易" -> "Python"
        "新语言" ->  "Kotlin"
        "面向对象" -> "Java"
        else -> "unkown"
    }
}
