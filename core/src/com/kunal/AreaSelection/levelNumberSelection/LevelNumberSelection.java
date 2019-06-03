package com.kunal.AreaSelection.levelNumberSelection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.AreaSelection;
import com.kunal.MainGame;

import java.util.LinkedList;

public class LevelNumberSelection implements Screen {
    MainGame game;
    OrthographicCamera cam;
    Viewport port;
    FileHandle file;
    String DataInFile = "";


    //dataFromFile in the variables
    private short UnlockedLevel, TotalLevel, levelState, levelTrack;
    private LinkedList<Short> stars;

    private Texture stone;
    private Texture number0, number1, number2, number3, number4, number5, number6, number7, number8, number9;

    private Texture cross, movRight, movLeft, lock, starTex;
    private Boolean upperLayer = true;

    /*
    for the file structure  :
        for every area the format is : "_LevelNumber_#_UnLockedLevels_#_TotalLevels_#_UnLockedLevel_MultiplyBy_Star"
        "$" is the end of the file
     */


    public LevelNumberSelection(MainGame game) {
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        file = Gdx.files.internal("TextFiles/LevelAreaInfo");
        DataInFile = file.readString();

        stars = new LinkedList<Short>();

        levelState = 0;

        levelTrack=0;

        processData();

        //texture and sprites
        stone = new Texture(Gdx.files.internal("AreaSelection/levelSelection/stone.png"));
        cross = new Texture(Gdx.files.internal("utils/hudX.png"));
        movRight = new Texture(Gdx.files.internal("utils/arrowRight.png"));
        movLeft = new Texture(Gdx.files.internal("utils/arrowLeft.png"));


        //number
        number0 = new Texture(Gdx.files.internal("AreaSelection/Numbers/hud0.png"));
        number1 = new Texture(Gdx.files.internal("AreaSelection/Numbers/hud1.png"));
        number2 = new Texture(Gdx.files.internal("AreaSelection/Numbers/hud2.png"));
        number3 = new Texture(Gdx.files.internal("AreaSelection/Numbers/hud3.png"));
        number4 = new Texture(Gdx.files.internal("AreaSelection/Numbers/hud4.png"));
        number5 = new Texture(Gdx.files.internal("AreaSelection/Numbers/hud5.png"));
        number6 = new Texture(Gdx.files.internal("AreaSelection/Numbers/hud6.png"));
        number7 = new Texture(Gdx.files.internal("AreaSelection/Numbers/hud7.png"));
        number8 = new Texture(Gdx.files.internal("AreaSelection/Numbers/hud8.png"));
        number9 = new Texture(Gdx.files.internal("AreaSelection/Numbers/hud9.png"));

        lock = new Texture(Gdx.files.internal("AreaSelection/levelSelection/locked.png"));

        starTex = new Texture(Gdx.files.internal("AreaSelection/levelSelection/star.png"));


    }

