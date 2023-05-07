package johngrib.math

import kotlin.math.abs

class Ratio {
    /**
     * 약분된 분자.
     */
    private val numerator: Int

    /**
     * 약분된 분모.
     */
    private val denominator: Int

    /**
     * 부호가 + 이면 1, - 이면 -1.
     */
    private val sign: Int

    private constructor(
        numerator: Int,
        denominator: Int,
    ) {
        if (denominator == 0) {
            throw IllegalArgumentException("Denominator cannot be 0.")
        }
        this.sign = when {
            numerator == 0 -> 1
            numerator > 0 && denominator > 0 -> 1
            numerator < 0 && denominator < 0 -> 1
            else -> -1
        }
        val gcdValue = gcd(numerator, denominator)
        this.numerator = abs(numerator) / gcdValue
        this.denominator = abs(denominator) / gcdValue
    }

    override fun toString(): String {
        if (numerator == 0) {
            return "0"
        }
        val prefixSign = if (sign < 0) "-" else ""
        if (denominator == 1) {
            return "${prefixSign}$numerator"
        }
        return "$prefixSign$numerator/$denominator"
    }

    companion object {
        fun of(numerator: Int, denominator: Int): Ratio {
            return Ratio(numerator, denominator)
        }

        /**
         * 두 수의 '최대공약수'를 리턴합니다.
         */
        fun gcd(a: Int, b: Int): Int {
            if (a == 0 && b == 0) {
                throw IllegalArgumentException("GCD cannot be calculated for both 0 and 0.")
            }
            var num1 = abs(a)
            var num2 = abs(b)
            while (num2 != 0) {
                val tempNum = num1 % num2
                num1 = num2
                num2 = tempNum
            }
            return num1
        }
    }
}