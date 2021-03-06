package com.kunal.PlayGround.LevelsObstacles.BreakableCandyBars;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;

import java.util.LinkedList;

public class BreakableCandyBarVariables {
    public short length = 0;
    public short type = 0;
    public short angle = 0;
    /*
    type :
        1 - chocolate
        2 - green
        3 - pink
        4 - red
     */
    public LinkedList<Joint> joints;
    public LinkedList<Body> body;
    public int originX, originY;
    public short size=10;
    public boolean contactHappend = false;
    public boolean broken = false;

    public Sprite midPart, endPart;

}
