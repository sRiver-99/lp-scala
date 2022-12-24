object ComprehensionsYield{

    def sum_even_numbers = (L: List[Int]) => {
        var sum = 0
        for (X <- L if X % 2 == 0) {
            sum += X
        }
        sum
    }

    def sqrt_divisors = (X: Int) => {
        /*
        È necessario aggiungere 1 alla radice quadrata perchè il massimo è escluso
        List.range(2, 3) = List(2)
        List.range(2, 5) = List(2, 3, 4)
        */
        for (Y <- List.range(2, math.sqrt(X).toInt + 1) if X % Y == 0) yield Y
    }

    def is_prime = (X: Int) => {
        sqrt_divisors(X).length == 0
    }

    /*
    Versione alternativa di is_prime
    def is_prime(X: Int): Boolean = {
        for (Y <- List.range(2, math.sqrt(X).toInt + 1) if X % Y == 0) return false
        true
    }
    */

    def main(args: Array[String]) = {
        printf("Sum of even numbers between 1 and 10 = " + sum_even_numbers(List.range(1, 11)).toString() + "\n")
        printf("Sum of even numbers between 1 and 99 = " + sum_even_numbers(List.range(1, 100)).toString() + "\n")
        printf("Is 2 prime? " + is_prime(2) + " [sqrt_divisors = " + sqrt_divisors(2) + "]\n")
        printf("Is 3 prime? " + is_prime(3) + " [sqrt_divisors = " + sqrt_divisors(3) + "]\n")
        printf("Is 4 prime? " + is_prime(4) + " [sqrt_divisors = " + sqrt_divisors(4) + "]\n")
        printf("Is 5 prime? " + is_prime(5) + " [sqrt_divisors = " + sqrt_divisors(5) + "]\n")
        printf("Is 6 prime? " + is_prime(6) + " [sqrt_divisors = " + sqrt_divisors(6) + "]\n")
        printf("Is 256 prime? " + is_prime(256) + " [sqrt_divisors = " + sqrt_divisors(256) + "]\n")
        printf("Is 257 prime? " + is_prime(257) + " [sqrt_divisors = " + sqrt_divisors(257) + "]\n")
    }

}