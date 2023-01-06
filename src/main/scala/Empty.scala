class Empty(n: Int) extends Case {
  var isRevealed = false
  val nAdjacentMines = n

  override def toString: String = if (isRevealed) nAdjacentMines.toString else " "
}
