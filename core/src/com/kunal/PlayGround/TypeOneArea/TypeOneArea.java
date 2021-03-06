package com.kunal.PlayGround.TypeOneArea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.AreaSelection.levelNumberSelection.LevelNumberSelection;
import com.kunal.MainGame;
import com.kunal.PlayGround.ObjectCreation;
import com.kunal.PlayGround.PlayAreaUtils;
import com.kunal.PlayGround.VariablesForPlayArea;
import com.kunal.PlayGround.constScreen.CuttingArea.CuttingAreaManager;
import com.kunal.PlayGround.constScreen.ShapeChooser;
import com.kunal.utils.BodyGenerator;
import com.kunal.utils.TiledMapLoadingHelper;

public class TypeOneArea implements Screen {

    MainGame game;

    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera cam;
    private Viewport port;

    private Sprite Brake, start, chooseBody, HardMoveShapes, CamScroller, DropAnyShapeButton, ShapeRotACW, ShapeRotCW,
            per45degRot, pause, fadedBG, resume, exit, flag,coin1,coin2,coin3;
    private Boolean brakeBool = false, startBool = false, hardMove = true, hardmoveFaultResolver = false,
            isCamScrollerTouched = false, toDrawDropAnyShapeButton = true, isAnyShapeSelected = false,
            ACWTouched = false, CWtouched = false, paused = false, coin1anim = false,
            coin2anim = false, coin3anim= false;

    private float coin1Alpha = 0, coin2Alpha = 0,coin3Alpha = 0;

    //CamScroller
    private short CamScrollerX = 1020, CamScrollerY = 710;
    private byte camScrollSize =100;
    private ShapeRenderer sred;

    private ObjectCreation objectCreation;

    private int dragged_touchX =0;

    //temp Rotation Folder for shapes
    float tempRotForShape;

    //tiled map
    private TiledMap map;
    private OrthogonalTiledMapRenderer tmr;

    private float ver[];

    float camscl = 1.4f;

    int originX, originY;
    float shapeX, shapeY;

    Polygon poly;

    //follow cycle if start is pressed
    private boolean CamfollowCycle = false, startAnimToMoveCycle = false, finalvalofcamcontroller = false;

    //posision mapper sprite
    Sprite posMap;

    public TypeOneArea(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH*camscl, AllVariables.HEIGHT*camscl, cam);

        port.apply();

        world = new World(new Vector2(0,0f), false);

        b2dr = new Box2DDebugRenderer();

        sred = new ShapeRenderer();

        objectCreation = new ObjectCreation();

        objectCreation.CreateBicycle(world, 600,0);
        objectCreation.CreateCutouts(world);

        poly = new Polygon();

        //for testing
        //AllVariables.PresentAreaNumber = 101;
        //AllVariables.PresentLevelNumber = 101;
        //for testing

        //safelt platforn for all objects
        BodyGenerator.BodyAssemble(world, true, "Land", new Vector2(640, -1500),
                new Vector2(2000, 50), 1,1, AllVariables.Bit_land,
                (short)(AllVariables.Bit_Bicycle|AllVariables.Bit_enimes|AllVariables.Bit_Tool|AllVariables.Bit_land));


        //tiled map
        map = new TmxMapLoader().load(VariablesForPlayArea.LevelMapToBeLoaded);
        tmr = new OrthogonalTiledMapRenderer(map);
        for (int parseObjI = 1; parseObjI <= TiledMapLoadingHelper.NumberOfObj(); parseObjI++)
            PlayAreaUtils.parseTiledObj(world,map.getLayers().get("Object Layer "+parseObjI).getObjects());


        System.out.println(AllVariables.PresentAreaNumber + "\t" + AllVariables.PresentLevelNumber);

        //cam.position.set(port.getWorldWidth()/2, port.getWorldHeight()/2,0);

        Brake = new Sprite(new Texture(Gdx.files.internal("playArea/Brake.png")));
        Brake.setPosition(1050,140);
        Brake.setSize(180*camscl,150*camscl);
        Brake.setAlpha(0f);

        start = new Sprite(new Texture(Gdx.files.internal("playArea/Start.png")));
        start.setPosition(-190, 50);
        start.setSize(150*camscl, 150*camscl);
        start.setAlpha(0.8f);

        chooseBody = new Sprite(new Texture(Gdx.files.internal("playArea/ChooseBody.png")));
        chooseBody.setPosition(50, 140);
        chooseBody.setSize(150*camscl, 150*camscl);
        chooseBody.setAlpha(0.8f);

