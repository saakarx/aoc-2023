fun main() {
  val numbers = hashMapOf<String, String>(
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9"
  )

  fun part1(input: List<String>): Int {
    var res = 0
    for (line in input) {
      if (line.isEmpty()) continue

      val regex = Regex("\\D")
      val newLine = line.replace(regex, "")
      var twoNumbers: String

      if (newLine.length == 1) twoNumbers = newLine.repeat(2)
      else if (newLine.length == 2) twoNumbers = newLine.slice(0..1)
      else twoNumbers = newLine.first().toString() + newLine.last().toString()

      res += twoNumbers.toInt()
    }

    return res
  }

  fun part2(input: List<String>): Int {
    var res = 0
    for (line in input) {
      if (line.isEmpty()) continue

      val numbersWordsRegex = Regex("(one|two|three|four|five|six|seven|eight|nine|zero)")
      val nonDigitRegex = Regex("\\D")
      val newLine = line
        .replace(numbersWordsRegex, fun(match) = numbers.getOrDefault(match.value, ""))
        .replace(nonDigitRegex, "")

      var twoNumbers: String

      if (newLine.length == 1) twoNumbers = newLine.repeat(2)
      else if (newLine.length == 2) twoNumbers = newLine.slice(0..1)
      else twoNumbers = newLine.first().toString() + newLine.last().toString()

      res += twoNumbers.toInt()
    }

    return res
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day01_test")
  val testOutput = part1(testInput)
  check(testOutput == 142)

  val input = readInput("Day01")
  part1(input).println()
  part2(input).println()
}
