abstract class Bool {

    def and(b: => Bool): Bool
    def or(b: => Bool): Bool

}

case object True extends Bool {

    def and(b: => Bool) = b
    def or(b: => Bool) = this

}

case object False extends Bool {

    def and(b: => Bool) = this
    def or(b: => Bool) = b

}

object ShortCircuitLogicMain{

    def bottom: () => Nothing = () => bottom()

    def main(args: Array[String]) = {
        print("True and bottom() => ")
        try{
            println(True and bottom())
        }catch {
            case e: java.lang.StackOverflowError => println("java.lang.StackOverflowError")
        }
        print("False and bottom() => ")
        try {
            println(False and bottom())
        } catch {
            case e: java.lang.StackOverflowError => println("java.lang.StackOverflowError")
        }
        print("True or bottom() => ")
        try{
            println(True or bottom())
        }catch{
            case e: java.lang.StackOverflowError => println("java.lang.StackOverflowError")
        }
        print("False or bottom() => ")
        try {
            println(False or bottom())
        } catch {
            case e: java.lang.StackOverflowError => println("java.lang.StackOverflowError")
        }
    }

}