        HardMoveShapes = new Sprite(new Texture(Gdx.files.internal("playArea/HardMove.png")));
        HardMoveShapes.setPosition(50, 240);
        HardMoveShapes.setSize(100*camscl, 100*camscl);
        HardMoveShapes.setAlpha(1f);

        CamScroller = new Sprite(new Texture(Gdx.files.internal("playArea/CamScroller.png")));
        CamScroller.setPosition(50,240);
        CamScroller.setSize(camScrollSize*camscl, camScrollSize*camscl);
        CamScroller.setAlpha(1);

        DropAnyShapeButton = new Sprite(new Texture(Gdx.files.internal("playArea/DropAnyShapeButton.png")));
        DropAnyShapeButton.setPosition(50,240);
        DropAnyShapeButton.setSize(100*camscl, 60*camscl);
        DropAnyShapeButton.setAlpha(0.5f);

        ShapeRotACW = new Sprite(new Texture(Gdx.files.internal("playArea/ShapeRotation_ACW.png")));
        ShapeRotACW.setPosition(50,240);
        ShapeRotACW.setSize(50*camscl, 50*camscl);
        ShapeRotACW.setAlpha(0.8f);

        per45degRot = new Sprite(new Texture(Gdx.files.internal("playArea/ShapeRotation_per45deg.png")));
        per45degRot.setPosition(50,240);
        per45degRot.setSize(50*camscl, 50*camscl);
        per45degRot.setAlpha(0.8f);


        ShapeRotCW = new Sprite(new Texture(Gdx.files.internal("playArea/ShapeRotation_CW.png")));
        ShapeRotCW.setPosition(50,240);
        ShapeRotCW.setSize(50*camscl, 50*camscl);
        ShapeRotCW.setAlpha(0.8f);

        pause = new Sprite(new Texture(Gdx.files.internal("playArea/pauseTex.png")));
        pause.setPosition(50,240);
        pause.setSize(100*camscl, 100*camscl);
        pause.setAlpha(0.8f);

        fadedBG = new Sprite(new Texture(Gdx.files.internal("playArea/fadedBG.png")));
        fadedBG.setPosition(0,0);
        fadedBG.setSize(Gdx.graphics.getWidth()*2f, Gdx.graphics.getHeight()*2);

        resume = new Sprite(new Texture(Gdx.files.internal("playArea/resume.png")));
        resume.setPosition(0,0);
        resume.setSize(250*camscl, 100*camscl);

        exit = new Sprite(new Texture(Gdx.files.internal("playArea/exit.png")));
        exit.setPosition(0,0);
        exit.setSize(250*camscl, 100*camscl);

        flag = new Sprite(new Texture(Gdx.files.internal("playArea/flagRed_up.png")));
        flag.setPosition(TiledMapLoadingHelper.flagpos().x,TiledMapLoadingHelper.flagpos().y);

        posMap = new Sprite(new Texture(Gdx.files.internal("badlogic.png")));
        posMap.setPosition(50,50);
        posMap.setSize(70,70);

        coin1 = new Sprite(new Texture(Gdx.files.internal(AllVariables.coinAdd)));
        coin1.setPosition(TiledMapLoadingHelper.coin1Pos().x,TiledMapLoadingHelper.coin1Pos().y);
        coin1.setSize(100,100);
        coin1.setAlpha(coin1Alpha);

        coin2 = new Sprite(new Texture(Gdx.files.internal(AllVariables.coinAdd)));
        coin2.setPosition(TiledMapLoadingHelper.coin2Pos().x,TiledMapLoadingHelper.coin2Pos().y);
        coin2.setSize(100,100);
        coin2.setAlpha(coin2Alpha);

        coin3 = new Sprite(new Texture(Gdx.files.internal(AllVariables.coinAdd)));
        coin3.setPosition(TiledMapLoadingHelper.coin3Pos().x,TiledMapLoadingHelper.coin3Pos().y);
        coin3.setSize(100,100);
        coin3.setAlpha(coin3Alpha);




