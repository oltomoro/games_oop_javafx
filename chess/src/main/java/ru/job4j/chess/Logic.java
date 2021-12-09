package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

public final class Logic {
    /**
     * Массив всех фигур на поле
     */
    private final Figure[] figures = new Figure[32];

    /**
     * Индекс последней добавленной фигуры
     */
    private int index = 0;

    /**
     * Добавляет фигуру на поле
     * @param figure добавляемая фигура
     */
    public void add(Figure figure) {
        figures[index++] = figure;
    }

    /**
     * Перемещает фигуру из начальной позиции в конечную
     * @param source начальная позиция фигуры
     * @param dest конечная позиция фигуры
     * @throws FigureNotFoundException если фигура отсутствует на клетке
     * @throws ImpossibleMoveException если фигура перемещается не по правилам
     * @throws OccupiedCellException если фигура перемещается на занятую клетку
     */
    public void move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        int index = findBy(source);
        Cell[] steps = figures[index].way(dest);
        free(steps);
        figures[index] = figures[index].copy(dest);
    }

    /**
     * Проверяет что на пути к конечной точке следования нет других фигур
     *
     * @param steps клетки которые нужно пройти перед достижением конечной клетки
     * @return true если путь свободен, false если клетки на пути заняты другими фигурами
     * @throws OccupiedCellException
     */
    private boolean free(Cell[] steps) throws OccupiedCellException {
        boolean result = false;
        for (Cell cell : steps) {
            try {
                findBy(cell);
                throw new OccupiedCellException();
            } catch (FigureNotFoundException e) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Очищает игровое поле
     */
    public void clean() {
        Arrays.fill(figures, null);
        index = 0;
    }

    /**
     * Проверяет есть ли фигура в указанной клетке
     * @param cell клетка в которой выполняется поиск
     * @return индекс фигуры, если она найдена
     * @throws FigureNotFoundException если фигура не найдена
     */
    private int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != figures.length; index++) {
            Figure figure = figures[index];
            if (figure != null && figure.position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException();
    }
}
