class Rational(n: Int, d: Int) extends AnyRef {

    val num = n
    val den = d

    def this(n: Int) = this(n, 1)

    def + (other: Rational): Rational = new Rational(num * other.den + other.num * den, den * other.den)
    
    def + (i: Int): Rational = new Rational(num + i * den, den)
    
    override def toString = "" + num + "/" + den

}

object RationalMain{

    def main(args: Array[String]) = {
        val r1 = new Rational(4, 3)
        println("R:4/3 = " + r1)
        val r2 = new Rational(7)  
        println("R:7 = " + r2)
        println("R:4/3 + R:7 = " + (r1 + r2))
        println("R:4/3 + I:2 = " + (r1 + 2))
    }

}