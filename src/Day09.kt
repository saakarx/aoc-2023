fun main() {
  fun getNextNumberInSeries(numbers: List<Int>): Int {
    if (numbers.all { i -> i == 0 }) {
      return 0
    }

    val newList = ArrayList<Int>()
    for (idx in 0 until (numbers.size - 1)) {
      newList.add(numbers[idx + 1] - numbers[idx])
    }
    return getNextNumberInSeries(newList) + newList.last()
  }

  fun getPrevNumberInSeries(numbers: List<Int>): Int {
    if (numbers.all { i -> i == 0 }) {
      return 0
    }

    val newList = ArrayList<Int>()
    for (idx in 0 until (numbers.size - 1)) {
      newList.add(numbers[idx + 1] - numbers[idx])
    }
    return newList.first() - getPrevNumberInSeries(newList)
  }

  fun extrapolateNextNumberInSeries(numbers: List<Int>): Int {
    return numbers.last() + getNextNumberInSeries(numbers)
  }

  fun extrapolatePrevNumberInSeries(numbers: List<Int>): Int {
    return numbers.first() - getPrevNumberInSeries(numbers)
  }

  fun part1(input: List<String>): Int {
    val result = ArrayList<Int>()
    input.forEach(fun(line) {
      val numbers = line.split(" ").map { i -> i.toInt() }
      result.add(extrapolateNextNumberInSeries(numbers))
    })

    return result.sum()
  }

  fun part2(input: List<String>): Int {
    val result = ArrayList<Int>()

    input.forEach(fun(line) {
      val numbers = line.split(" ").map { i -> i.toInt() }
      result.add(extrapolatePrevNumberInSeries(numbers))
    })

    return result.sum()
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day09_test")
  val part1TestOutput = part1(testInput)
  val part2TestOutput = part2(testInput)
  check(part1TestOutput == 114)
  check(part2TestOutput == 2)

  val input = readInput("Day09")
  part1(input).println()
  part2(input).println()

}
