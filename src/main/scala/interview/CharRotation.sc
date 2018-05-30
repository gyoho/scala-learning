val base: Int = 'a'.toInt
val length: Int = 26


def cipher(str: String, step: Int): String = {
  // require(step > 0)
  // abs
  str.toList.map { l =>
    if (!l.isLetter) {
      l
    } else {
      (((l.toInt - base + step) % length) + base).toChar
    }
  }.mkString
}

assert(cipher("hello world", 13) == "")
cipher("zendesk, beautifully simple.", 13)
