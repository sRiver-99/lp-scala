object StarParameter {

    def stringConcat(separator: Char, strings: String*): String = {
        println("The type of 'strings' is " + strings.getClass)
        strings.reduce((acc: String, str: String) => acc + separator + str)
    }

    /*
    Le funzioni (ed i metodi) consentono di dichiarare l'ultimo (e solo l'ultimo) dei loro parametri 
    di tipo Type*, questo permette di passare infiniti parametri di tipo Type che verranno trasformati
    in un ArraySeq[Type] prima della chiamata
    */

    def main(args: Array[String]) = {
        println(stringConcat(' ', "Lorem", "ipsum", "dolor", "sit", "amet,", "consectetur",
            "adipiscing", "elit,", "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et",
            "dolore", "magna", "aliqua."))
    }

}