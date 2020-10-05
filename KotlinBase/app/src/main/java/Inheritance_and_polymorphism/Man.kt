package Inheritance_and_polymorphism
class Man (name:String):Human(name){
    override fun eat() {
        print("${name}吃得多 ")
    }

}