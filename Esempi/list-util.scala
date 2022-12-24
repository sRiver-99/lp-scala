object ListUtil{

    def map[A, B](func: A => B, list: List[A]): List[B] = {
        list match {
            case Nil => Nil
            case hd::tl => func(hd) :: map(func, tl)
        }
    }

    def reduce[T](func: (T, T) => T, list: List[T]): T = {
        def reduceAux(acc: T, list: List[T]): T = list match {
            case Nil => acc
            case hd::tl => reduceAux(func(acc, hd), tl)
        }
        reduceAux(list.head, list.tail)
    }

    def exists[T](pred: T => Boolean, list: List[T]): Boolean = {
        for (elem <- list if pred(elem)) return true;
        false
    }

    def forall[T](pred: T => Boolean, list: List[T]): Boolean = {
        reduce((X: Boolean, Y: Boolean) => X && Y, map(pred, list))
    }

    def quicksort[T](beforeThan: (T, T) => Boolean, list: List[T]): List[T] = {
        list match {
            case Nil => Nil
            case pivot::tl =>
                /*
                Il metodo partition divide una lista in due sottoliste:
                part1 contiene tutti gli elementi della lista che soddisfano il predicato
                part2 contiene tutti gli elementi della lista che non soddisfano il predicato
                L'operatore ::: serve a concatenare due liste
                */
                val (part1, part2) = tl.partition((X: T) => beforeThan(X, pivot))
                quicksort(beforeThan, part1) ::: List(pivot) ::: quicksort(beforeThan, part2)
        }
    }

    def main(args: Array[String]) = {
        val mylist = List(1, 2, 3, 4, 5, 6, 7)
        printf("mylist = [" + mylist.mkString(", ") + "]\n")
        printf("map(+5, mylist) = [" + map((elem: Int) => elem + 5, mylist).mkString(", ") + "]\n")
        printf("map(*2, mylist) = [" + map((elem: Int) => elem * 2, mylist).mkString(", ") + "]\n")
        printf("reduce(+, mylist) = " + reduce((acc: Int, elem: Int) => acc + elem, mylist) + "\n")
        printf("reduce(*, mylist) = " + reduce((acc: Int, elem: Int) => acc * elem, mylist) + "\n")
        printf("exists(even, mylist) = " + exists((elem: Int) => elem % 2 == 0, mylist) + "\n")
        printf("exists(>10, mylist) = " + exists((elem: Int) => elem > 10, mylist) + "\n")
        printf("forall(odd, mylist) = " + forall((elem: Int) => elem % 2 == 1, mylist) + "\n")
        printf("forall(<10, mylist) = " + forall((elem: Int) => elem < 10, mylist) + "\n")
        printf("quicksort(descending, mylist) = [" + quicksort((X: Int, Y: Int) => X > Y, mylist).mkString(", ") + "]\n")
    }

}