        //pos remapping
        //for (int i =0; i<VariablesForPlayArea.CutOutBodies.size(); i++){
        //VariablesForPlayArea.CutOutBodies.get(i).setTransform(VariablesForPlayArea.Sh_pos.get(i), VariablesForPlayArea.CutOutBodies.get(i).getAngle());
        //}

        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
    }

    @Override
    public void show() {
        AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
        AllVariables.witdth_translation =  (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
    }

    @Override
    public void render(float dt) {
        //Gdx.gl.glClearColor(.7f, 0.7f, .9f, 1);
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(dt);

        b2dr.render(world, cam.combined.scl(AllVariables.PPM));
        //need to fix this

        sred.setProjectionMatrix(cam.combined.scl(1/100f));

        tmr.render();

        sred.begin(ShapeRenderer.ShapeType.Line);

        sred.setColor(1, 1f, 1, 1);

        for (int i = 0; i < VariablesForPlayArea.shapes.size(); i++) {
            ver = new float[(VariablesForPlayArea.shapes.get(i).size() * 2)];
            for (int j = 0, k = 0; j < VariablesForPlayArea.shapes.get(i).size(); j++) {
                ver[k] = VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(0)][0]/(2)-VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][0]/2;
                k++;
                ver[k] = VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(0)][1]/(2) -VariablesForPlayArea.BigSqurePoints[VariablesForPlayArea.shapes.get(i).get(j)][1]/2;
                k++;
            }


            poly = new Polygon(ver);
            //poly.setPosition(AllVariables.BackWheel.getPosition().x*100
            //    , AllVariables.BackWheel.getPosition().y*100);
            poly.setPosition(VariablesForPlayArea.CutOutBodies.get(i).getPosition().x * 100,
                    VariablesForPlayArea.CutOutBodies.get(i).getPosition().y * 100);
            //it -am ::200);

            poly.setRotation(VariablesForPlayArea.Angle_Of_Shape.get(i));
            poly.dirty();
            sred.polygon(poly.getTransformedVertices());


            ver = null;

        }


        sred.end();

        AllVariables.batch.setProjectionMatrix(cam.combined);


        AllVariables.batch.begin();
        Brake.draw(AllVariables.batch);
        start.draw(AllVariables.batch);
        chooseBody.draw(AllVariables.batch);
        HardMoveShapes.draw(AllVariables.batch);
        CamScroller.draw(AllVariables.batch);
        DropAnyShapeButton.draw(AllVariables.batch);
        pause.draw(AllVariables.batch);
        flag.draw(AllVariables.batch);
        if(isAnyShapeSelected){
            ShapeRotACW.draw(AllVariables.batch);
            ShapeRotCW.draw(AllVariables.batch);
            per45degRot.draw(AllVariables.batch);
        }
        if (paused) {
            fadedBG.draw(AllVariables.batch);
            resume.draw(AllVariables.batch);
            exit.draw(AllVariables.batch);
        }

        posMap.draw(AllVariables.batch);
        //coin
        coin1.draw(AllVariables.batch);
        coin2.draw(AllVariables.batch);
        coin3.draw(AllVariables.batch);

        AllVariables.batch.end();
    }


    private void update(float dt){
        //System.out.println(VariablesForPlayArea.CutOutBodies.get(0).getFixtureList().get(0).getFilterData().maskBits);
        input(dt);


        //if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        //world.step((1)/(1/dt), 6,2);


        //all collectable code here=========================================================================
        // //meaning dynamic obj

        //this statement here because it will always be false and the program will not went in and this save some sweet time
        //flag
        if ((AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.flagpos().x-5 &&
                (AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)-(25+100+5)<=TiledMapLoadingHelper.flagpos().x-5){
            if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.flagpos().y-5 ||
                    (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.flagpos().y-5) {
                if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.flagpos().y+60 ||
                        (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.flagpos().y+60) {
                    flag.setTexture(new Texture(Gdx.files.internal("playArea/flagRed_down.png")));
                    flag.setSize(flag.getTexture().getWidth(), flag.getTexture().getHeight());
                }
            }
        }

        //coin1
        if ((AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin1Pos().x-5 &&
                (AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)-(25+100+5)<=TiledMapLoadingHelper.coin1Pos().x){
            if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin1Pos().y-5 ||
                    (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin1Pos().y-5) {
                if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.coin1Pos().y+100 ||
                        (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.coin1Pos().y+100) {
                    coin1anim = true;
                }
            }
        }


        //coin2
        if ((AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin2Pos().x-5 &&
                (AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)-(25+100+5)<=TiledMapLoadingHelper.coin2Pos().x){
            if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin2Pos().y-5 ||
                    (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin2Pos().y-5) {
                if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.coin2Pos().y+100 ||
                        (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.coin2Pos().y+100) {
                    coin2anim = true;
                }
            }
        }


        //coin3
        if ((AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin3Pos().x-5 &&
                (AllVariables.FrontWheel.getPosition().x*AllVariables.PPM)-(25+100+5)<=TiledMapLoadingHelper.coin3Pos().x){
            if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin3Pos().y-5 ||
                    (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)+(25+5) >= TiledMapLoadingHelper.coin3Pos().y-5) {
                if ((AllVariables.FrontWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.coin3Pos().y+100 ||
                        (AllVariables.BackWheel.getPosition().y*AllVariables.PPM)-(25+5) <= TiledMapLoadingHelper.coin3Pos().y+100) {
                    coin3anim = true;
                }
            }
        }


        //System.out.println(((AllVariables.BackWheel.getPosition().y * AllVariables.PPM)));


        //if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        world.step(1/(1/dt), 6,2);



        //ends here

        //Vector3 campos = cam.position;
        //campos.x = (AllVariables.BackWheel.getPosition().x)*AllVariables.PPM;
        //campos.y = (AllVariables.BackWheel.getPosition().y)*AllVariables.PPM;
        //cam.position.set(campos);

        //all animations---------------------------------------------------------------------------
        if (coin1anim){
            if (coin1.getScaleX()<=2.5f)
                coin1.scale(0.5f);
            if (coin1Alpha <=1) {
                coin1Alpha += 0.1f;
                coin1.translate(0,60);
            }
        }
        if (coin2anim){
            if (coin2.getScaleX()<=2.5f)
                coin2.scale(0.5f);
            if (coin2Alpha <=1) {
                coin2Alpha += 0.1f;
                coin2.translate(0,60);
            }
        }
        if (coin3anim){
            if (coin3.getScaleX()<=2.5f)
                coin3.scale(0.5f);
            if (coin3Alpha <=1) {
                coin3Alpha += 0.1f;
                coin3.translate(0,60);
            }
        }



        //==-------------------------------------------------------------------------------------



        coin1.setAlpha(1-coin1Alpha);
        coin2.setAlpha(1-coin2Alpha);
        coin3.setAlpha(1-coin3Alpha);


        if (!CamfollowCycle)
            cam.position.set(VariablesForPlayArea.camposX, 600, cam.position.z);
        else {
            VariablesForPlayArea.camposX = (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM;
            cam.position.set(VariablesForPlayArea.camposX, 600f, cam.position.z);
        }

        if (VariablesForPlayArea.camposX >= VariablesForPlayArea.endPoint.y)
            CamfollowCycle = false;

        //start
        if (startAnimToMoveCycle){
            if(VariablesForPlayArea.camposX - 156 >= (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM  -25 &&
                    VariablesForPlayArea.camposX - 156 <= (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM+25 ){
                start.setAlpha(0);
                chooseBody.setAlpha(0);
                HardMoveShapes.setAlpha(0);
                ShapeRotACW.setAlpha(0);
                ShapeRotCW.setAlpha(0);
                per45degRot.setAlpha(0);
                toDrawDropAnyShapeButton = false;
                CamScroller.setAlpha(0);
                startBool = true;
                pause.setAlpha(0);

                world.setGravity(new Vector2(0,-10));
                Brake.setAlpha(0.4f);
                PlayAreaUtils.MoveShapesToRealWorld();
                VariablesForPlayArea.shapeNumberSelected = 21;
                CamfollowCycle = true;
                startAnimToMoveCycle = false;
                finalvalofcamcontroller = true;
            }else{
                if(VariablesForPlayArea.camposX - 136 > (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM)
                    VariablesForPlayArea.camposX -=40;
                if(VariablesForPlayArea.camposX - 136 < (AllVariables.BackWheel.getPosition().x) * AllVariables.PPM)
                    VariablesForPlayArea.camposX +=40;

            }
        }

        cam.update();

        tmr.setView(cam);

        //position of sprites
        start.setPosition(-190+(cam.position.x - AllVariables.WIDTH/2), 50+(cam.position.y - AllVariables.HEIGHT/2));
        Brake.setPosition(1200+(cam.position.x - AllVariables.WIDTH/2), 50+(cam.position.y - AllVariables.HEIGHT/2));
        chooseBody.setPosition(1200+(cam.position.x - AllVariables.WIDTH/2), 50+(cam.position.y - AllVariables.HEIGHT/2));
        HardMoveShapes.setPosition(-220+(cam.position.x - AllVariables.WIDTH/2), 540+(cam.position.y -AllVariables.HEIGHT/2));
        CamScroller.setPosition(CamScrollerX+(cam.position.x - AllVariables.WIDTH/2), CamScrollerY+(cam.position.y - AllVariables.HEIGHT/2));
        DropAnyShapeButton.setPosition(-220+(cam.position.x - AllVariables.WIDTH/2), 440+(cam.position.y -AllVariables.HEIGHT/2));
        pause.setPosition(-220+(cam.position.x - AllVariables.WIDTH/2), 700+(cam.position.y -AllVariables.HEIGHT/2));
        fadedBG.setPosition(-280+(cam.position.x - AllVariables.WIDTH/2), -200+(cam.position.y -AllVariables.HEIGHT/2));
        resume.setPosition(470+(cam.position.x - AllVariables.WIDTH/2), 540+(cam.position.y -AllVariables.HEIGHT/2));
        exit.setPosition(470+(cam.position.x - AllVariables.WIDTH/2), 340+(cam.position.y -AllVariables.HEIGHT/2));





        if (toDrawDropAnyShapeButton){
            ShapeRotACW.setPosition(1420+(cam.position.x - AllVariables.WIDTH/2), 370+(cam.position.y - AllVariables.HEIGHT/2));
            ShapeRotCW.setPosition(1420+(cam.position.x - AllVariables.WIDTH/2), 610+(cam.position.y - AllVariables.HEIGHT/2));
            per45degRot.setPosition(1420+(cam.position.x - AllVariables.WIDTH/2), 490+(cam.position.y - AllVariables.HEIGHT/2));

        }

        //cam dragging
        if (isCamScrollerTouched){
            //moved to left
            if(originX - dragged_touchX > Gdx.graphics.getWidth()/24){
                CamScrollerX = 950;
                CamScrollerY = 715;
                camScrollSize = 70;
                if (hardMove)
                    VariablesForPlayArea.camposX-=30f;
                else
                    VariablesForPlayArea.camposX-=10f;
            }
            //moved to right
            else if(dragged_touchX - originX > Gdx.graphics.getWidth()/28){
                CamScrollerX = 1100;
                CamScrollerY = 715;
                camScrollSize = 70;
                if (hardMove)
                    VariablesForPlayArea.camposX+=30f;
                else
                    VariablesForPlayArea.camposX+=10f;


            }else{
                CamScrollerX = 1020;
                CamScrollerY = 710;
                camScrollSize = 100;
            }
        }

        if (!finalvalofcamcontroller) {
            //x is init point and y is final point
            if (VariablesForPlayArea.camposX < VariablesForPlayArea.endPoint.x) {
                VariablesForPlayArea.camposX = VariablesForPlayArea.endPoint.x;
            } else if (VariablesForPlayArea.camposX > VariablesForPlayArea.endPoint.y) {
                VariablesForPlayArea.camposX = VariablesForPlayArea.endPoint.y;
            }

        }



        //changing rotation
        if (ACWTouched){
            tempRotForShape = VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected);
            if (hardMove)
                tempRotForShape-=3;
            else
                tempRotForShape-=1;
            if(tempRotForShape<=0)
                tempRotForShape = (360 - tempRotForShape);
            VariablesForPlayArea.Angle_Of_Shape.set(VariablesForPlayArea.shapeNumberSelected, tempRotForShape);
        }

        if (CWtouched){
            tempRotForShape = VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected);
            if (hardMove)
                tempRotForShape+=3;
            else
                tempRotForShape+=1;
            if(tempRotForShape>=360)
                tempRotForShape = (short) (tempRotForShape - 360);
            VariablesForPlayArea.Angle_Of_Shape.set(VariablesForPlayArea.shapeNumberSelected, tempRotForShape);
        }

        if (toDrawDropAnyShapeButton) {
            //DropAnyShape alpha init
            if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.shapes.size() -1) {
                //a shape is selected
                DropAnyShapeButton.setAlpha(1f);
                isAnyShapeSelected = true;
            }
            else {
                //no shape is selected
                DropAnyShapeButton.setAlpha(0.5f);
                isAnyShapeSelected = false;
            }
        }else {
            DropAnyShapeButton.setAlpha(0f);
        }

        //reintializing the shape position and rotation
        if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size() - 1) {
            VariablesForPlayArea.Sh_pos.set(VariablesForPlayArea.shapeNumberSelected, VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).getPosition());
            VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).setTransform(VariablesForPlayArea.Sh_pos.get(VariablesForPlayArea.shapeNumberSelected), (float) (VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected)* (Math.PI/180)));
        }

        for (int i=0; i<VariablesForPlayArea.CutOutBodies.size(); i++){
            VariablesForPlayArea.Angle_Of_Shape.set(i, (float) (VariablesForPlayArea.CutOutBodies.get(i).getAngle()*(180/Math.PI)));
        }
        //-------------------------------------------

        //size changer for camScroller
        CamScroller.setSize(camScrollSize*camscl, camScrollSize*camscl);
        if (startBool){
            if (brakeBool) {
                //AllVariables.BackWheel.setAngularVelocity(0);
                AllVariables.FrontWheel.setAngularVelocity(0);
            }else{
                if (AllVariables.BackWheel.getAngularVelocity() > -8)
                    AllVariables.BackWheel.setAngularVelocity(-10);
                else
                    AllVariables.BackWheel.setAngularVelocity(AllVariables.BackWheel.getAngularVelocity()-3);
            }
        }

        //System.out.println(isAnyShapeSelected);
        //System.out.println(VariablesForPlayArea.CutOutBodies.get(0).getPosition());


    }

    private void input(float dt){
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            posMap.translate(-5,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            posMap.translate(0,-5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            posMap.translate(5,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            posMap.translate(0,5);
        }
        //System.out.println(posMap.getX()+"\t" + posMap.getY());

        //System.out.println(cam.position.x);
        Gdx.input.setInputProcessor(
                new InputProcessor() {
                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        screenY = Gdx.graphics.getHeight() - screenY;
                        hardmoveFaultResolver = false;
                        if (startBool) {
                            //for brake
                            if (screenX > (1040* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenX < (1230* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenY > 140*AllVariables.inpM && screenY < 290*AllVariables.inpM) {
                                Brake.setAlpha(0.9f);
                                brakeBool = true;
                                return false;
                            }
                        }

                        if(!startBool) {
                            if (!paused) {
                                //hardmove
                                if (screenX > (15 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX < (130 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY > 480 * AllVariables.inpM && screenY < 595 * AllVariables.inpM) {
                                    hardMove = !hardMove;
                                    if (hardMove)
                                        HardMoveShapes.setAlpha(1);
                                    else
                                        HardMoveShapes.setAlpha(0.4f);
                                    hardmoveFaultResolver = true;
                                    return false;
                                }

                                //camScroller
                                if (screenX > (910 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX < (1010 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY > 600 * AllVariables.inpM && screenY < 720 * AllVariables.inpM) {
                                    //System.out.println("omPLan");
                                    isCamScrollerTouched = true;
                                    CamScroller.setAlpha(0.7f);
                                    originX = screenX;
                                    originY = screenY;
                                    dragged_touchX = screenX;

                                }

                                //Drop any shape resolver
                                if (screenX > (25 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX < (125 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY > 415 * AllVariables.inpM && screenY < 470 * AllVariables.inpM) {
                                    VariablesForPlayArea.shapeNumberSelected = 15;
                                }

                                //rotation of shapes
                                if (isAnyShapeSelected) {
                                    //for lock Wise
                                    if (screenX > (1190 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenX < (1250 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenY > 535 * AllVariables.inpM && screenY < 585 * AllVariables.inpM) {

                                        ACWTouched = true;
                                    }

                                    //for anti Clock Wise
                                    else if (screenX > (1190 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenX < (1250 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenY > 370 * AllVariables.inpM && screenY < 420 * AllVariables.inpM) {

                                        CWtouched = true;
                                    }

                                    //for 45 deg rotation
                                    else if (screenX > (1190 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenX < (1250 * AllVariables.inpM) + AllVariables.witdth_translation
                                            && screenY > 455 * AllVariables.inpM && screenY < 505 * AllVariables.inpM) {

                                        //get the present angle of the shape selected
                                        tempRotForShape = VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected);
                                        if (tempRotForShape % 45 == 0) {
                                            tempRotForShape += 45;
                                            if (tempRotForShape >= 360)
                                                tempRotForShape = (short) (tempRotForShape - 360);
                                            VariablesForPlayArea.Angle_Of_Shape.set(VariablesForPlayArea.shapeNumberSelected, tempRotForShape);
                                        } else {
                                            for (int i =1;true;i++){
                                                if ((int)(tempRotForShape / (45*i)) <= 0){
                                                    System.out.println("ok");
                                                    tempRotForShape -= tempRotForShape - (45*(i));
                                                    break;
                                                }
                                            }
                                            if (tempRotForShape >= 360)
                                                tempRotForShape = (short) (tempRotForShape - 360);
                                            VariablesForPlayArea.Angle_Of_Shape.set(VariablesForPlayArea.shapeNumberSelected, tempRotForShape);
                                        }

                                    }
                                }
                            }

                            if (paused){
                                //resume
                                if (screenX > (520 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX < (770 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY > 490 * AllVariables.inpM && screenY < 590 * AllVariables.inpM) {
                                    paused = false;
                                    pause.setAlpha(1);

                                }

                                //exit
                                if (screenX > (520 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenX < (770 * AllVariables.inpM) + AllVariables.witdth_translation
                                        && screenY > 345 * AllVariables.inpM && screenY < 445 * AllVariables.inpM) {
                                    game.setScreen(new LevelNumberSelection(game));

                                }
                            }

                            //pause
                            if (screenX > (30 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX < (130 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY > 600 * AllVariables.inpM && screenY < 700 * AllVariables.inpM) {
                                paused = true;
                                pause.setAlpha(0);

                            }
                        }


                        if (screenX > (45* AllVariables.inpM)+AllVariables.witdth_translation
                                && screenX < (200* AllVariables.inpM)+AllVariables.witdth_translation
                                && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {


                        }else if (screenX > (1040 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1230 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {

                        }else if (screenX > (1115 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1195 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 635* AllVariables.inpM && screenY < 705* AllVariables.inpM) {

                        }else if (screenX > (1200 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1270 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 390 * AllVariables.inpM && screenY < 460 * AllVariables.inpM){

                        } else if (screenX > (1200 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1270 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 515 * AllVariables.inpM && screenY < 590 * AllVariables.inpM){

                        } else if (screenX > (1180 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (1270 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 360 * AllVariables.inpM && screenY < 605 * AllVariables.inpM){

                        } else if (screenX > (20 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenX < (150 * AllVariables.inpM) + AllVariables.witdth_translation
                                && screenY > 580 * AllVariables.inpM && screenY < 920 * AllVariables.inpM){

                        }else if (isCamScrollerTouched || ACWTouched || CWtouched){

                        } else {
                            if (!paused) {
                                if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size() - 1) {
                                    if (hardMove) {
                                        VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).setTransform(
                                                ((((screenX - AllVariables.witdth_translation) / AllVariables.inpM) * camscl + (cam.position.x - AllVariables.WIDTH / 2)) / AllVariables.PPM) - VariablesForPlayArea.BigSqurePoints[0][0] / (2) / AllVariables.PPM,
                                                (((screenY / AllVariables.inpM) * camscl - 200 + (cam.position.y - AllVariables.HEIGHT / 2)) / AllVariables.PPM) + VariablesForPlayArea.BigSqurePoints[12][1] / (2) / AllVariables.PPM,
                                                (float) (VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected) * (Math.PI / 180)));
                                        return false;

                                    } else {
                                        //dragging of objects
                                        originX = screenX;
                                        originY = screenY;
                                        shapeX = VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).getPosition().x;
                                        shapeY = VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).getPosition().y;
                                        return false;
                                    }
                                }
                            }
                        }


                        return false;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        screenY = Gdx.graphics.getHeight() - screenY;


                        if (brakeBool) {
                            Brake.setAlpha(0.4f);
                            brakeBool = false;
                        }

                        if(isCamScrollerTouched) {
                            CamScrollerX = 1020;
                            CamScrollerY = 710;
                            camScrollSize = 100;
                        }

                        if (isCamScrollerTouched) {
                            CamScroller.setAlpha(1f);
                            isCamScrollerTouched = false;
                        }

                        if (ACWTouched)
                            ACWTouched = false;

                        if (CWtouched)
                            CWtouched = false;


                        if (!startBool && !paused){
                            //for start
                            if (screenX > (45* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenX < (200* AllVariables.inpM)+AllVariables.witdth_translation
                                    && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {
                                startAnimToMoveCycle = true;
                                VariablesForPlayArea.endPoint.x = 200;
                                return true;
                            }
                            //shape chooser
                            if (screenX > (1040 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenX < (1230 * AllVariables.inpM) + AllVariables.witdth_translation
                                    && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {
                                //code to choosing body
                                Gdx.input.setInputProcessor(null);
                                game.setScreen(new ShapeChooser(game));
                                return false;
                            }
                        }


                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {
                        screenY = Gdx.graphics.getHeight() - screenY;

                        //this else is placed to prevent drag to happen for cam when touched on cam buttin and if not then the shape drag will happen

                        if (isCamScrollerTouched){
                            dragged_touchX = screenX;

                        }else {
                            if (!paused) {
                                if (!hardmoveFaultResolver) {
                                    if (!hardMove) {
                                        if (VariablesForPlayArea.shapeNumberSelected <= VariablesForPlayArea.CutOutBodies.size() - 1) {
                                            if (!hardMove) {
                                                if (!ACWTouched && !CWtouched) {
                                                    //this is to prevent the ghost movement of the shapes
                                                    //start
                                                    if (screenX > (45* AllVariables.inpM)+AllVariables.witdth_translation
                                                            && screenX < (200* AllVariables.inpM)+AllVariables.witdth_translation
                                                            && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {
                                                    }else{
                                                        //shape chooser
                                                        if (screenX > (1040 * AllVariables.inpM) + AllVariables.witdth_translation
                                                                && screenX < (1230 * AllVariables.inpM) + AllVariables.witdth_translation
                                                                && screenY > 140* AllVariables.inpM && screenY < 290* AllVariables.inpM) {
                                                            // rot per 45 deg
                                                        }else{
                                                            if (screenX > (1190 * AllVariables.inpM) + AllVariables.witdth_translation
                                                                    && screenX < (1250 * AllVariables.inpM) + AllVariables.witdth_translation
                                                                    && screenY > 455 * AllVariables.inpM && screenY < 505 * AllVariables.inpM) {
                                                            }else {
                                                                //dragging statement-------------------------------------------------
                                                                VariablesForPlayArea.CutOutBodies.get(VariablesForPlayArea.shapeNumberSelected).setTransform(((shapeX * AllVariables.PPM) + (screenX - originX)) / 100,
                                                                        ((shapeY * AllVariables.PPM) + (screenY - originY)) / 100,
                                                                        (float) (VariablesForPlayArea.Angle_Of_Shape.get(VariablesForPlayArea.shapeNumberSelected) * (Math.PI / 180)));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }




                        return false;
                    }

                    @Override
                    public boolean keyDown(int keycode) {
                        if (keycode == Input.Keys.Z){
                            game.setScreen(new CuttingAreaManager(game));
                        }
                        if (keycode == Input.Keys.SPACE){
                            world.setGravity(new Vector2(0,-10));
                        }
                        if (keycode == Input.Keys.B){
                            game.setScreen(new LevelNumberSelection(game));
                        }
                        if (keycode == Input.Keys.F){
                            flag.setTexture(new Texture(Gdx.files.internal("playArea/flagRed_down.png")));
                            flag.setSize(flag.getTexture().getWidth(), flag.getTexture().getHeight());

                            //flag.setTexture(new Texture(Gdx.files.internal("playArea/flagRed_down.png")));
                            //flag.setSize(flag.getTexture().getWidth(), flag.getTexture().getHeight());
                        }
                        if (keycode == Input.Keys.E){
                            System.out.println(AllVariables.BackWheel.getPosition().x*AllVariables.PPM +"\t" + AllVariables.BackWheel.getPosition().y*AllVariables.PPM);

                            //flag.setTexture(new Texture(Gdx.files.internal("playArea/flagRed_down.png")));
                            //flag.setSize(flag.getTexture().getWidth(), flag.getTexture().getHeight());
                        }




                        return false;
                    }

                    @Override
                    public boolean keyUp(int keycode) {
                        if (keycode == Input.Keys.SPACE){
                            world.setGravity(new Vector2(0,0));
                        }
                        return false;
                    }

                    @Override
                    public boolean keyTyped(char character) {
                        return false;
                    }

                    @Override
                    public boolean mouseMoved(int screenX, int screenY) {
                        return false;
                    }

                    @Override
                    public boolean scrolled(int amount) {
                        return false;
                    }
                }
        );


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
        world.dispose();
        b2dr.dispose();
        Brake.getTexture().dispose();
        start.getTexture().dispose();
        chooseBody.getTexture().dispose();
        HardMoveShapes.getTexture().dispose();
        CamScroller.getTexture().dispose();
        DropAnyShapeButton.getTexture().dispose();
        ShapeRotACW.getTexture().dispose();
        ShapeRotCW.getTexture().dispose();
        per45degRot.getTexture().dispose();
        sred.dispose();
        map.dispose();
        tmr.dispose();
    }
}
