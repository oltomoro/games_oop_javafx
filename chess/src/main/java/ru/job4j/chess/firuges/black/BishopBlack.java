package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    /**
     * Позиция фигуры
     */
    private final Cell position;

    /**
     * Конструктор
     *
     * @param ps стартовая клетка фигуры
     */
    public BishopBlack(final Cell ps) {
        position = ps;
    }

    /**
     * Возвращает текущую позицию фигуры
     *
     * @return клетка в которой находится фигура
     */
    @Override
    public Cell position() {
        return position;
    }

    /**
     * Определяет все клетки которые пройдет фигура перед достижением конечной клетки
     *
     * @param dest конечная клетка
     * @return массив клеток которые пройдет фигура
     */
    @Override
    public Cell[] way(Cell dest) {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not move by diagonal from %s to %s", position, dest)
            );
        }
        int size = Math.abs(dest.getX() - position.getX());
        Cell[] steps = new Cell[size];
        int deltaX = dest.getX() - position.getX() > 0 ? 1 : -1;
        int deltaY = dest.getY() - position.getY() > 0 ? 1 : -1;
        int x = position.getX();
        int y = position.getY();
        for (int index = 0; index < size; index++) {
            x += deltaX;
            y += deltaY;
            steps[index] = Cell.findBy(x, y);
        }
        return steps;
    }

    /**
     * Проверяет является ли путь фигуры диагональю
     *
     * @param source начальная клетка пути
     * @param dest   конечная клетка пути
     * @return true - если является диагональю, false - если нет
     */
    public boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(dest.getX() - source.getX()) == Math.abs(source.getY() - dest.getY());
    }

    /**
     * Создает объект текущей фигуры в указанной клетке
     *
     * @param dest клетка где нужно создать новый объект
     * @return объект новой созданной фигуры
     */
    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
