import MatrixUtils.is_inside

import scala.io.StdIn.readLine

object Main {
  def main(args: Array[String]): Unit = {
    val mineSweeper = new MineSweeper(6, 4, 5);
    var isMine = false
    do {
      var x = -1
      var y = -1
      mineSweeper.display()
      do {
        println("Saisir les coordonnées de la case à réveler tel que x y")
        val input = readLine().split(" ").map(_.toInt)
        x = input(0)
        y = input(1)
      } while (!is_inside(mineSweeper.grid, x, y) || !mineSweeper.is_not_revealed(x, y))
      isMine = !mineSweeper.interact(x, y)
      if (isMine) println("PERDU !")
      if (mineSweeper.nNonRevealed == 0) println("GAGNE !")
    } while (mineSweeper.nNonRevealed > 0 && !isMine)
    mineSweeper.display()
  }
}

