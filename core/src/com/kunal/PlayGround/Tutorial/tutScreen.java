package com.kunal.PlayGround.Tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;
import com.kunal.PlayGround.Tutorial.InGameTutorial.BasicTutorial;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.playScreen.playScreen;
import com.kunal.utils.ReDirectToTheLevel;
import com.kunal.utils.slideShow.slideShow;

import java.util.LinkedList;

public class tutScreen implements Screen {

    MainGame game;
    OrthographicCamera cam;
    Viewport port;

    //need  to add left right after wards reffer lever selection for ease
    short levelState;

    private Texture cross;//, movRight, movLeft;
    // tut icons
    private Texture Basic_tut;

    BitmapFont font;

    public tutScreen (MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        cross = new Texture(Gdx.files.internal("utils/hudX.png"));
        //movRight = new Texture(Gdx.files.internal("utils/arrowRight.png"));
        //movLeft = new Texture(Gdx.files.internal("utils/arrowLeft.png"));

        Basic_tut = new Texture(Gdx.files.internal("tut/icons/basic.png"));

        font = new BitmapFont();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter prams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        prams.size = 24;
        prams.color = Color.ORANGE;
        font = generator.generateFont(prams);


    }

    @Override
    public void show() {
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.3f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        AllVariables.batch.setProjectionMatrix(cam.combined);
        input(delta);

        cam.update();

        AllVariables.batch.begin();
        //cross to go back
        AllVariables.batch.draw(cross,0+cam.position.x-AllVariables.WIDTH/2,720-128);
        font.draw(AllVariables.batch,"Learn Basic", 290,470);



        //tutorials
        AllVariables.batch.draw(Basic_tut, 250, 460, 200, 200);
        AllVariables.batch.end();

    }

    private void input(float dt){
        if (Gdx.input.justTouched()) {

            System.out.println(Gdx.input.getX()+"\t"+Gdx.input.getY());

            //cross
            if (Gdx.input.getX() >= (0 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getX() < (128 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 0 * AllVariables.inpM && Gdx.input.getY() < 128 * AllVariables.inpM) {
                //dispose();
                game.setScreen(new AreaSelection(game,true));
                return;
            }
            //basic tut
            if (Gdx.input.getX() >= (270 * AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getX() < (440* AllVariables.inpM) + AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 87 * AllVariables.inpM && Gdx.input.getY() < 232 * AllVariables.inpM) {
                //dispose();
                //game.setScreen(new BasicTutorial(game, (short) 1));
                AllVariables.PresentLevelNumber = 0;
                AllVariables.PresentAreaNumber = 0;
                VariablesForPlayArea.tutState = 0;
                ReDirectToTheLevel.Direct(game,false);

                //to Open YouTube in main
                //AllVariables.openApps.OpenApp("https://youtu.be/hD6K6XK_AFY");
                return;
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
        cam.update();
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
