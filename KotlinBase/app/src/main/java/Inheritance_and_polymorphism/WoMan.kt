package Inheritance_and_polymorphism

class WoMan (name:String):Human(name) {
    override fun eat(){
        print("${name}吃得少")
    }
}