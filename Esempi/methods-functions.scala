object MethodsFunctions{

    def succMethod(x: Int) = x + 1
    def idMethod[T](x: T) = x

    def main(args: Array[String]) = {
        val succFunction = (x: Int) => x + 1
        printf("succFunction(1) = " + succFunction(1) +
            " [Int => Int] is an alias for succFunction.apply(1) = " + succFunction.apply(1) + "\n")
        /*
        Le funzioni sono valori di una classe particolare che ha un metodo apply(Args),
        sono di prima classe ma non supportano il polimorfismo parametrico
        */
        val succFunctionFromMethod = succMethod _
        printf("succMethod(2) = " + succMethod(2) + " [Int => Int]\nsuccFunctionFromMethod(3) = " +
            succFunctionFromMethod(3) + " [Int => Int]\n")
        /*
        I metodi devono essere trasformati in funzioni (applicazione parziale) per poter essere
        assegnati ad una variabile, tuttavia possono essere passati come argomenti senza effettuare
        la conversione
        */
        printf("idMethod(4) = " + idMethod(4) + " [T => T]\nidMethod(\"hello\") = " +
            idMethod("hello") + " [T => T]\n")
        val idFunctionFromMethod = idMethod _
        printf("idFunctionFromMethod [Nothing => Nothing]\n")
        /*
        Se un metodo che usa il polimorfismo parametrico viene trasformato in una funzione senza
        specificare di quali tipi saranno i parametri polimorfi, diventerà una funzione di tipo
        Nothing => Nothing e non potrà essere chiamata.
        Non è possibile passare direttamente tale metodo come parametro
        */
        val idFunctionFromMethodInt = idMethod[Int] _
        printf("idFunctionFromMethodInt(5) = " + idFunctionFromMethodInt(5) + " [Int => Int]\n")
        val idFunctionFromMethodString = idMethod[String] _
        printf("idFunctionFromMethodString(\"world\") = " + idFunctionFromMethodString("world") +
            " [String => String]\n")
        val idFunctionFromMethodAny = idMethod[Any] _
        printf("idFunctionFromMethodAny(6) = " + idFunctionFromMethodAny(6) +
            " [Any => Any]\n")
        printf("idFunctionFromMethodAny(\"Lorem ipsum\") = " + idFunctionFromMethodAny("Lorem ipsum") +
            " [Any => Any]\n")
    }

}