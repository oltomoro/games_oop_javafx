package ru.job4j.chess;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.junit.Assert.*;

public class BishopBlackTest {
    @Test
    public void whenCreateThenPositionIsValid() {
        Cell startPos = Cell.C1;
        Figure bb = new BishopBlack(startPos);
        Assert.assertEquals("Текущая позиция фигуры не равна позиции указанной при инициализации", bb.position(), startPos);
    }

    @Test
    public void whenCopyThenPositionIsValid() {
        Cell startPos = Cell.C1;
        Cell destPos = Cell.G5;
        Figure bb = new BishopBlack(startPos);
        Figure bbCopy = bb.copy(destPos);
        Assert.assertEquals("Неверная позиция при копировании фигуры",
                bbCopy.position(), destPos);
    }

    @Test
    public void whenGoRightUpThenWayContainsAllCells() {
        Cell startPos = Cell.D4;
        Cell destPos = Cell.H8;
        Figure bb = new BishopBlack(startPos);
        Cell[] resultWay = bb.way(destPos);
        assertThat(resultWay, arrayContainingInAnyOrder(Cell.E5, Cell.F6, Cell.G7, Cell.H8));
        assertEquals(resultWay.length, 4);
    }

    @Test
    public void whenGoLeftUpThenWayContainsAllCells() {
        Cell startPos = Cell.D4;
        Cell destPos = Cell.A7;
        Figure bb = new BishopBlack(startPos);
        Cell[] resultWay = bb.way(destPos);
        assertThat(resultWay, arrayContainingInAnyOrder(Cell.C5, Cell.B6, Cell.A7));
        assertEquals(resultWay.length, 3);
    }

    @Test
    public void whenGoRightDownThenWayContainsAllCells() {
        Cell startPos = Cell.D4;
        Cell destPos = Cell.G1;
        Figure bb = new BishopBlack(startPos);
        Cell[] resultWay = bb.way(destPos);
        assertThat(resultWay, arrayContainingInAnyOrder(Cell.E3, Cell.F2, Cell.G1));
        assertEquals(resultWay.length, 3);
    }

    @Test
    public void whenGoLeftDownThenWayContainsAllCells() {
        Cell startPos = Cell.D4;
        Cell destPos = Cell.A1;
        Figure bb = new BishopBlack(startPos);
        Cell[] resultWay = bb.way(destPos);
        assertThat(resultWay, arrayContainingInAnyOrder(Cell.C3, Cell.B2, Cell.A1));
        assertEquals(resultWay.length, 3);
    }

    @Test
    public void whenIsDiagonalThenReturnTrue() {
        Cell startPos = Cell.D4;
        Cell destPos1 = Cell.H8;
        Cell destPos2 = Cell.G1;
        Cell destPos3 = Cell.A1;
        Cell destPos4 = Cell.A7;
        BishopBlack bb = new BishopBlack(startPos);
        assertTrue(String.format("Направление %s - %s не является диагональю", startPos, destPos1),
                bb.isDiagonal(startPos, destPos1));
        assertTrue(String.format("Направление %s - %s не является диагональю", startPos, destPos2),
                bb.isDiagonal(startPos, destPos2));
        assertTrue(String.format("Направление %s - %s не является диагональю", startPos, destPos3),
                bb.isDiagonal(startPos, destPos3));
        assertTrue(String.format("Направление %s - %s не является диагональю", startPos, destPos3),
                bb.isDiagonal(startPos, destPos4));
    }

    @Test
    public void whenIsNotDiagonalThenReturnFalse() {
        Cell startPos = Cell.D4;
        Cell destPos1 = Cell.D5;
        Cell destPos2 = Cell.E4;
        Cell destPos3 = Cell.C4;
        Cell destPos4 = Cell.D3;
        BishopBlack bb = new BishopBlack(startPos);
        assertFalse(String.format("Направление %s - %s является диагональю", startPos, destPos1),
                bb.isDiagonal(startPos, destPos1));
        assertFalse(String.format("Направление %s - %s является диагональю", startPos, destPos2),
                bb.isDiagonal(startPos, destPos2));
        assertFalse(String.format("Направление %s - %s является диагональю", startPos, destPos3),
                bb.isDiagonal(startPos, destPos3));
        assertFalse(String.format("Направление %s - %s является диагональю", startPos, destPos4),
                bb.isDiagonal(startPos, destPos4));
    }
}
