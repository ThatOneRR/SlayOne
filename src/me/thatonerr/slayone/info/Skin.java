package me.thatonerr.slayone.info;

import me.thatonerr.slayone.framework.Animation;

import java.util.*;

public class Skin {

    public static final Map<String, Skin> skins = new HashMap<>();
    private String name;
    private List<Animation> headAnims, headReloadAnims, bodyAnims;
    private Animation downHeadAnim, downLeftHeadAnim, leftHeadAnim, upLeftHeadAnim, upHeadAnim, upRightHeadAnim, rightHeadAnim, downRightHeadAnim, downHeadReloadAnim, downLeftHeadReloadAnim, leftHeadReloadAnim, upLeftHeadReloadAnim, upHeadReloadAnim, upRightHeadReloadAnim, rightHeadReloadAnim, downRightHeadReloadAnim, downBodyAnim, downLeftBodyAnim, leftBodyAnim, upLeftBodyAnim, upBodyAnim, upRightBodyAnim, rightBodyAnim, downRightBodyAnim, leftHandAnim, rightHandAnim;

    public Skin(String name, Animation downHeadAnim, Animation downLeftHeadAnim, Animation leftHeadAnim, Animation upLeftHeadAnim, Animation upHeadAnim, Animation upRightHeadAnim, Animation rightHeadAnim, Animation downRightHeadAnim, Animation downHeadReloadAnim, Animation downLeftHeadReloadAnim, Animation leftHeadReloadAnim, Animation upLeftHeadReloadAnim, Animation upHeadReloadAnim, Animation upRightHeadReloadAnim, Animation rightHeadReloadAnim, Animation downRightHeadReloadAnim, Animation downBodyAnim, Animation downLeftBodyAnim, Animation leftBodyAnim, Animation upLeftBodyAnim, Animation upBodyAnim, Animation upRightBodyAnim, Animation rightBodyAnim, Animation downRightBodyAnim, Animation leftHandAnim, Animation rightHandAnim) {
        this.name = name;
        this.downHeadAnim = downHeadAnim;
        this.downLeftHeadAnim = downLeftHeadAnim;
        this.leftHeadAnim = leftHeadAnim;
        this.upLeftHeadAnim = upLeftHeadAnim;
        this.upHeadAnim = upHeadAnim;
        this.upRightHeadAnim = upRightHeadAnim;
        this.rightHeadAnim = rightHeadAnim;
        this.downRightHeadAnim = downRightHeadAnim;
        this.downHeadReloadAnim = downHeadReloadAnim;
        this.downLeftHeadReloadAnim = downLeftHeadReloadAnim;
        this.leftHeadReloadAnim = leftHeadReloadAnim;
        this.upLeftHeadReloadAnim = upLeftHeadReloadAnim;
        this.upHeadReloadAnim = upHeadReloadAnim;
        this.upRightHeadReloadAnim = upRightHeadReloadAnim;
        this.rightHeadReloadAnim = rightHeadReloadAnim;
        this.downRightHeadReloadAnim = downRightHeadReloadAnim;
        this.downBodyAnim = downBodyAnim;
        this.downLeftBodyAnim = downLeftBodyAnim;
        this.leftBodyAnim = leftBodyAnim;
        this.upLeftBodyAnim = upLeftBodyAnim;
        this.upBodyAnim = upBodyAnim;
        this.upRightBodyAnim = upRightBodyAnim;
        this.rightBodyAnim = rightBodyAnim;
        this.downRightBodyAnim = downRightBodyAnim;
        this.leftHandAnim = leftHandAnim;
        this.rightHandAnim = rightHandAnim;
        headAnims = Arrays.asList(downHeadAnim, downLeftHeadAnim, leftHeadAnim, upLeftHeadAnim, upHeadAnim, upRightHeadAnim, rightHeadAnim, downRightHeadAnim);
        headReloadAnims = Arrays.asList(downHeadReloadAnim, downLeftHeadReloadAnim, leftHeadReloadAnim, upLeftHeadReloadAnim, upHeadReloadAnim, upRightHeadReloadAnim, rightHeadReloadAnim, downRightHeadReloadAnim);
        bodyAnims = Arrays.asList(downBodyAnim, downLeftBodyAnim, leftBodyAnim, upLeftBodyAnim, upBodyAnim, upRightBodyAnim, rightBodyAnim, downRightBodyAnim);
    }

    public List<Animation> getHeadAnims() {
        return headAnims;
    }

    public List<Animation> getHeadReloadAnims() {
        return headReloadAnims;
    }

    public List<Animation> getBodyAnims() {
        return bodyAnims;
    }

    public Animation getLeftHandAnim() {
        return leftHandAnim;
    }

    public Animation getRightHandAnim() {
        return rightHandAnim;
    }

}
