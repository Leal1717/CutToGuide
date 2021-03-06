package com.kunal.PlayGround.LevelsObstacles.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kunal.AllVariables;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.utils.BodyGenerator;

public class Snake {

    TextureAtlas atlas;
    Sprite img;
    TextureRegion region;
    boolean img1 = true;
    long timer;
    long upDownCounter;
    boolean goingUp = true;
    int deltaUp = 2,upchecker = 0;

    public Snake (World world) {

        atlas = new TextureAtlas(Gdx.files.internal("playArea/LevelObstacles/snake/pack.atlas"));
        region = atlas.findRegion("snakeSlime");
        img = new Sprite(region);

        for (int i=0; i< VariablesForPlayArea.snakes.size(); i++){
            VariablesForPlayArea.snakes.get(i).isDead = false;

            VariablesForPlayArea.snakes.get(i).hitBox = BodyGenerator.BodyAssemble(world, true, "Evil",
                    new Vector2(VariablesForPlayArea.snakes.get(i).x, VariablesForPlayArea.snakes.get(i).y),
                    new Vector2(20,60),0,1,AllVariables.Bit_enimes,
                    (short)(AllVariables.Bit_Bicycle | AllVariables.Bit_Tool | AllVariables.Bit_enimes));



        }

        timer = System.currentTimeMillis();
        upDownCounter = System.currentTimeMillis();
    }

    public void render () {
        for (int i =0; i<VariablesForPlayArea.snakes.size(); i++){
            img.setPosition(VariablesForPlayArea.snakes.get(i).x, VariablesForPlayArea.snakes.get(i).y);

            VariablesForPlayArea.snakes.get(i).hitBox.setTransform((VariablesForPlayArea.snakes.get(i).x+27)/AllVariables.PPM,
                    (VariablesForPlayArea.snakes.get(i).y+70)/AllVariables.PPM, 0);


            if (VariablesForPlayArea.snakes.get(i).is180rot){
                img.setRotation(180);
            }else {
                img.setRotation(0);
            }
            if (VariablesForPlayArea.snakes.get(i).isDead) {
                img.setRegion(atlas.findRegion("snakeSlime_dead"));
            }else {
                if (img1){
                    img.setRegion(atlas.findRegion("snakeSlime"));
                }else {
                    img.setRegion(atlas.findRegion("snakeSlime_ani"));
                }
            }
            img.draw(AllVariables.batch);

        }
    }

    public void update () {
        //change texture------------------------------------------
        if (System.currentTimeMillis() - timer >= 300){
            img1 = !img1;
            timer = System.currentTimeMillis();
        }
        //--------------------------------------------------------

        //moving up down
        if (System.currentTimeMillis() - upDownCounter >= 2){
            upchecker += deltaUp;
            upDownCounter = System.currentTimeMillis();
            for (int i=0; i<VariablesForPlayArea.snakes.size(); i++) {

                //up going
                if (goingUp) {
                    VariablesForPlayArea.snakes.get(i).y += deltaUp;
                } else {
                    VariablesForPlayArea.snakes.get(i).y -= deltaUp;
                }
            }
        }


        if (upchecker>=140) {
            upchecker = 0;
            goingUp = !goingUp;
        }



    }

}
