object MutableImmutable{

    def main(args: Array[String]) = {
        val array: Array[String] = new Array(3)
        printf("array = [" + array.mkString(", ") + "]\n")
        /*
        array = new array(2)
        Non è possibile riassegnare una variabile dichiatrata come val,
        il compilatore non lo permette
        */
        array(0) = "Hello World"
        printf("array = [" + array.mkString(", ") + "]\n")
        var price: Double = 100
        printf("price = " + price + "\n")
        price += price*.20
        //É possibile riassegnare una variabile dichiarata come var
        printf("price = " + price + "\n")
    }

}