package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;
import huglife.HugLifeUtils;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Random;

public class Clorus extends Creature{
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();

    }

    public void move() {
        if(energy < 0.03) {
            energy = 0;
        } else {
            energy -= 0.03;
        }

    }

    public void stay() {
        if(energy < 0.01) {
            energy = 0;
        } else {
            energy -= 0.01;
        }
    }

    public Clorus replicate() {
        this.energy /= 2;
        Clorus r = new Clorus(this.energy);
        return this;
    }

    // Direction key:  TOP, BOTTOM, RIGHT, LEFT
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipsDirection = new ArrayDeque<>();
        boolean anyPlips = false;
        for(Direction key : neighbors.keySet()) {
            Occupant value = neighbors.get(key);
            if(value.name().equals("empty")) {
                emptyNeighbors.add(key);
            }
            if(value.name().equals("plip")) {
                anyPlips = true;
                plipsDirection.add(key);
            }
        }

        // if no empty square, stay
        if(emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        if(anyPlips) {
            return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(plipsDirection));
        }

        if(energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        }

        return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
    }

}
