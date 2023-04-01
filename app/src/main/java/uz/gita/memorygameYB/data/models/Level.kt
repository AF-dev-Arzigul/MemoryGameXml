package uz.gita.memorygameYB.data.models

enum class Level(val x: Int, val y: Int, val time: Long , val showTime : Long) {
    EASY(4, 3, 60_000,3000),
    MEDIUM(5, 4, 200_000,5000),
    HARD(6, 5, 300_000,7000)
}