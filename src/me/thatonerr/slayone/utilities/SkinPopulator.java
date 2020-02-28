package me.thatonerr.slayone.utilities;

import me.thatonerr.slayone.framework.Animation;
import me.thatonerr.slayone.info.Skin;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static me.thatonerr.slayone.utilities.Utils.getImageResource;

public class SkinPopulator {

    public static void populate() {
        addSkinFromFolder("default", "Default");
    }

    private static void addSkinFromFolder(String name, String skinName) {
        Animation downHeadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/down.png")));
        Animation downLeftHeadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/downleft.png")));
        Animation leftHeadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/left.png")));
        Animation upLeftHeadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/upleft.png")));
        Animation upHeadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/up.png")));
        Animation upRightHeadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/upright.png")));
        Animation rightHeadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/right.png")));
        Animation downRightHeadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/downright.png")));
        Animation downHeadReloadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/downreload.png")));
        Animation downLeftHeadReloadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/downleftreload.png")));
        Animation leftHeadReloadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/leftreload.png")));
        Animation upLeftHeadReloadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/upleftreload.png")));
        Animation upHeadReloadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/upreload.png")));
        Animation upRightHeadReloadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/uprightreload.png")));
        Animation rightHeadReloadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/rightreload.png")));
        Animation downRightHeadReloadAnim = new Animation(4, getHeadFrames(getImageResource("/sprites/skins/" + name + "/head/downrightreload.png")));
        Animation downBodyAnim = new Animation(4, getBodyFrames(getImageResource("/sprites/skins/" + name + "/body/down.png")));
        Animation downLeftBodyAnim = new Animation(4, getBodyFrames(getImageResource("/sprites/skins/" + name + "/body/downleft.png")));
        Animation leftBodyAnim = new Animation(4, getBodyFrames(getImageResource("/sprites/skins/" + name + "/body/left.png")));
        Animation upLeftBodyAnim = new Animation(4, getBodyFrames(getImageResource("/sprites/skins/" + name + "/body/upleft.png")));
        Animation upBodyAnim = new Animation(4, getBodyFrames(getImageResource("/sprites/skins/" + name + "/body/up.png")));
        Animation upRightBodyAnim = new Animation(4, getBodyFrames(getImageResource("/sprites/skins/" + name + "/body/upright.png")));
        Animation rightBodyAnim = new Animation(4, getBodyFrames(getImageResource("/sprites/skins/" + name + "/body/right.png")));
        Animation downRightBodyAnim = new Animation(4, getBodyFrames(getImageResource("/sprites/skins/" + name + "/body/downright.png")));
        Animation leftArmAnim = new Animation(4, getArmFrames(getImageResource("/sprites/skins/" + name + "/arms/left_arm.png")));
        Animation rightArmAnim = new Animation(4, getArmFrames(getImageResource("/sprites/skins/" + name + "/arms/right_arm.png")));
        Skin.skins.put(skinName, new Skin(skinName, downHeadAnim, downLeftHeadAnim, leftHeadAnim, upLeftHeadAnim, upHeadAnim, upRightHeadAnim, rightHeadAnim, downRightHeadAnim, downHeadReloadAnim, downLeftHeadReloadAnim, leftHeadReloadAnim, upLeftHeadReloadAnim, upHeadReloadAnim, upRightHeadReloadAnim, rightHeadReloadAnim, downRightHeadReloadAnim, downBodyAnim, downLeftBodyAnim, leftBodyAnim, upLeftBodyAnim, upBodyAnim, upRightBodyAnim, rightBodyAnim, downRightBodyAnim, leftArmAnim, rightArmAnim));
    }

    private static List<BufferedImage> getHeadFrames(BufferedImage spriteSheet) {
        List<BufferedImage> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(spriteSheet.getSubimage(i * 60, 0, 60, 60));
        }
        return list;
    }

    private static List<BufferedImage> getBodyFrames(BufferedImage spriteSheet) {
        List<BufferedImage> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(spriteSheet.getSubimage(i * 17 + (i * 15), 0, 17, 10));
        }
        return list;
    }

    private static List<BufferedImage> getArmFrames(BufferedImage spriteSheet) {
        List<BufferedImage> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(spriteSheet.getSubimage(i * 18, 0, 18, 18));
        }
        return list;
    }

}
