package johngrib.math

import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import kotlin.math.abs

@DisplayName("Ratio")
class RatioTest : DescribeSpec({
    describe("gcd") {
        val gcdParcialFirst = { number1: Int -> { number2: Int -> Ratio.gcd(number1, number2) } }
        val gcdParcialLast = { number1: Int -> { number2: Int -> Ratio.gcd(number2, number1) } }

        context("서로 다른 두 정수가 주어지면") {
            val givenCases = listOf(
                Triple(2, 4, 2),
                Triple(54, 24, 6),
                Triple(-54, 24, 6),
                Triple(48, 180, 12),
                Triple(48, -180, 12),
                Triple(17, 22, 1),
            )

            it("두 수의 최대공약수를 리턴한다") {
                givenCases.forEach { (number1, number2, expected) ->
                    assertNotEquals(number1, number2, "주어진 두 수는 서로 다른 수여야 합니다.")
                    val resultGCD = Ratio.gcd(number1, number2)

                    assertEquals(resultGCD, expected, "리턴된 GCD 값이 예상한 값과 같아야 합니다.")
                    assertTrue(resultGCD > 0, "리턴된 GCD 값은 0보다 큰 정수여야 합니다.")
                }
            }
        }
        context("똑같은 두 정수가 주어지면") {
            val givenCases = listOf(
                2, 7, 31, 100, -41, Int.MAX_VALUE, Int.MIN_VALUE + 1
            )

            it("두 수의 최대공약수를 리턴한다") {
                givenCases.forEach { number  ->
                    val resultGCD = Ratio.gcd(number, number)

                    assertEquals(resultGCD, abs(number), "리턴된 GCD 값이 예상한 값과 같아야 합니다.")
                    assertTrue(resultGCD > 0, "리턴된 GCD 값은 0보다 큰 정수여야 합니다.")
                }
            }
        }

        context("'1' 과 '정수'가 주어지면") {
            val gcd1N = gcdParcialFirst(1)
            val gcdN1 = gcdParcialLast(1)

            val givenCases = listOf(
                1, 2, 3, 7, 100,
                -1, -2, -3, -7, -100,
                Int.MAX_VALUE, Int.MIN_VALUE,
            )

            it("1을 리턴한다") {
                givenCases.forEach { number ->
                    gcd1N(number) shouldBe 1
                    gcdN1(number) shouldBe 1
                }
            }
        }

        context("'0'과 '0이 아닌 정수'가 주어지면") {
            val gcd1N = gcdParcialFirst(0)
            val gcdN1 = gcdParcialLast(0)
            val givenCases = listOf(
                1, 2, 3, 7, 100,
                -1, -2, -3, -7, -100,
                Int.MAX_VALUE, Int.MIN_VALUE,
            )

            it("0이 아닌 주어진 수의 절대값을 리턴한다") {
                givenCases.forEach { number ->
                    gcd1N(number) shouldBe abs(number)
                    gcdN1(number) shouldBe abs(number)
                }
            }
        }
    }
})