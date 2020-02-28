package me.thatonerr.slayone.utilities;

import me.thatonerr.slayone.guns.LaserPistol;
import me.thatonerr.slayone.projectiles.LaserBullet;

public class GunPopulator {

    public static void populateSprites() {
        LaserPistol.getSprites();

        LaserBullet.getSprites();
    }

}
