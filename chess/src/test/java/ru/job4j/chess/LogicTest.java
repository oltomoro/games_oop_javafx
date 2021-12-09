package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

public class LogicTest {
    @Test(expected = OccupiedCellException.class)
    public void whenMoveAndWayIsBlockedThenThrowExeption()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.D4));
        logic.add(new BishopBlack(Cell.E5));
        logic.move(Cell.D4, Cell.H8);
    }

    @Test(expected = FigureNotFoundException.class)
    public void whenMoveAndFigureNotFoundThenThrowExeption()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.D4));
        logic.move(Cell.D3, Cell.H8);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveAndImpossibleWayThrowExeption()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.D4));
        logic.move(Cell.D4, Cell.E4);
    }
}