    private void processData(){
        String tempstring= "";
        boolean doSkip = false;
        short hashCount =0, shorttmp;

        char[] data = DataInFile.toCharArray();
        hashCount = 0;
        tempstring = "";

        for (int i =0; i<DataInFile.length(); i++){
            if (doSkip){
                if (data[i] == '\n'){
                    doSkip = false;
                    hashCount = 0;
                    continue;
                }else {
                    continue;
                }
            }

            if (hashCount == 0){
                if (data[i] != '#') {
                    tempstring += data[i];
                }else {
                    hashCount++;
                    shorttmp = new Short(tempstring);
                    tempstring = "";

                    if (shorttmp == AllVariables.PresentAreaNumber) {
                        continue;
                    } else {
                        doSkip = true;
                        continue;
                    }
                }
            }

            if (hashCount == 1){
                if (data[i] != '#'){
                    tempstring += data[i];
                }else{
                    hashCount++;
                    shorttmp = new Short(tempstring);
                    tempstring = "";

                    UnlockedLevel = shorttmp;
                    continue;
                }
            }

            if (hashCount == 2){
                if (data[i] != '#'){
                    tempstring += data[i];
                }else{
                    hashCount++;
                    shorttmp = new Short(tempstring);
                    tempstring = "";

                    TotalLevel = shorttmp;

                    stars.clear();
                    continue;
                }
            }

            if (hashCount == 3){
                if (data[i] != '#'){
                    tempstring += data[i];
                    shorttmp = new Short(tempstring);
                    tempstring = "";

                    stars.add(shorttmp);
                }else{
                    hashCount++;
                    continue;
                }
            }

            if (hashCount == 4){
                break;
            }

            ///////////////////////////////
        }


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
        //move left
        AllVariables.batch.draw(movLeft,400+cam.position.x-AllVariables.WIDTH/2,720-128);
        //move right
        AllVariables.batch.draw(movRight,900+cam.position.x-AllVariables.WIDTH/2,720-128);


        for (int i =0,inc=0; i<TotalLevel/10;i++,inc+=1280) {
            //all stages
            AllVariables.batch.draw(stone, 250 + inc, 460, 120, 200);
            AllVariables.batch.draw(stone, 450 + inc, 460, 120, 200);
            AllVariables.batch.draw(stone, 650 + inc, 460, 120, 200);
            AllVariables.batch.draw(stone, 850 + inc, 460, 120, 200);
            AllVariables.batch.draw(stone, 1050 + inc, 460, 120, 200);

            AllVariables.batch.draw(stone, 250 + inc, 200, 120, 200);
            AllVariables.batch.draw(stone, 450 + inc, 200, 120, 200);
            AllVariables.batch.draw(stone, 650 + inc, 200, 120, 200);
            AllVariables.batch.draw(stone, 850 + inc, 200, 120, 200);
            AllVariables.batch.draw(stone, 1050 + inc, 200, 120, 200);
        }

        //number init
        //1
        AllVariables.batch.draw(number1,260, 470, 100,100);
        //2
        AllVariables.batch.draw(number2,460, 470, 100,100);
        //3
        AllVariables.batch.draw(number3,660, 470, 100,100);
        //4
        AllVariables.batch.draw(number4,860, 470, 100,100);
        //5
        AllVariables.batch.draw(number5,1060, 470, 100,100);
        //6
        AllVariables.batch.draw(number6,260, 210, 100,100);
        //7
        AllVariables.batch.draw(number7,465, 210, 100,100);
        //8
        AllVariables.batch.draw(number8,660, 210, 100,100);
        //9
        AllVariables.batch.draw(number9,860, 210, 100,100);
        //10
        AllVariables.batch.draw(number1,1040 , 210, 100,100);
        AllVariables.batch.draw(number0,1080 , 210, 100,100);
        //11
        AllVariables.batch.draw(number1,240 + 1280, 470, 100,100);
        AllVariables.batch.draw(number1,280 + 1280, 470, 100,100);
        //12
        AllVariables.batch.draw(number1,440 + 1280, 470, 100,100);
        AllVariables.batch.draw(number2,480 + 1280, 470, 100,100);
        //13
        AllVariables.batch.draw(number1,640 + 1280, 470, 100,100);
        AllVariables.batch.draw(number3,680 + 1280, 470, 100,100);
        //14
        AllVariables.batch.draw(number1,840 + 1280, 470, 100,100);
        AllVariables.batch.draw(number4,880 + 1280, 470, 100,100);
        //15
        AllVariables.batch.draw(number1,1040 + 1280, 470, 100,100);
        AllVariables.batch.draw(number5,1080 + 1280, 470, 100,100);
        //16
        AllVariables.batch.draw(number1,240 + 1280, 210, 100,100);
        AllVariables.batch.draw(number6,280 + 1280, 210, 100,100);
        //17
        AllVariables.batch.draw(number1,440 + 1280, 210, 100,100);
        AllVariables.batch.draw(number7,480 + 1280, 210, 100,100);
        //18
        AllVariables.batch.draw(number1,640 + 1280, 210, 100,100);
        AllVariables.batch.draw(number8,680 + 1280, 210, 100,100);
        //19
        AllVariables.batch.draw(number1,840 + 1280, 210, 100,100);
        AllVariables.batch.draw(number9,880 + 1280, 210, 100,100);
        //20
        AllVariables.batch.draw(number2,1040 + 1280, 210, 100,100);
        AllVariables.batch.draw(number0,1080 + 1280, 210, 100,100);
        //21
        AllVariables.batch.draw(number2,240 + 1280*2, 470, 100,100);
        AllVariables.batch.draw(number1,280 + 1280*2, 470, 100,100);
        //22
        AllVariables.batch.draw(number2,440 + 1280*2, 470, 100,100);
        AllVariables.batch.draw(number2,480 + 1280*2, 470, 100,100);
        //23
        AllVariables.batch.draw(number2,640 + 1280*2, 470, 100,100);
        AllVariables.batch.draw(number3,680 + 1280*2, 470, 100,100);
        //24
        AllVariables.batch.draw(number2,840 + 1280*2, 470, 100,100);
        AllVariables.batch.draw(number4,880 + 1280*2, 470, 100,100);
        //25
        AllVariables.batch.draw(number2,1040 + 1280*2, 470, 100,100);
        AllVariables.batch.draw(number5,1080 + 1280*2, 470, 100,100);
        //26
        AllVariables.batch.draw(number2,240 + 1280*2, 210, 100,100);
        AllVariables.batch.draw(number6,280 + 1280*2, 210, 100,100);
        //27
        AllVariables.batch.draw(number2,440 + 1280*2, 210, 100,100);
        AllVariables.batch.draw(number7,480 + 1280*2, 210, 100,100);
        //28
        AllVariables.batch.draw(number2,640 + 1280*2, 210, 100,100);
        AllVariables.batch.draw(number8,680 + 1280*2, 210, 100,100);
        //29
        AllVariables.batch.draw(number2,840 + 1280*2, 210, 100,100);
        AllVariables.batch.draw(number9,880 + 1280*2, 210, 100,100);
        //30
        AllVariables.batch.draw(number3,1040 + 1280*2, 210, 100,100);
        AllVariables.batch.draw(number0,1080 + 1280*2, 210, 100,100);

        //Lock
        levelTrack = 0;
        for (int i =0, inci = 0; i<TotalLevel/10; i++){
            for (int j =0, incj=470; j<2; j++){
                for (int k =0, inck=250; k<5;k++){
                    levelTrack++;
                    if (levelTrack <= UnlockedLevel){

                    }else {
                        AllVariables.batch.draw(lock,inck+inci,incj,120,120);
                    }
                    inck+=200;
                }
                incj = 210;
            }
            inci+=1280;
        }

        //AllVariables.batch.draw(starTex, 255, 425, 50, 50);
        //AllVariables.batch.draw(starTex, 285, 425, 50, 50);
        //AllVariables.batch.draw(starTex, 315, 425, 50, 50);

        //stars
        upperLayer = false;
        for (int i =0, incStar = -1280, incTonextLevel=0; i<UnlockedLevel; i++){
            if (i%5 == 0) {
                if (upperLayer) {
                    upperLayer = false;
                    incTonextLevel = 255;
                }
                else {
                    upperLayer = true;
                    incTonextLevel = 255;
                }
            }
            if (i%10==0)
                incStar+=1280;

            if (upperLayer){
                for (int j=0, incSmall = incTonextLevel; j<stars.get(i); j++, incSmall+=30)
                    AllVariables.batch.draw(starTex, incSmall + incStar, 425, 50, 50);
            }else {
                for (int j=0, incSmall = incTonextLevel; j<stars.get(i); j++, incSmall+=30)
                    AllVariables.batch.draw(starTex, incSmall + incStar, 165, 50, 50);
            }
            incTonextLevel+=200;
        }


        AllVariables.batch.end();

    }

