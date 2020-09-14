@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.minDivisor
import lesson3.task1.revert
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double =
    if (v.isEmpty()) 0.0
    else sqrt(v.map { it * it }.fold(0.0) { prRes, el -> prRes + el })

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
    if (list.isEmpty()) 0.0
    else list.sum() / list.size

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    val s = mean(list)
    for (i in 0 until list.size) list[i] -= s
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int =
    a.foldIndexed(0) { index, prRes, el -> prRes + b[index] * el }

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int =
    p.foldIndexed(0) { k, s, el -> s + el * x.toDouble().pow(k).toInt() }

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isEmpty()) return list
    var s = list[0]
    for (i in 1 until list.size) {
        s += list[i]
        list[i] = s
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var n1 = n
    val list = mutableListOf<Int>()
    var d: Int
    while (n1 != 1) {
        d = minDivisor(n1)
        n1 /= d
        list.add(d)
    }
    return list
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    if (n < base) return listOf(n)
    var n1 = n
    val num = mutableListOf<Int>()
    while (n1 > 0) {
        num.add(n1 % base)
        n1 /= base
    }
    return num.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var n1 = n
    var a: Int
    var s = ""
    while (n1 >= base) {
        a = n1 % base
        if (a < 10) s += "$a"
        else s += 'a' + a - 10
        n1 /= base
    }
    if (n1 < 10) s += "$n1"
    else s += 'a' + n1 - 10
    return s.reversed()
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int =
    digits.reversed().foldIndexed(0) { index, prRes, el -> prRes + el * base.toDouble().pow(index).toInt() }

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var num = 0
    val s = str.reversed()
    for (i in s.indices)
        num += if (s[i] in 'a'..'z') (s[i].toInt() - 87) * base.toDouble().pow(i).toInt()
        else (s[i].toInt() - 48) * base.toDouble().pow(i).toInt()
    return num
}


/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var n1 = n
    var s = StringBuilder()
    val rn = "IXCMVLD"
    var a: Int
    var i = 0
    while (n1 != 0) {
        a = n1 % 10
        when (a) {
            in 1..3 -> for (j in 1..a) s = StringBuilder(s).append(rn[i])
            4 -> s = StringBuilder(s).append(rn[4 + i]).append(rn[i])
            5 -> s = StringBuilder(s).append(rn[4 + i])
            in 6..8 -> {
                for (j in 1..(a - 5)) s = StringBuilder(s).append(rn[i])
                s = StringBuilder(s).append(rn[4 + i])
            }
            9 -> s = StringBuilder(s).append(rn[i + 1]).append(rn[i])
        }
        n1 /= 10
        i++
    }
    return s.toString().reversed()
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var s = halfTheNumber(n / 1000)
    s = s.trim()
    var s1 = StringBuilder(s)
    if (s1.isNotEmpty()) {
        if (n / 10000 % 10 == 1) s1 = StringBuilder(s1).append(" тысяч ")
        else
            when (n / 1000 % 10) {
                1 -> {
                    s1 = s1.deleteRange(s1.length - 4, s1.length)
                    s1 = StringBuilder(s1).append("одна тысяча ")
                }
                2 -> {
                    s1 = s1.deleteRange(s1.length - 3, s1.length)
                    s1 = StringBuilder(s1).append("две тысячи ")
                }
                in 3..4 -> s1 = StringBuilder(s1).append(" тысячи ")
                else -> s1 = StringBuilder(s1).append(" тысяч ")
            }
    }
    s1 = StringBuilder(s1).append(halfTheNumber(n % 1000))
    return s1.toString().trim()
}

fun halfTheNumber(x: Int): String {
    val numbers = listOf(
        "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять",
        "сорок", "девяносто", "сто", "двести"
    )
    var s = StringBuilder()
    var a = x / 100
    if (x >= 100) {
        when (a) {
            in 1..2 -> s = StringBuilder(s).append(numbers[11 + a])
            else -> {
                s = StringBuilder(s).append(numbers[a - 1])
                s = if (a in 3..4) StringBuilder(s).append("ста")
                else StringBuilder(s).append("сот")
            }
        }
        s = StringBuilder(s).append(" ")
    }
    a = x / 10 % 10
    if (a != 0) {
        when (a) {
            1 -> {
                val b = x % 10
                s = when (b) {
                    0 -> StringBuilder(s).append(numbers[9])
                    1, 3 -> StringBuilder(s).append(numbers[b - 1])
                    2 -> StringBuilder(s).append("две")
                    else -> StringBuilder(s).append(numbers[b - 1].dropLast(1))
                }
                if (b != 0) s = StringBuilder(s).append("надцать")
                return s.toString()
            }
            4 -> s = StringBuilder(s).append(numbers[10])
            9 -> s = StringBuilder(s).append(numbers[11])
            else -> {
                s = StringBuilder(s).append(numbers[a - 1])
                s = if (a in 5..8) StringBuilder(s).append("десят")
                else StringBuilder(s).append("дцать")
            }
        }
        s = StringBuilder(s).append(" ")
    }
    a = x % 10
    if (a != 0) s = StringBuilder(s).append(numbers[a - 1])
    return s.toString()
}