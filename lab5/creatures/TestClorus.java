package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        c.replicate();
        assertEquals(1, c.energy(), 0.01);
    }

    @Test
    public void testAttack() {
        Clorus c = new Clorus(2);
        Plip p = new Plip(2);
        c.attack(p);
        assertEquals(4, c.energy(), 0.01);
    }

    @Test
    public void testChoose() {
        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Plip());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // Any Plips are seen, attack.
        HashMap<Direction, Occupant> hasPlips = new HashMap<Direction, Occupant>();
        hasPlips.put(Direction.TOP, new Empty());
        hasPlips.put(Direction.BOTTOM, new Plip());
        hasPlips.put(Direction.LEFT, new Impassible());
        hasPlips.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(hasPlips);
        expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        assertEquals(expected, actual);

        // energy >= 1, replicate.
        c = new Clorus(1);
        HashMap<Direction, Occupant> replicate = new HashMap<>();
        replicate.put(Direction.TOP, new Impassible());
        replicate.put(Direction.BOTTOM, new Impassible());
        replicate.put(Direction.LEFT, new Empty());
        replicate.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(replicate);
        expected = new Action(Action.ActionType.REPLICATE, Direction.LEFT);
        assertEquals(expected, actual);

        // move to random empty square.
        HashMap<Direction, Occupant> allEmpty = new HashMap<>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);

    }
}
