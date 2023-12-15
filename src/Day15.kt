fun main() {
  fun hashString(input: String): Int {
    val asciiValues = input.toCharArray().map { i -> i.code }
    var result = 0

    asciiValues.forEach(fun(i) {
      result += i
      result *= 17
      result %= 256
    })

    return result
  }

  fun part1(input: List<String>): Int {
    var result = 0

    input.forEach(fun(line) {
      val strings = line.split(",")
      result += strings.sumOf { i -> hashString(i) }
    })

    return result
  }

  data class Lens(val name: String, val value: Int)

  fun part2(input: List<String>): Int {
    val map = hashMapOf<Int, ArrayList<Lens>>()

    input.forEach(fun(line) {
      val strings = line.split(",")
      val regex = Regex("^(\\w+)([=-])(\\d*)\$")

      strings.forEach(fun(string) {
        val match = regex.matchEntire(string)
        if (match != null) {
          val (_, label, op, number) = match.groupValues
          val labelHashValue = hashString(label)

          val newMapAtCursor = map.getOrDefault(labelHashValue, ArrayList())

          // search if lens already exists
          val idx = newMapAtCursor.indexOfFirst { i -> i.name == label }

          if (op == "=") {
            // if does not exists update the lens
            if (idx != -1) {
              newMapAtCursor[idx] = Lens(label, number.toInt())
              // add new lens
            } else {
              newMapAtCursor.add(Lens(label, number.toInt()))
            }
          } else if (op == "-") {
            if (idx != -1) newMapAtCursor.removeAt(idx)
          }

          map[labelHashValue] = newMapAtCursor
        }
      })
    })

    var res = 0
    map.forEach(fun (key, value) {
      res += value.mapIndexed { idx, lens -> (key + 1) * (idx + 1) * lens.value }.sum()
    })
    return res
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day15_test")

  val part1TestOutput = part1(testInput)
  val part2TestOutput = part2(testInput)

  check(part1TestOutput == 1320)
  check(part2TestOutput == 145)

  val input = readInput("Day15")

  part1(input).println()
  part2(input).println()
}
