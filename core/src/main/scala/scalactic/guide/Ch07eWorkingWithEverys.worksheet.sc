import org.scalactic._

One(1)
Many(1, 3)
Many(1, 2, 3)

Every(1)
Every(1, 2)
Every(1, 2, 3)

Many(1, 2, 3).map(_ + 1)
One(1).map(_ + 1)
Every(1, 2, 3).containsSlice(Every(2, 3))
Every(1, 2, 3).containsSlice(Every(3, 4))
Every(-1, -2, 3, 4, 5).minBy(_.abs)

Every(1, 2, 3).filter(_ < 10) // Result: Vector(1, 2, 3)
Every(1, 2, 3).filter(_ > 10) // Result: Vector()

for (i <- Every(1, 2, 3)) yield i + 1
for (i <- Every(1, 2, 3) if i < 10) yield i + 1

for {
  i <- Every(1, 2, 3)
  j <- Every('a', 'b', 'c')
} yield (i, j)

for {
  i <- Every(1, 2, 3) if i < 10
  j <- Every('a', 'b', 'c')
} yield (i, j)
