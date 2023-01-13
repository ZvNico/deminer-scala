import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object MatrixUtils {
  def get_dimension[T](m: Array[Array[T]]): (Int, Int) = {
    (m(0).length, m.length)
  }

  def is_inside[T](m: Array[Array[T]], i: Int, j: Int): Boolean = {
    val (width, height) = get_dimension(m)
    (0 <= i && i < width) && (0 <= j && j < height)
  }

  def get_neighbors[T](m: Array[Array[T]], i: Int, j: Int): Array[(Int, Int)] = {
    val neighbors = new ArrayBuffer[(Int, Int)]()
    for (y <- -1 to 1) {
      for (x <- -1 to 1 if x != 0 || y != 0) {
        val pos = (i + x, j + y)
        if (is_inside(m, pos._1, pos._2)) {
          neighbors += pos
        }
      }
    }
    neighbors.toArray
  }

  def random_coords[T](m: Array[Array[T]]): (Int, Int) = {
    val (width, height) = get_dimension(m)
    val r = new Random
    (r.nextInt(width), r.nextInt(height))
  }

  def incr_tab(m: Array[Array[Int]], i: Int, j: Int): Unit = {
    if (m(j)(i)!= -1) m(j)(i) += 1
  }

  def random_mine(m: Array[Array[Int]]): (Int, Int) = {
    while (true) {
      val (x, y) = random_coords(m)
      if (m(y)(x) != -1) {
        m(y)(x) = -1
        return (x, y)
      }
    }
    (-1, -1)
  }

  def init_case(k: Int): Case = {
    if (k == -1) return new Mine
    new Empty(k)
  }

  def init_game(w: Int, h: Int, n: Int): Array[Array[Case]] = {
    val intMatrix = Array.ofDim[Int](h, w)
    var minesToPlace = n;
    while (minesToPlace > 0) {
      val (x, y) = random_mine(intMatrix)
      for (cell <- get_neighbors(intMatrix, x, y)) {
        incr_tab(intMatrix, cell._1, cell._2)
      }
      minesToPlace -= 1
    }

    val caseMatrix = Array.ofDim[Case](h, w)
    for (j <- 0 until h) {
      for (i <- 0 until w) {
        caseMatrix(j)(i) = init_case(intMatrix(j)(i))
      }
    }
    caseMatrix
  }
}
