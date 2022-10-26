package tictactoe

fun main() {

    var step = "X"
    val str = "         "
    var result = ""
    var countX: Int = 0
    var countO: Int = 0
    val grid = mutableListOf(
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' ')
    )
    printGrid(grid)

    while(result == "") {
        val xy = readln()
        val x0 = xy[0]
        val y0 = xy[2]
        if (x0.isDigit() && y0.isDigit()) {
            val x = x0.toString().toInt()
            val y = y0.toString().toInt()
            if (x <= 3 && y <= 3 && grid[x-1][y-1] != 'X' && grid[x-1][y-1] != 'O') {

                if (step == "X") {
                    grid[x-1][y-1] = 'X'
                    printGrid(grid)
                    step = "O"
                } else {
                    grid[x-1][y-1] = 'O'
                    printGrid(grid)
                    step = "X"
                }
                //проверки
                //подсчет Х и О
                for (i in str) {
                    if (i=='X') {
                        countX++
                    }
                    if (i=='O') {
                        countO++
                    }
                }
                // кто победил?
                val winLines = "horizontal ${grid[0][0]}${grid[0][1]}${grid[0][2]} ${grid[1][0]}${grid[1][1]}${grid[1][2]} ${grid[2][0]}${grid[2][1]}${grid[2][2]} " +
                        "vertical   ${grid[0][0]}${grid[1][0]}${grid[2][0]} ${grid[0][1]}${grid[1][1]}${grid[2][1]} ${grid[0][2]}${grid[1][2]}${grid[2][2]} " +
                        "diagonal   ${grid[0][0]}${grid[1][1]}${grid[2][2]} ${grid[0][2]}${grid[1][1]}${grid[2][0]}"

                val Xwins = winLines.contains("XXX")
                val Owins = winLines.contains("OOO")

                when {
                    countX - countO !in -1..1 || Xwins && Owins -> result = "Impossible"
                    Xwins -> result = "X wins"
                    Owins -> result = "O wins"
                    !grid[0].contains(' ') && !grid[1].contains(' ') && !grid[2].contains(' ') -> result = "Draw"
                    //else -> println("Game not finished")
                }
            //конец проверок
            }
            else {
                when {
                    x > 3 || y > 3 -> println("Coordinates should be from 1 to 3!")
                    grid[x-1][y-1] == 'X' || grid[x-1][y-1] == 'O' -> println("This cell is occupied! Choose another one!")
                }
            }
        } else {
            println("You should enter numbers!")
        }
    }
    println(result)
}

fun printGrid(grid: MutableList<MutableList<Char>>) {
    println("---------")
    println("| ${grid[0].joinToString(" ")} |")
    println("| ${grid[1].joinToString(" ")} |")
    println("| ${grid[2].joinToString(" ")} |")
    println("---------")
}