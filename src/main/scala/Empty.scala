class Empty(var n: Int) extends Case {
  var isRevealed = false

  override def toString: String = if (isRevealed) n.toString else " "
}
