package johngrib.math

import kotlin.math.abs

class Ratio
private constructor(
    private val _numerator: Int,
    private val _denominator: Int,
) {
    private var gcdValue: Int
    private var numerator: Int
    private var denominator: Int

    init {
        if (_denominator == 0) {
            throw IllegalArgumentException("Denominator cannot be 0.")
        }
        gcdValue = gcd(_numerator, _denominator)
        numerator = _numerator / gcdValue
        denominator = _denominator / gcdValue
    }

    override fun toString(): String {
        if (numerator == 0) {
            return "0"
        }
        if (denominator == 1) {
            return "$numerator"
        }
        if ((numerator < 0 && denominator < 0) || (numerator > 0 && denominator > 0)) {
            return "$numerator/$denominator"
        }
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            return "-${abs(numerator)}/${abs(denominator)}"
        }
        return "$numerator/$denominator"
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