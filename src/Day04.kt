import kotlin.math.pow

fun main() {
  fun part1(input: List<String>): Int {
    var ans = 0

    input.forEach(fun(line) {
      val (_, games) = line.split(":")
      val (winningNumbers, numbers) = games.split("|")
      val winningNumbersList = winningNumbers.trim().split(Regex("\\s+")).map { i -> i.toInt() }
      val numbersList = numbers.trim().split(Regex("\\s+")).map { i -> i.toInt() }

      val winningNumbersInNumbersList = numbersList.filter { i -> winningNumbersList.contains(i) }

      ans += if (winningNumbersInNumbersList.isNotEmpty()) {
        2.0.pow((winningNumbersInNumbersList.size - 1).toDouble()).toInt()
      } else {
        0
      }
    })

    println("ans: $ans\n-----")

    return ans
  }

  fun part2(input: List<String>): Int {
    val originalScratchCards = hashMapOf<Int, Int>()
    val copiedScratchCards = hashMapOf<Int, Int>()

    for (i in 1..input.size) {
      originalScratchCards[i] = 1
    }

    for (i in input.indices) {
      val line = input[i]
      // Format for a play -> Card 1: 12 12 1 2 12 | 12 12 12 12 12 12 12 12
      val (_, games) = line.split(":")
      val (winningNumbers, numbers) = games.split("|")
      val winningNumbersList = winningNumbers.trim().split(Regex("\\s+")).map { v -> v.toInt() }
      val numbersList = numbers.trim().split(Regex("\\s+")).map { v -> v.toInt() }
      val winningNumbersInNumbersList = numbersList.filter { v -> winningNumbersList.contains(v) }

      for (index in 1..winningNumbersInNumbersList.size) {
        val newIdx = index + (i + 1)
        copiedScratchCards[newIdx] =
          (copiedScratchCards.getOrDefault(newIdx, 0) + 1) + copiedScratchCards.getOrDefault(i + 1, 0)
        // ^ add 1 to the copy scratch cards for the newIdx             ^ if there are copy of the original scratch card, add them
      }
    }

    return originalScratchCards.values.sum() + copiedScratchCards.values.sum()
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day04_test")

  val part1TestInput = part1(testInput)
  check(part1TestInput == 13)

  val part2TestInput = part2(testInput)
  check(part2TestInput == 30)

  val input = readInput("Day04")
  part1(input).println()
  part2(input).println()
}
