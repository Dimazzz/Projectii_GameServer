package org.projii.serverside.gs;


import org.projii.commons.spaceship.Spaceship;
import org.projii.commons.spaceship.weapon.Projectile;

import java.util.ArrayList;

public class GameState {
    private ArrayList<Spaceship> spaceships;
    private ArrayList<Projectile> projectiles;
    public GameState(){}
    public  GameState( ArrayList<Spaceship> spaceships,ArrayList<Projectile> projectiles)
    {
        this.setProjectiles(projectiles);
        this.setSpaceships(spaceships);
    }

    public ArrayList<Spaceship> getSpaceships() {
        return spaceships;
    }

    public void setSpaceships(ArrayList<Spaceship> spaceships) {
        this.spaceships = spaceships;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }
}
