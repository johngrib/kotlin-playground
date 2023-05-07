package johngrib.math

import kotlin.math.abs

class Ratio() {

    companion object {
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