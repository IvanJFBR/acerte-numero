package studiosol.acerteonumero.type

enum class GameStatus(val value: Int) {
    Normal(0),
    Higher(1),
    Lower(2),
    Right(3),
    Error(4);
}