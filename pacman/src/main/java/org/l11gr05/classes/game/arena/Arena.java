package org.l11gr05.classes.game.arena;

import org.l11gr05.classes.game.elements.PacDot;
import org.l11gr05.classes.game.elements.Pacman;
import org.l11gr05.classes.game.elements.Position;
import org.l11gr05.classes.game.elements.PowerPellet;
import org.l11gr05.classes.game.elements.ghost.*;

import java.util.ArrayList;
import java.util.List;

public class Arena implements IArenaObservable {
    private int height;
    private int width;

    private Pacman pacman;

    private Blinky blinky;
    private Inky inky;
    private Pinky pinky;
    private Clyde clyde;

    List<Wall> walls = new ArrayList<Wall>();
    List<PacDot> pacDots = new ArrayList<PacDot>();
    List<PowerPellet> powerPellets = new ArrayList<PowerPellet>();

    public Blinky getBlinky() {
        return this.blinky;
    }

    public Inky getInky() {
        return this.inky;
    }

    public Pinky getPinky() {
        return this.pinky;
    }

    public Clyde getClyde() {
        return this.clyde;
    }

    public void setBlinky(Blinky blinky) {
        this.blinky = blinky;
    }

    public void setInky(Inky inky) {
        this.inky = inky;
    }

    public void setPinky(Pinky pinky) {
        this.pinky = pinky;
    }

    public void setClyde(Clyde clyde) {
        this.clyde = clyde;
    }

    public List<PacDot> getPacDots() {
        return this.pacDots;
    }

    public Pacman getPacman() {
        return this.pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public Arena() {
        this.width = 0;
        this.height = 0;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public void addPacDot(PacDot pacDot) {
        pacDots.add(pacDot);
    }

    public List<Ghost> getGhosts() {
        List<Ghost> temp = new ArrayList<>();
        temp.add(this.blinky);
        temp.add(this.clyde);
        temp.add(this.inky);
        temp.add(this.pinky);
        return temp;
    }

    public void addPowerPellet(PowerPellet powerPellet) {
        powerPellets.add(powerPellet);
    }

    public int getHeight() {
        return this.height;
    }

    public List<Wall> getWalls() {
        return this.walls;
    }

    public List<PowerPellet> getPowerPellets() {
        return this.powerPellets;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }

    @Override
    public void powerPelletEaten() {
        this.blinky.powerPelletEaten();
        this.inky.powerPelletEaten();
        this.clyde.powerPelletEaten();
        this.pinky.powerPelletEaten();
    }

    public void pacDotRemove(Position position) {
        for (int i = 0; i < this.pacDots.size(); i++) {
            if (pacDots.get(i).getPosition().equals(position)) {
                this.pacDots.remove(pacDots.get(i));
                this.getPacman().increaseScorePacDot();
                break;
            }
        }
    }

    public void powerPelletRemove(Position position) {
        for (int i = 0; i < this.powerPellets.size(); i++) {
            if (powerPellets.get(i).getPosition().equals(position)) {
                this.powerPellets.remove(powerPellets.get(i));
                powerPelletEaten();
                this.getPacman().increaseScorePowerPellet();
                break;
            }
        }
    }

    public Ghost isGhost(Position position) {
        if (this.blinky.getPosition().equals(position)){
            this.blinky.pacManCollision();
            return this.blinky;
        }
        else if (this.inky.getPosition().equals(position)) {
            this.inky.pacManCollision();
            return this.inky;
        }
        else if (this.clyde.getPosition().equals(position)) {
            this.clyde.pacManCollision();
            return this.clyde;
        }
        else if (this.pinky.getPosition().equals(position)) {
            this.pinky.pacManCollision();
            return this.pinky;
        }
        else return null;
    }
}