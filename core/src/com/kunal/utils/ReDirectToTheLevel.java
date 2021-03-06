package com.kunal.utils;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.kunal.AllVariables;
import com.kunal.MainGame;
import com.kunal.PlayGround.Area1.AreaOneClass;
import com.kunal.PlayGround.LevelsObstacles.BreakableCandyBars.BreakableCandyBar;
import com.kunal.PlayGround.LevelsObstacles.BreakableCandyBars.BreakableCandyBarVariables;
import com.kunal.PlayGround.LevelsObstacles.CreateHole.CreateHoleVariables;
import com.kunal.PlayGround.LevelsObstacles.DirectionReverse.DirectionReverseVariables;
import com.kunal.PlayGround.LevelsObstacles.Jumper.JumperVariables;
import com.kunal.PlayGround.LevelsObstacles.Roundcandy.RoundCandyVariables;
import com.kunal.PlayGround.LevelsObstacles.barnacle.BarnacleVariables;
import com.kunal.PlayGround.LevelsObstacles.dropingLolipop.DropingLolipopVariables;
import com.kunal.PlayGround.LevelsObstacles.droppingSpinkes.DroppingSpikeVariables;
import com.kunal.PlayGround.LevelsObstacles.droppingSpinkes.DroppingSpikes;
import com.kunal.PlayGround.LevelsObstacles.fireOnFloor.FireOnFloorVariables;
import com.kunal.PlayGround.LevelsObstacles.flappyBirdPipes.flappyBirdPipes;
import com.kunal.PlayGround.LevelsObstacles.flappyBirdPipes.flappyBirdPipesVariables;
import com.kunal.PlayGround.LevelsObstacles.fullSawThatRoams.FullSawVariables;
import com.kunal.PlayGround.LevelsObstacles.halfSaw.HalfSaw;
import com.kunal.PlayGround.LevelsObstacles.halfSaw.HalfSawVariables;
import com.kunal.PlayGround.LevelsObstacles.snake.SnakeVariables;
import com.kunal.PlayGround.LevelsObstacles.speedController.SpeedController;
import com.kunal.PlayGround.LevelsObstacles.speedController.SpeedControllerVariables;
import com.kunal.PlayGround.LevelsObstacles.spikes.Spikes;
import com.kunal.PlayGround.LevelsObstacles.spikes.SpikesVariables;
import com.kunal.PlayGround.Tutorial.InGameTutorial.BasicTutorial;
import com.kunal.PlayGround.TypeThreeArea.Type3Area;
import com.kunal.PlayGround.TypeTwoArea.TypeTwoArea;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.PlayGround.powerUpInInventory.PowerUpInInventoryVariables;

import java.util.LinkedList;

public class ReDirectToTheLevel {

    //0 area number and 0 level number is for the tutorial

    public ReDirectToTheLevel() {
    }

