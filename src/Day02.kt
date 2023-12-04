fun main() {
  val condition = hashMapOf("red" to 12, "green" to 13, "blue" to 14)

  fun isPossible(value: HashMap<String, Int>): Boolean {
    return value.getOrDefault("red", 0) <= condition.getOrDefault("red", 0) && value.getOrDefault(
      "blue",
      0
    ) <= condition.getOrDefault("blue", 0) && value.getOrDefault("green", 0) <= condition.getOrDefault("green", 0)
  }

  fun part1(input: List<String>): Int {
    val gamesPlayed = hashMapOf<Int, ArrayList<HashMap<String, Int>>>()

    input.forEach(fun(item) {
      val (gameTitle, games) = item.split(":")
      val title = gameTitle.replace("Game ", "").toInt()

      gamesPlayed[title] = arrayListOf()

      val records = games.split(";")
      records.forEach(fun(record) {
        val i = record.trim().split(",")
        val values = hashMapOf<String, Int>()

        i.forEach(fun(item) {
          val (value, color) = item.trim().split(" ")
          if (values.contains(color)) values[color] = values[color]?.plus(value.toInt()) ?: 0
          values[color] = value.toInt()
        })

        gamesPlayed[title]?.add(values)
      })
    })

    var sum = 0
    gamesPlayed.forEach { (key, value) -> if (value.all { v -> isPossible(v) }) sum += key; }

    return sum
  }

  fun part2(input: List<String>): Int {
    val gamesPlayed = hashMapOf<Int, HashMap<String, Int>>()
    input.forEach(fun(line) {
      val (gameTitle, games) = line.replace(Regex("Game ", RegexOption.IGNORE_CASE), "").split(": ")
      val title = gameTitle.toInt()

      gamesPlayed[title] = hashMapOf()
      val records = games.split(";")
      records.forEach(fun(record) {
        val i = record.trim().split(",")

        i.forEach(fun(cur) {
          val (value, color) = cur.trim().split(" ")
          if (gamesPlayed[title]!![color] != null) {
            if (gamesPlayed[title]!!.getOrDefault(color, 0) < value.toInt()) {
              gamesPlayed[title]?.set(color, value.toInt())
            }
          } else {
            gamesPlayed[title]?.set(color, value.toInt())
          }
        })

      })
    })

    var sum = 0
    gamesPlayed.forEach(fun(_, games) {
      var ans = 0

      games.toList().forEachIndexed(fun(idx, value) {
        if (idx == 0) ans = value.second
        else ans *= value.second
      })

      sum += ans
    })

    return sum
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day02_test")
  val part1TestOutput = part1(testInput)
  check(part1TestOutput == 8)

  val part2TestOutput = part2(testInput)
  check(part2TestOutput == 2286)

  val input = readInput("Day02")
  part1(input).println()
  part2(input).println()
}