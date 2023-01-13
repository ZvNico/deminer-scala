import MatrixUtils.{get_neighbors, init_game}

class MineSweeper(width: Int, height: Int, nMines: Int) {
  val grid: Array[Array[Case]] = init_game(width, height, nMines)
  var nNonRevealed: Int = width * height - nMines


  def interact(i: Int, j: Int): Boolean = {
    val case_ = grid(j)(i)
    if (case_.isInstanceOf[Mine]) return false
    reveal(i, j)
    true
  }

  def reveal(i: Int, j: Int): Unit = {
    val case_ = grid(j)(i)
    if (case_.isInstanceOf[Mine]) return
    if (case_.asInstanceOf[Empty].isRevealed) return

    case_.asInstanceOf[Empty].isRevealed = true
    nNonRevealed -= 1
    if (case_.asInstanceOf[Empty].n == 0)
      get_neighbors(grid, i, j).foreach(neighbor => reveal(neighbor._1, neighbor._2))
  }

  def is_not_revealed(i: Int, j: Int): Boolean = {
    val case_ = grid(j)(i)
    if (case_.isInstanceOf[Empty]) return !case_.asInstanceOf[Empty].isRevealed
    true
  }

  def display(): Unit = {
    print(this)
  }

  override def toString: String = {
    var res = "-" * (2 * width + 1) + "\n"
    for (line <- grid) {
      for (cell <- line) {
        res += s"|$cell"
      }
      res += "|\n"
      res += "-" * (2 * width + 1) + "\n"
    }
    res
  }
}