    public static void Direct(MainGame game, Boolean restarted) {

        flappyBirdPipesVariables fbPipes;
        //CreateHoleVariables createHole;
        JumperVariables jumper;
        HalfSawVariables halfSawVariables;
        FullSawVariables fullSawVariables;
        PowerUpInInventoryVariables powerupVar;
        SpeedControllerVariables speedctlrvar;
        DirectionReverseVariables dirRev;

        //candy world
        BreakableCandyBarVariables breakableCandyBar;
        RoundCandyVariables roundCandy;
        DropingLolipopVariables dropingLolipop;
        FireOnFloorVariables fire;
        SpikesVariables spikes;
        DroppingSpikeVariables droppingSpikes;
        SnakeVariables snakes;
        BarnacleVariables barnacle;

        if (!restarted) {
            //clear it all
            VariablesForPlayArea.powerUpList.clear();
            VariablesForPlayArea.flappyBirdPipesList.clear();
            VariablesForPlayArea.createHoleList.clear();
            VariablesForPlayArea.jumperList.clear();
            VariablesForPlayArea.halfSawList.clear();
            VariablesForPlayArea.fullSawList.clear();
            VariablesForPlayArea.speedCtrlList.clear();
            VariablesForPlayArea.dirRevList.clear();
            VariablesForPlayArea.HintOneEnabled = false;
            VariablesForPlayArea.HintTwoEnabled = false;

            //candy world
            VariablesForPlayArea.breakingCandyBar.clear();
            VariablesForPlayArea.roundCandies.clear();
            VariablesForPlayArea.dropingLolipop.clear();
            VariablesForPlayArea.fire.clear();
            VariablesForPlayArea.spike.clear();
            VariablesForPlayArea.dropingSpike.clear();
            VariablesForPlayArea.snakes.clear();
            VariablesForPlayArea.barnacle.clear();
        }

        VariablesForPlayArea.gameOver = false;

        //tutorial=============================================================
        if (AllVariables.PresentAreaNumber == 0 && AllVariables.PresentLevelNumber == 0) {
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/tut/tutorial.tmx";
            VariablesForPlayArea.endPoint.x = 1200+1000;
            VariablesForPlayArea.endPoint.y = 6000;

            game.setScreen(new BasicTutorial(game));
        }
        //=======================================================================

        // area 1 ----------------------------------------------------------------
        if (AllVariables.PresentAreaNumber == 1) {
            if (AllVariables.PresentLevelNumber == 1) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level1.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 5600;

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 2) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level2.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 5800;

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 3) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level3.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 5000;

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 4) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level4.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 6000;

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 5) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level5.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 6000;

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 6) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level6.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 6500;
                if (!restarted) {

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = true;
                    speedctlrvar.x = 720;
                    speedctlrvar.y = 520;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 7) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level7.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 8500;

                if (!restarted) {

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = true;
                    speedctlrvar.x = 3050;
                    speedctlrvar.y = 520;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 1000;
                    fullSawVariables.ypos = 572;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;

                    VariablesForPlayArea.fullSawList.add(fullSawVariables);


                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 8) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level8.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 8500;

                if (!restarted) {

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = true;
                    speedctlrvar.x = 900;
                    speedctlrvar.y = 520;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 4064;
                    fullSawVariables.ypos = 600;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 1200;
                    fullSawVariables.ypos = 572;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);


                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 8050;
                    fullSawVariables.ypos = 500;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);


                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 6500;
                    fullSawVariables.ypos = 520;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 6000;
                    fullSawVariables.ypos = 520;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 5000;
                    fullSawVariables.ypos = 520;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);


                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 9) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level9.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 8500;

                if (!restarted) {
                    halfSawVariables = new HalfSawVariables();
                    halfSawVariables.xdestination = 2400;
                    halfSawVariables.oneisSelected = true;
                    halfSawVariables.xorigin = 1700;
                    halfSawVariables.yorigin = 512;
                    VariablesForPlayArea.halfSawList.add(halfSawVariables);


                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 4;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 5000;
                    fullSawVariables.ypos = 500;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);


                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 2000;
                    fullSawVariables.ypos = 500;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);


                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 10) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level10.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 8500;

                if (!restarted) {
                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 1500;
                    fullSawVariables.ypos = 500;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 7600;
                    fullSawVariables.ypos = 700;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    dirRev = new DirectionReverseVariables();
                    dirRev.x = 300;
                    dirRev.y = 525;
                    dirRev.active = true;
                    VariablesForPlayArea.dirRevList.add(dirRev);


                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);


                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 3;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = false;
                    speedctlrvar.x = 7200;
                    speedctlrvar.y = 570;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = false;
                    speedctlrvar.x = 7300;
                    speedctlrvar.y = 570;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = false;
                    speedctlrvar.x = 7400;
                    speedctlrvar.y = 570;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = false;
                    speedctlrvar.x = 7500;
                    speedctlrvar.y = 570;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = false;
                    speedctlrvar.x = 7600;
                    speedctlrvar.y = 570;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = false;
                    speedctlrvar.x = 7700;
                    speedctlrvar.y = 570;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = false;
                    speedctlrvar.x = 7800;
                    speedctlrvar.y = 570;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);


                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 11) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level11.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 9200;

                if (!restarted) {
                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 1300;
                    fullSawVariables.ypos = 760;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 1350;
                    fullSawVariables.ypos = 1007;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 7500;
                    fullSawVariables.ypos = 550;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 4;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 12) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level12.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 9000;

                if (!restarted) {
                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 1900;
                    fullSawVariables.ypos = 800;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 2600;
                    fullSawVariables.ypos = 900;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);


                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 4;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 3160;
                    fullSawVariables.ypos = 700;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);


                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 13) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level13.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 8500;

                if (!restarted) {

                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 14) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level14.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 8500;

                if (!restarted) {
                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 630;
                    fullSawVariables.ypos = 1450;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    halfSawVariables = new HalfSawVariables();
                    halfSawVariables.xdestination = 7200;
                    halfSawVariables.oneisSelected = true;
                    halfSawVariables.xorigin = 6700;
                    halfSawVariables.yorigin = 320;
                    VariablesForPlayArea.halfSawList.add(halfSawVariables);

                    halfSawVariables = new HalfSawVariables();
                    halfSawVariables.xdestination = 8200;
                    halfSawVariables.oneisSelected = true;
                    halfSawVariables.xorigin = 7600;
                    halfSawVariables.yorigin = 320;
                    VariablesForPlayArea.halfSawList.add(halfSawVariables);


                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 15) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level15.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 8500;

                if (!restarted) {
                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 4;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 4;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    jumper = new JumperVariables();
                    jumper.x = 1350;
                    jumper.y = 512;
                    VariablesForPlayArea.jumperList.add(jumper);

                    jumper = new JumperVariables();
                    jumper.x = 3750;
                    jumper.y = 512;
                    VariablesForPlayArea.jumperList.add(jumper);

                    halfSawVariables = new HalfSawVariables();
                    halfSawVariables.xdestination = 4800;
                    halfSawVariables.oneisSelected = true;
                    halfSawVariables.xorigin = 4314;
                    halfSawVariables.yorigin = 736;
                    VariablesForPlayArea.halfSawList.add(halfSawVariables);

                    jumper = new JumperVariables();
                    jumper.x = 6187;
                    jumper.y = 480;
                    VariablesForPlayArea.jumperList.add(jumper);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 6990;
                    fullSawVariables.ypos = 900;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 7600;
                    fullSawVariables.ypos = 900;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);
                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 16) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level16.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 8500;

                if (!restarted) {
                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 3;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 2;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = true;
                    speedctlrvar.x = 2200;
                    speedctlrvar.y = 520;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 517;
                    fullSawVariables.ypos = 542;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    halfSawVariables = new HalfSawVariables();
                    halfSawVariables.xdestination = 2920;
                    halfSawVariables.oneisSelected = true;
                    halfSawVariables.xorigin = 2450;
                    halfSawVariables.yorigin = 512;
                    VariablesForPlayArea.halfSawList.add(halfSawVariables);

                    jumper = new JumperVariables();
                    jumper.x = 5827;
                    jumper.y = 380;
                    VariablesForPlayArea.jumperList.add(jumper);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 6957;
                    fullSawVariables.ypos = 547;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 17) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level17.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 8500;

                if (!restarted) {
                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 332;
                    fullSawVariables.ypos = 292;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);


                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = true;
                    speedctlrvar.x = 3350;
                    speedctlrvar.y = 890;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                    jumper = new JumperVariables();
                    jumper.x = 3390;
                    jumper.y = 192;
                    VariablesForPlayArea.jumperList.add(jumper);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 2;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    halfSawVariables = new HalfSawVariables();
                    halfSawVariables.xdestination = 4787;
                    halfSawVariables.oneisSelected = true;
                    halfSawVariables.xorigin = 4180;
                    halfSawVariables.yorigin = 512;
                    VariablesForPlayArea.halfSawList.add(halfSawVariables);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 7682;
                    fullSawVariables.ypos = 392;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 7000;
                    fullSawVariables.ypos = 392;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 6550;
                    fullSawVariables.ypos = 392;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 5890;
                    fullSawVariables.ypos = 392;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = true;
                    fullSawVariables.intialDirectionForward = true;
                    fullSawVariables.initialdead = true;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 18) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level18.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 8500;

                if (!restarted) {

                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 19) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level19.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 8500;

                if (!restarted) {
                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 1327;
                    fullSawVariables.ypos = 577;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = true;
                    speedctlrvar.x = 952;
                    speedctlrvar.y = 537;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                    fullSawVariables = new FullSawVariables();
                    fullSawVariables.xpos = 3797;
                    fullSawVariables.ypos = 1032;
                    fullSawVariables.size = 128;
                    fullSawVariables.forwardDirection = false;
                    fullSawVariables.intialDirectionForward = false;
                    fullSawVariables.initialdead = false;
                    VariablesForPlayArea.fullSawList.add(fullSawVariables);

                    halfSawVariables = new HalfSawVariables();
                    halfSawVariables.xdestination = 4800;
                    halfSawVariables.oneisSelected = true;
                    halfSawVariables.xorigin = 4300;
                    halfSawVariables.yorigin = 447;
                    VariablesForPlayArea.halfSawList.add(halfSawVariables);

                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = true;
                    speedctlrvar.x = 6100;
                    speedctlrvar.y = 307;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);

                    jumper = new JumperVariables();
                    jumper.x = 8500;
                    jumper.y = 857;
                    VariablesForPlayArea.jumperList.add(jumper);


                }

                game.setScreen(new TypeTwoArea(game, restarted));
            } else if (AllVariables.PresentLevelNumber == 20) {
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level20.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 8500;

                if (!restarted) {
                    speedctlrvar = new SpeedControllerVariables();
                    speedctlrvar.SpeedIncrementor = true;
                    speedctlrvar.x = 5237;
                    speedctlrvar.y = 587;
                    VariablesForPlayArea.speedCtrlList.add(speedctlrvar);
                }

                game.setScreen(new TypeTwoArea(game, restarted));
            }
        }

        //area 2-----------------------------------------------------------
        else if (AllVariables.PresentAreaNumber == 2) {
            if (AllVariables.PresentLevelNumber == 1) {
                //VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area2/Area2Level1.tmx";
                VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area2/Area2Level1.tmx";
                VariablesForPlayArea.endPoint.x = 1200;
                VariablesForPlayArea.endPoint.y = 5600;

                if (!restarted) {

                    powerupVar = new PowerUpInInventoryVariables();
                    powerupVar.TypeOfPower = 1;
                    VariablesForPlayArea.powerUpList.add(powerupVar);

                    snakes = new SnakeVariables();
                    snakes.x = 1000;
                    snakes.y =450-147;
                    snakes.is180rot = false;
                    VariablesForPlayArea.snakes.add(snakes);

                    snakes = new SnakeVariables();
                    snakes.x = 1500;
                    snakes.y =850-147;
                    snakes.is180rot = true;
                    VariablesForPlayArea.snakes.add(snakes);


                }

                VariablesForPlayArea.bulletsHave = 3;

                game.setScreen(new Type3Area(game, restarted));
            }

        }

        return;


        //area 1 end ----------------------------------------------------------------


    }
}

    //level basic template
    /*
        candy bar
        //breaking candy bars
        breakableCandyBar = new BreakableCandyBarVariables();
        breakableCandyBar.length = 11;
        breakableCandyBar.type = 1;
        breakableCandyBar.originX = 1700;
        breakableCandyBar.originY = 500;
        breakableCandyBar.size = 25;
        breakableCandyBar.angle = 0;
        VariablesForPlayArea.breakingCandyBar.add(breakableCandyBar);


        breakableCandyBar = new BreakableCandyBarVariables();
        breakableCandyBar.length = 14;
        breakableCandyBar.type = 4;
        breakableCandyBar.originX = 4122;
        breakableCandyBar.originY = 527;
        breakableCandyBar.size = 15;
        breakableCandyBar.angle = 60;
        VariablesForPlayArea.breakingCandyBar.add(breakableCandyBar);


//round candies
        roundCandy = new RoundCandyVariables();
        roundCandy.x = 1697;
        roundCandy.y = 700;
        roundCandy.baseSize = 10;
        roundCandy.count = 40;
        VariablesForPlayArea.roundCandies.add(roundCandy);


        roundCandy = new RoundCandyVariables();
        roundCandy.x = 1697;
        roundCandy.y = 500;
        roundCandy.baseSize = 15;
        roundCandy.count = 28;
        VariablesForPlayArea.roundCandies.add(roundCandy);


        roundCandy = new RoundCandyVariables();
        roundCandy.x = 1697;
        roundCandy.y = 900;
        roundCandy.baseSize = 20;
        roundCandy.count = 20;
        VariablesForPlayArea.roundCandies.add(roundCandy);

//droping lolipop
        dropingLolipop = new DropingLolipopVariables();
        dropingLolipop.x = 1500;
        dropingLolipop.y = 1050;
        dropingLolipop.stickLen = 50;
        dropingLolipop.stickWid = 5;
        dropingLolipop.candyRadius = 25;
        dropingLolipop.velToDrop = -200;
        dropingLolipop.type = 3;
        dropingLolipop.angle = 2;
        VariablesForPlayArea.dropingLolipop.add(dropingLolipop);

//fire

        fire = new FireOnFloorVariables();
        fire.x = 700;
        fire.y = 500;
        fire.type = 1;
        VariablesForPlayArea.fire.add(fire);

        fire = new FireOnFloorVariables();
        fire.x = 800;
        fire.y = 500;fire.type = 2;
        VariablesForPlayArea.fire.add(fire);

        fire = new FireOnFloorVariables();
        fire.x = 850;
        fire.y = 500;
        fire.type = 3;
        VariablesForPlayArea.fire.add(fire);

//constant spines
        spikes = new SpikesVariables();
        spikes.x = 900;
        spikes.y = 480;
        spikes.angle = 0;
        VariablesForPlayArea.spike.add(spikes);

        spikes = new SpikesVariables();
        spikes.x = 1300;
        spikes.y = 480;
        spikes.angle = 0;
        VariablesForPlayArea.spike.add(spikes);

        spikes = new SpikesVariables();
        spikes.x = 1900;
        spikes.y = 480;
        spikes.angle = 0;
        VariablesForPlayArea.spike.add(spikes);

//dropping spines
        droppingSpikes = new DroppingSpikeVariables();
        droppingSpikes.x = 2000;
        droppingSpikes.y = 1000;
        VariablesForPlayArea.dropingSpike.add(droppingSpikes);

//snakes
        snakes = new SnakeVariables();
        snakes.x = 1000;
        snakes.y =450-147;
        snakes.is180rot = true;
        VariablesForPlayArea.snakes.add(snakes);

//barnace
        barnacle = new BarnacleVariables();
        barnacle.x = 1000;
        barnacle.y =450-80;
        barnacle.is180rot = false;
        VariablesForPlayArea.barnacle.add(barnacle);

        barnacle = new BarnacleVariables();
        barnacle.x = 1500;
        barnacle.y =750-80;
        barnacle.is180rot = true;
        VariablesForPlayArea.barnacle.add(barnacle);


        barnacle = new BarnacleVariables();
        barnacle.x = 2000;
        barnacle.y =450-80;
        barnacle.is180rot = false;
        VariablesForPlayArea.barnacle.add(barnacle);





    */


    /*
    else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==10){
            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level10.tmx";
            VariablesForPlayArea.endPoint.x = 1200;
            VariablesForPlayArea.endPoint.y = 8500;

            if (!restarted) {

            }

            game.setScreen(new TypeTwoArea(game, restarted));
        }
     */

    //else if (AllVariables.PresentAreaNumber == 1 && AllVariables.PresentLevelNumber ==7){
    //            VariablesForPlayArea.LevelMapToBeLoaded = "playArea/tiledMap/area1/Area1Level7.tmx";
    //
    //            VariablesForPlayArea.endPoint.x = 600;
    //            VariablesForPlayArea.endPoint.y = 7400;
    //            if (!restarted) {
    //
    //                /*
    //                // flappy bird type pipes
    //                fbPipes.x =1500;
    //                fbPipes.y = 500;
    //                fbPipes.gapeHorizontalLength = 64;
    //                fbPipes.gapVerticalLength = 400;
    //                fbPipes.gapStartFrombottom = 160;
    //                VariablesForPlayArea.flappyBirdPipesList.add(fbPipes);
    //
    //                fbPipes = new flappyBirdPipesVariables();
    //                fbPipes.x = 4000;
    //                fbPipes.y = 500;
    //                fbPipes.gapeHorizontalLength = 128;
    //                fbPipes.gapVerticalLength = 200;
    //                fbPipes.gapStartFrombottom = 32;
    //                VariablesForPlayArea.flappyBirdPipesList.add(fbPipes);
    //
    //                fbPipes = new flappyBirdPipesVariables();
    //                fbPipes.x = 6000;
    //                fbPipes.y = 500;
    //                fbPipes.gapeHorizontalLength = 192;
    //                fbPipes.gapVerticalLength = 300;
    //                fbPipes.gapStartFrombottom = 288;
    //                VariablesForPlayArea.flappyBirdPipesList.add(fbPipes);
    //                */
    //                /*
    //                createHole = new CreateHoleVariables();
    //                createHole.map = new TmxMapLoader().load("playArea/LevelObstacles/createHole/level7/Area1Level7.tmx");
    //                createHole.tmr = new OrthogonalTiledMapRenderer(createHole.map);
    //                VariablesForPlayArea.createHoleList.add(createHole);
    //                */
    //                /*
    //                //jumper
    //
    //                jumper = new JumperVariables();
    //                jumper.x = 1500;
    //                jumper.y = 510;
    //                VariablesForPlayArea.jumperList.add(jumper);
    //
    //
    //                jumper = new JumperVariables();
    //                jumper.x = 2500;
    //                jumper.y = 510;
    //                VariablesForPlayArea.jumperList.add(jumper);
    //                */
    //
    //
    //                /*
    //                //halhSaw
    //                halfSawVariables = new HalfSawVariables();
    //                halfSawVariables.xdestination = 1700; //-100 for 2
    //                halfSawVariables.oneisSelected = true;
    //                halfSawVariables.xorigin = 1700;
    //                halfSawVariables.yorigin = 512;
    //                VariablesForPlayArea.halfSawList.add(halfSawVariables);
    //                */
    //
    //
    //
    //                //fullSaw
    //                fullSawVariables = new FullSawVariables();
    //                fullSawVariables.xpos = 3000;
    //                fullSawVariables.ypos = 572;
    //                fullSawVariables.size = 128;
    //                fullSawVariables.forwardDirection = false;
    //                fullSawVariables.intialDirectionForward = false;
    //
    //                VariablesForPlayArea.fullSawList.add(fullSawVariables);
    //
    //
    //                /*
    //                //powerUps
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 1;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 2;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 3;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 2;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 1;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 3;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //                */
    //
    //                powerupVar = new PowerUpInInventoryVariables();
    //                powerupVar.TypeOfPower = 4;
    //                VariablesForPlayArea.powerUpList.add(powerupVar);
    //
    //
    //
    //                /*
    //                //speed controller
    //                speedctlrvar = new SpeedControllerVariables();
    //                speedctlrvar.SpeedIncrementor = false;
    //                speedctlrvar.x = 800;
    //                speedctlrvar.y = 512;
    //                VariablesForPlayArea.speedCtrlList.add(speedctlrvar);
    //
    //                speedctlrvar = new SpeedControllerVariables();
    //                speedctlrvar.SpeedIncrementor = false;
    //                speedctlrvar.x = 2200;
    //                speedctlrvar.y = 512;
    //                VariablesForPlayArea.speedCtrlList.add(speedctlrvar);
    //
    //                speedctlrvar = new SpeedControllerVariables();
    //                speedctlrvar.SpeedIncrementor = true;
    //                speedctlrvar.x = 3000;
    //                speedctlrvar.y = 512;
    //                VariablesForPlayArea.speedCtrlList.add(speedctlrvar);
    //                */
    //
    //                /*
    //                dirRev = new DirectionReverseVariables();
    //                dirRev.x = 2000;
    //                dirRev.y = 525;
    //                dirRev.active = true;
    //
    //                VariablesForPlayArea.dirRevList.add(dirRev);
    //                */
    //
    //
    //
    //            }
    //
    //            game.setScreen(new TypeTwoArea(game, restarted));
    //        }
    // sds


