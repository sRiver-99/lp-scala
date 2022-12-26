import scala.annotation.tailrec

/*
Per creare una funzione (o un metodo) che sia ricorsiva di coda, è necessario importare ed utilizzare
l'annotazione 'tailrec'. Nel caso in cui il codice non sia veramente ricorsivo di coda il compilatore
lo comunica e fa fallire la compilazione
*/

object TailRecursion {
    
    def fact(n: Long): Long = {
        @tailrec def factAux(n: Long, acc: Long): Long = {
            if (n == 0) acc
            else factAux(n - 1, acc * n)
        }
        factAux(n, 1)
    }

    def main(args: Array[String]) = {
        for(N <- List.range(0, 21)){
            println(s"$N! = " + fact(N))
        }
    }

}