    private void input(float dt){
        if (Gdx.input.isKeyJustPressed(Input.Keys.B))
            game.setScreen(new AreaSelection(game));
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            System.out.println("Unlock Levels : \t" + UnlockedLevel);
            System.out.println("Total Levels : \t" + TotalLevel);
            System.out.println("Stars : \t" + stars);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            levelState--;
            if (levelState<=-1)
                levelState=0;
            else
                cam.position.set(cam.position.x -= 1280, cam.position.y, cam.position.z);
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            levelState++;
            if (levelState >= TotalLevel/10)
                levelState = (short) (TotalLevel/10 -1);
            else
                cam.position.set(cam.position.x += 1280, cam.position.y, cam.position.z);
        }

        if (Gdx.input.justTouched()){
            //cross
            if (Gdx.input.getX() >= (0*AllVariables.inpM)+AllVariables.witdth_translation &&
                Gdx.input.getX() < (128*AllVariables.inpM)+AllVariables.witdth_translation &&
                Gdx.input.getY() >= 0 * AllVariables.inpM && Gdx.input.getY() < 128*AllVariables.inpM){
                game.setScreen(new AreaSelection(game));
            }

            //left
            if (Gdx.input.getX() >= (400*AllVariables.inpM)+AllVariables.witdth_translation &&
                    Gdx.input.getX() < (500*AllVariables.inpM)+AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 0 * AllVariables.inpM && Gdx.input.getY() < 128*AllVariables.inpM){
                levelState--;
                if (levelState<=-1)
                    levelState=0;
                else
                    cam.position.set(cam.position.x -= 1280, cam.position.y, cam.position.z);
            }

            //right
            if (Gdx.input.getX() >= (900*AllVariables.inpM)+AllVariables.witdth_translation &&
                    Gdx.input.getX() < (1000*AllVariables.inpM)+AllVariables.witdth_translation &&
                    Gdx.input.getY() >= 0 * AllVariables.inpM && Gdx.input.getY() < 128*AllVariables.inpM){
                levelState++;
                if (levelState >= TotalLevel/10)
                    levelState = (short) (TotalLevel/10 -1);
                else
                    cam.position.set(cam.position.x += 1280, cam.position.y, cam.position.z);
            }

            if (cam.position.x == 640){
                if (Gdx.input.getY() >= 150 && Gdx.input.getY() <= 275){
                    //1--------------------------------------------
                    if (Gdx.input.getX() >= 230 && Gdx.input.getX() <= 395){
                        if (UnlockedLevel >= 1){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //2---------------------------------------
                    else if (Gdx.input.getX() >= 430 && Gdx.input.getX() <= 595){
                        if (UnlockedLevel >= 2){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //3----------------------------------------
                    else if (Gdx.input.getX() >= 630 && Gdx.input.getX() <= 795){
                        if (UnlockedLevel >= 3){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //4-------------------------------------------
                    else if (Gdx.input.getX() >= 830 && Gdx.input.getX() <= 995){
                        if (UnlockedLevel >= 4){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //5-----------------------------------------------
                    else if (Gdx.input.getX() >= 1030 && Gdx.input.getX() <= 1195){
                        if (UnlockedLevel >= 5){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }

                }
                else if (Gdx.input.getY() >= 410 && Gdx.input.getY() <= 535){
                    //6-------------------------------------------------
                    if (Gdx.input.getX() >= 230 && Gdx.input.getX() <= 395){
                        if (UnlockedLevel >= 6){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //7-------------------------------------------------------
                    else if (Gdx.input.getX() >= 430 && Gdx.input.getX() <= 595){
                        if (UnlockedLevel >= 7){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //8-------------------------------------------------------
                    else if (Gdx.input.getX() >= 630 && Gdx.input.getX() <= 795){
                        if (UnlockedLevel >= 8){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //9-------------------------------------------------------
                    else if (Gdx.input.getX() >= 830 && Gdx.input.getX() <= 995){
                        if (UnlockedLevel >= 9){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //10--------------------------------------------------------
                    else if (Gdx.input.getX() >= 1030 && Gdx.input.getX() <= 1195){
                        if (UnlockedLevel >= 10){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                }

            }
            // 11 to 20
            else if (cam.position.x == 1920){
                if (Gdx.input.getY() >= 150 && Gdx.input.getY() <= 275){
                    //11--------------------------------------------
                    if (Gdx.input.getX() >= 230 && Gdx.input.getX() <= 395){
                        if (UnlockedLevel >= 11){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //12---------------------------------------
                    else if (Gdx.input.getX() >= 430 && Gdx.input.getX() <= 595){
                        if (UnlockedLevel >= 12){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //13----------------------------------------
                    else if (Gdx.input.getX() >= 630 && Gdx.input.getX() <= 795){
                        if (UnlockedLevel >= 13){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //14-------------------------------------------
                    else if (Gdx.input.getX() >= 830 && Gdx.input.getX() <= 995){
                        if (UnlockedLevel >= 14){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //15-----------------------------------------------
                    else if (Gdx.input.getX() >= 1030 && Gdx.input.getX() <= 1195){
                        if (UnlockedLevel >= 15){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }

                }else if (Gdx.input.getY() >= 410 && Gdx.input.getY() <= 535){
                    //16-------------------------------------------------
                    if (Gdx.input.getX() >= 230 && Gdx.input.getX() <= 395){
                        if (UnlockedLevel >= 16){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //17-------------------------------------------------------
                    else if (Gdx.input.getX() >= 430 && Gdx.input.getX() <= 595){
                        if (UnlockedLevel >= 17){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //18-------------------------------------------------------
                    else if (Gdx.input.getX() >= 630 && Gdx.input.getX() <= 795){
                        if (UnlockedLevel >= 18){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //19-------------------------------------------------------
                    else if (Gdx.input.getX() >= 830 && Gdx.input.getX() <= 995){
                        if (UnlockedLevel >= 19){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //20--------------------------------------------------------
                    else if (Gdx.input.getX() >= 1030 && Gdx.input.getX() <= 1195){
                        if (UnlockedLevel >= 20){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                }
            }
            //21 to 30
            else if (cam.position.x == 3200){
                if (Gdx.input.getY() >= 150 && Gdx.input.getY() <= 275){
                    //21--------------------------------------------
                    if (Gdx.input.getX() >= 230 && Gdx.input.getX() <= 395){
                        if (UnlockedLevel >= 21){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //22---------------------------------------
                    else if (Gdx.input.getX() >= 430 && Gdx.input.getX() <= 595){
                        if (UnlockedLevel >= 22){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //23----------------------------------------
                    else if (Gdx.input.getX() >= 630 && Gdx.input.getX() <= 795){
                        if (UnlockedLevel >= 23){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //24-------------------------------------------
                    else if (Gdx.input.getX() >= 830 && Gdx.input.getX() <= 995){
                        if (UnlockedLevel >= 24){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //25-----------------------------------------------
                    else if (Gdx.input.getX() >= 1030 && Gdx.input.getX() <= 1195){
                        if (UnlockedLevel >= 25){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }

                }else if (Gdx.input.getY() >= 410 && Gdx.input.getY() <= 535){
                    //26-------------------------------------------------
                    if (Gdx.input.getX() >= 230 && Gdx.input.getX() <= 395){
                        if (UnlockedLevel >= 26){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //27-------------------------------------------------------
                    else if (Gdx.input.getX() >= 430 && Gdx.input.getX() <= 595){
                        if (UnlockedLevel >= 27){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //28-------------------------------------------------------
                    else if (Gdx.input.getX() >= 630 && Gdx.input.getX() <= 795){
                        if (UnlockedLevel >= 28){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //29-------------------------------------------------------
                    else if (Gdx.input.getX() >= 830 && Gdx.input.getX() <= 995){
                        if (UnlockedLevel >= 29){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                    //30--------------------------------------------------------
                    else if (Gdx.input.getX() >= 1030 && Gdx.input.getX() <= 1195){
                        if (UnlockedLevel >= 30){
                            game.setScreen(new TestClassToUnlockLevel(game, UnlockedLevel));
                        }
                    }
                }
            }

            //System.out.println(cam.position.x);

            System.out.println(Gdx.input.getX()+"\t"+Gdx.input.getY());
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
        game.dispose();
        DataInFile="";
        stars.clear();
        stone.dispose();
        number0.dispose();
        number1.dispose();
        number2.dispose();
        number3.dispose();
        number4.dispose();
        number5.dispose();
        number6.dispose();
        number7.dispose();
        number8.dispose();
        number9.dispose();
        cross.dispose();
        movRight.dispose();
        movLeft.dispose();
    }
}