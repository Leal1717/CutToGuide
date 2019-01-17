package com.kunal.CuttingArea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.MainGame;

import java.util.LinkedList;

public class CuttingAreaManager implements Screen {

    MainGame game;
    private OrthographicCamera cam;
    private Viewport port;

    protected LinkedList<Byte> vertices;
    protected LinkedList<LinkedList<Byte>> shapes;

    //minmax to store the values minimum and max value of any row or columb of the selected shape
    //null value is stored when no vertex found on the respective coloum or row
    private LinkedList<MinMaxClass> minmax;


    LinkedList<Byte> inputsToChop;

    int step = 0, t = 0;

    private ShapeRenderer sr;


    // all the points of the big square
    protected int[][] BigSqurePoints = new int[16][2];

    //drawing shapes
    private float ver[];

    private int presentX, presntY;


    public CuttingAreaManager(MainGame game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, AllVariables.WIDTH, AllVariables.HEIGHT);

        port = new FitViewport(AllVariables.WIDTH, AllVariables.HEIGHT, cam);

        sr = new ShapeRenderer();

        //shapes and vertices
        vertices = new LinkedList<Byte>();
        shapes = new LinkedList<LinkedList<Byte>>();

        //inputs to chop
        inputsToChop = new LinkedList<Byte>();

        minmax = new LinkedList<MinMaxClass>();

        //all big squre Points
        BigSqurePoints[0][0] = 550;
        BigSqurePoints[0][1] = 700;
        BigSqurePoints[1][0] = 770;
        BigSqurePoints[1][1] = 700;
        BigSqurePoints[2][0] = 990;
        BigSqurePoints[2][1] = 700;
        BigSqurePoints[3][0] = 1210;
        BigSqurePoints[3][1] = 700;
        BigSqurePoints[4][0] = 550;
        BigSqurePoints[4][1] = 480;
        BigSqurePoints[5][0] = 770;
        BigSqurePoints[5][1] = 480;
        BigSqurePoints[6][0] = 990;
        BigSqurePoints[6][1] = 480;
        BigSqurePoints[7][0] = 1210;
        BigSqurePoints[7][1] = 480;
        BigSqurePoints[8][0] = 550;
        BigSqurePoints[8][1] = 260;
        BigSqurePoints[9][0] = 770;
        BigSqurePoints[9][1] = 260;
        BigSqurePoints[10][0] = 990;
        BigSqurePoints[10][1] = 260;
        BigSqurePoints[11][0] = 1210;
        BigSqurePoints[11][1] = 260;
        BigSqurePoints[12][0] = 550;
        BigSqurePoints[12][1] = 40;
        BigSqurePoints[13][0] = 770;
        BigSqurePoints[13][1] = 40;
        BigSqurePoints[14][0] = 990;
        BigSqurePoints[14][1] = 40;
        BigSqurePoints[15][0] = 1210;
        BigSqurePoints[15][1] = 40;

        /*// for big square
        vertices.add(BigSqurePoints[0][0]); //0
        vertices.add(BigSqurePoints[0][1]);
        vertices.add(BigSqurePoints[3][0]); //3
        vertices.add(BigSqurePoints[3][1]);
        vertices.add(BigSqurePoints[15][0]); //15
        vertices.add(BigSqurePoints[15][1]);
        vertices.add(BigSqurePoints[12][0]); //12
        vertices.add(BigSqurePoints[12][1]);
        */

        vertices.add((byte) 0);
        vertices.add((byte) 1);
        vertices.add((byte) 2);
        vertices.add((byte) 3);
        vertices.add((byte) 7);
        vertices.add((byte) 11);
        vertices.add((byte) 15);
        vertices.add((byte) 14);
        vertices.add((byte) 13);
        vertices.add((byte) 12);
        vertices.add((byte) 8);
        vertices.add((byte) 4);


        shapes.add(vertices);


        //Integer[] data = shapes.get(0).toArray(new Integer[shapes.get(0).size()]);


        //System.out.println(shapes.size());
        //System.out.println(shapes.get(0).size());
    }

    public CuttingAreaManager() {
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0, 0.6f, 1, 1);

        
        for (int i = 0; i < shapes.size(); i++) {
            for (int j = 0, k = 0; j < shapes.get(i).size()-1; j++) {
                sr.rectLine(BigSqurePoints[shapes.get(i).get(j)][0], BigSqurePoints[shapes.get(i).get(j)][1],
                        BigSqurePoints[shapes.get(i).get(j+1)][0], BigSqurePoints[shapes.get(i).get(j+1)][1], 2);
            }
        }


            //sr.setColor(0,1,0.5f,1);
        //sr.rect(BigSqurePoints[12][0], BigSqurePoints[12][1], 660 ,660);
        sr.setColor(1, 0.2f, 0.2f, 1);
        for (int i = 0; i < 16; i++) {
            sr.circle(BigSqurePoints[i][0], BigSqurePoints[i][1], 7);
        }

        sr.setColor(1f, 0f, 0f, 1);
        try {

            for (int i = 0; i < inputsToChop.size() - 1; i++) {
                try {
                    sr.rectLine(BigSqurePoints[inputsToChop.get(i)][0], BigSqurePoints[inputsToChop.get(i)][1],
                            BigSqurePoints[inputsToChop.get(i + 1)][0], BigSqurePoints[inputsToChop.get(i + 1)][1], 5);
                } catch (Exception e) {
                }
            }
            sr.rectLine(BigSqurePoints[inputsToChop.getLast()][0], BigSqurePoints[inputsToChop.getLast()][1], presentX, AllVariables.HEIGHT - presntY, 5);

        } catch (Exception e) {
        }

        sr.end();

    }

    private void update(float dt) {
        input(dt);
        try {
            cam.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sr.setProjectionMatrix(cam.combined);

//        for (int i = 0; i < shapes.size(); i++) {
//            ver = new float[shapes.get(i).size() * 2];
//            for (int j = 0, k = 0; j < shapes.get(i).size(); j++) {
//                ver[k] = BigSqurePoints[shapes.get(i).get(j)][0];
//                k++;
//                ver[k] = BigSqurePoints[shapes.get(i).get(j)][1];
//                k++;
//            }
//            ver = null;
//
//        }


    }

    private void input(float dt) {
        Gdx.input.setInputProcessor(
                new InputProcessor() {

                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        inputsToChop.clear();
                        inputsToChop.add((byte) 20);

                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {

                        presentX = screenX;
                        presntY = screenY;

                        if (screenX > BigSqurePoints[0][0] - 25 && screenX < BigSqurePoints[0][0] + 25) {
                            if (screenY > AllVariables.HEIGHT - (BigSqurePoints[0][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[0][1] - 25)) {
                                if (inputsToChop.getLast() != 0)
                                    inputsToChop.add((byte) 0);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[4][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[4][1] - 25)) {
                                if (inputsToChop.getLast() != 4)
                                    inputsToChop.add((byte) 4);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[8][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[8][1] - 25)) {
                                if (inputsToChop.getLast() != 8)
                                    inputsToChop.add((byte) 8);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[12][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[12][1] - 25)) {
                                if (inputsToChop.getLast() != 12)
                                    inputsToChop.add((byte) 12);
                            }
                        } else if (screenX > BigSqurePoints[1][0] - 25 && screenX < BigSqurePoints[1][0] + 25) {
                            if (screenY > AllVariables.HEIGHT - (BigSqurePoints[1][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[1][1] - 25)) {
                                if (inputsToChop.getLast() != 1)
                                    inputsToChop.add((byte) 1);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[5][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[5][1] - 25)) {
                                if (inputsToChop.getLast() != 5)
                                    inputsToChop.add((byte) 5);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[9][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[9][1] - 25)) {
                                if (inputsToChop.getLast() != 9)
                                    inputsToChop.add((byte) 9);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[13][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[13][1] - 25)) {
                                if (inputsToChop.getLast() != 13)
                                    inputsToChop.add((byte) 13);
                            }
                        } else if (screenX > BigSqurePoints[2][0] - 25 && screenX < BigSqurePoints[2][0] + 25) {
                            if (screenY > AllVariables.HEIGHT - (BigSqurePoints[2][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[2][1] - 25)) {
                                if (inputsToChop.getLast() != 2)
                                    inputsToChop.add((byte) 2);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[6][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[6][1] - 25)) {
                                if (inputsToChop.getLast() != 6)
                                    inputsToChop.add((byte) 6);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[10][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[10][1] - 25)) {
                                if (inputsToChop.getLast() != 10)
                                    inputsToChop.add((byte) 10);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[14][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[14][1] - 25)) {
                                if (inputsToChop.getLast() != 14)
                                    inputsToChop.add((byte) 14);
                            }
                        } else if (screenX > BigSqurePoints[3][0] - 25 && screenX < BigSqurePoints[3][0] + 25) {
                            if (screenY > AllVariables.HEIGHT - (BigSqurePoints[3][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[3][1] - 25)) {
                                if (inputsToChop.getLast() != 3)
                                    inputsToChop.add((byte) 3);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[7][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[7][1] - 25)) {
                                if (inputsToChop.getLast() != 7)
                                    inputsToChop.add((byte) 7);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[11][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[11][1] - 25)) {
                                if (inputsToChop.getLast() != 11)
                                    inputsToChop.add((byte) 11);
                            } else if (screenY > AllVariables.HEIGHT - (BigSqurePoints[15][1] + 25) && screenY < AllVariables.HEIGHT - (BigSqurePoints[15][1] - 25)) {
                                if (inputsToChop.getLast() != 15)
                                    inputsToChop.add((byte) 15);
                            }
                        }

                        return false;
                    }


                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        //remove first element
                        try {
                            inputsToChop.removeFirst();
                            if (inputsToChop.getLast() != inputsToChop.getFirst())
                                inputsToChop.addLast(inputsToChop.getFirst());
                            presentX = BigSqurePoints[inputsToChop.getLast()][0];
                            presntY = AllVariables.HEIGHT - BigSqurePoints[inputsToChop.getLast()][1];

                        } catch (Exception e) {
                        }


                        if (inputsToChop.isEmpty())
                            return false;

                        fillingMissingPoints();

                        // if internal cutting is done function is returned

                        cuttingTheParts();

                        inputsToChop.clear();

                        return false;
                    }


                    @Override
                    public boolean keyDown(int keycode) {
                        return false;
                    }

                    @Override
                    public boolean keyUp(int keycode) {
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

    private void fillingMissingPoints() {

        //refilling the unconsistancies
        for (int i = 1; i < inputsToChop.size(); i++) {

            //upside down cutting
            if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) % 4 == 0) {
                //up to down
                if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 4 >= 2) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) - 4));
                    i--;
                    continue;
                } else if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 4 <= -2) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) + 4));
                    i--;
                    continue;
                }
            }

            //cross left to right
            if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) % 5 == 0) {
                //upleft to downright
                if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 5 >= 2 && BigSqurePoints[inputsToChop.get(i)][0] > BigSqurePoints[inputsToChop.get(i - 1)][0]
                        && BigSqurePoints[inputsToChop.get(i)][1] < BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) - 5));
                    i--;
                    continue;
                }
                //downright to up left
                else if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 5 <= -2 && BigSqurePoints[inputsToChop.get(i)][0] < BigSqurePoints[inputsToChop.get(i - 1)][0]
                        && BigSqurePoints[inputsToChop.get(i)][1] > BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) + 5));
                    i--;
                    continue;
                }
            }

            //cross right to left
            if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) % 3 == 0) {
                //upright to downleft
                if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 3 >= 2 && BigSqurePoints[inputsToChop.get(i)][0] < BigSqurePoints[inputsToChop.get(i - 1)][0]
                        && BigSqurePoints[inputsToChop.get(i)][1] < BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) - 3));
                    i--;
                    continue;
                }
                //downleft to upright
                else if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) / 3 <= -2 && BigSqurePoints[inputsToChop.get(i)][0] > BigSqurePoints[inputsToChop.get(i - 1)][0]
                        && BigSqurePoints[inputsToChop.get(i)][1] > BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    inputsToChop.add(i, (byte) (inputsToChop.get(i) + 3));
                    i--;
                    continue;
                }
            }

            //sideways
            if (((inputsToChop.get(i) - inputsToChop.get(i - 1)) >= 2 && (inputsToChop.get(i) - inputsToChop.get(i - 1)) <= 4)
                    || ((inputsToChop.get(i) - inputsToChop.get(i - 1)) <= -2 && (inputsToChop.get(i) - inputsToChop.get(i - 1)) >= -4)) {
                if (BigSqurePoints[inputsToChop.get(i)][1] == BigSqurePoints[inputsToChop.get(i - 1)][1]) {
                    if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) >= 2) {
                        inputsToChop.add(i, (byte) (inputsToChop.get(i) - 1));
                        i--;
                        continue;
                    } else if ((inputsToChop.get(i) - inputsToChop.get(i - 1)) <= -2) {
                        inputsToChop.add(i, (byte) (inputsToChop.get(i) + 1));
                        i--;
                        continue;
                    }
                }
            }
        }
    }

    private void minmaxAssignment(int shapeNumber) {
        //shapes.get(shapeNumber)
        //order for putting is v1 v2 v3 v4 h1 h2 h3 h4
        //--------------------------------------
        //for v1 the fixed axis is x(550) axis and y axis is variable
        //--------------------------------------
        //for v2 the fixed axis is x(770) axis and y axis is variable
        //--------------------------------------
        //for v3 the fixed axis is x(990) axis and y axis is variable
        //--------------------------------------
        //for v4 the fixed axis is x(1210) axis and y axis is variable
        //--------------------------------------
        //for h1 the variable axis is xaxis and fixed axis is y(40) axis is variable
        //--------------------------------------
        //for h2 the variable axis is xaxis and fixed axis is y(260) axis is variable
        //--------------------------------------
        //for h3 the variable axis is xaxis and fixed axis is y(480) axis is variable
        //--------------------------------------
        //for h4 the variable axis is xaxis and fixed axis is y(700) axis is variable
        //--------------------------------------

        minmax.clear();
        MinMaxClass minmax1, minmax2, minmax3, minmax4;

        minmax1 = new MinMaxClass();
        minmax2 = new MinMaxClass();
        minmax3 = new MinMaxClass();
        minmax4 = new MinMaxClass();

        //v1 v2 v3 v4
        for (byte i : shapes.get(shapeNumber)) {
            //v1
            if (BigSqurePoints[i][0] == 550) {
                if (minmax1.min == null && minmax1.max == null) {
                    minmax1.min = i;
                    minmax1.max = i;
                }
                if (i > minmax1.max)
                    minmax1.max = i;

                if (i < minmax1.min)
                    minmax1.min = i;
            }

            //v2
            if (BigSqurePoints[i][0] == 770) {
                if (minmax2.min == null && minmax2.max == null) {
                    minmax2.min = i;
                    minmax2.max = i;
                }
                if (i > minmax2.max)
                    minmax2.max = i;

                if (i < minmax2.min)
                    minmax2.min = i;
            }

            //v3
            if (BigSqurePoints[i][0] == 990) {
                if (minmax3.min == null && minmax3.max == null) {
                    minmax3.min = i;
                    minmax3.max = i;
                }
                if (i > minmax3.max)
                    minmax3.max = i;

                if (i < minmax3.min)
                    minmax3.min = i;
            }

            //v4
            if (BigSqurePoints[i][0] == 1210) {
                if (minmax4.min == null && minmax4.max == null) {
                    minmax4.min = i;
                    minmax4.max = i;
                }
                if (i > minmax4.max)
                    minmax4.max = i;

                if (i < minmax4.min)
                    minmax4.min = i;
            }

        }

        minmax.add(minmax1);
        minmax.add(minmax2);
        minmax.add(minmax3);
        minmax.add(minmax4);

        minmax1 = new MinMaxClass();
        minmax2 = new MinMaxClass();
        minmax3 = new MinMaxClass();
        minmax4 = new MinMaxClass();

        //h1 h2 h3 h4
        for (byte i : shapes.get(shapeNumber)) {
            //v1
            if (BigSqurePoints[i][1] == 700) {
                if (minmax1.min == null && minmax1.max == null) {
                    minmax1.min = i;
                    minmax1.max = i;
                }
                if (i > minmax1.max)
                    minmax1.max = i;

                if (i < minmax1.min)
                    minmax1.min = i;
            }

            //v2
            if (BigSqurePoints[i][1] == 480) {
                if (minmax2.min == null && minmax2.max == null) {
                    minmax2.min = i;
                    minmax2.max = i;
                }
                if (i > minmax2.max)
                    minmax2.max = i;

                if (i < minmax2.min)
                    minmax2.min = i;
            }

            //v3
            if (BigSqurePoints[i][1] == 260) {
                if (minmax3.min == null && minmax3.max == null) {
                    minmax3.min = i;
                    minmax3.max = i;
                }
                if (i > minmax3.max)
                    minmax3.max = i;

                if (i < minmax3.min)
                    minmax3.min = i;
            }

            //v4
            if (BigSqurePoints[i][1] == 40) {
                if (minmax4.min == null && minmax4.max == null) {
                    minmax4.min = i;
                    minmax4.max = i;
                }
                if (i > minmax4.max)
                    minmax4.max = i;

                if (i < minmax4.min)
                    minmax4.min = i;
            }

        }

        minmax.add(minmax1);
        minmax.add(minmax2);
        minmax.add(minmax3);
        minmax.add(minmax4);

    }

    public void cuttingTheParts() {
        //identifying the shape on which cutting is executed
//
//        int numberofOverLap;
//
//        for (int i=0; i<shapes.size(); i++){
//            numberofOverLap = 0;
//            for (int j=0; j<shapes.get(i).size(); j++){
//
//            }
//
//        }
        int shapen = 0;
//


        //min max
        minmaxAssignment(shapen);

        //true false for min max
        for (int i = 0; i < inputsToChop.size(); i++) {
            for (int j = 0; j < minmax.size(); j++) {
                if (minmax.get(j).max == inputsToChop.get(i)) {
                    minmax.get(j).ismaxOverlap = true;
                }
                if (minmax.get(j).min == inputsToChop.get(i)) {
                    minmax.get(j).isminOverlap = true;
                }
            }

        }

        vertices = new LinkedList<Byte>();

        step = 1;

        //step 1 is vin             2 is hmax           3 is vmax       4 is hmin

        //if cutting type 1 is implemented
        //which is using one side of parent shape and not cutting with single line or 2 cuts with one pattern
        for (int i = 0; true; i++) {
            if (step == 1) {
                if (!minmax.get(i).isminOverlap)
                    vertices.add(minmax.get(i).min);
                else {
                    if (i + 1 == 4) {
                        i += 2;
                        if (minmax.get(i - 2).ismaxOverlap)
                            cutShape(i, false);
                        else
                            vertices.add(minmax.get(i).min);
                        i -= 2;
                    } else {
                        if (minmax.get(i + 1).isminOverlap)
                            cutShape(i, false);
                        else
                            vertices.add(minmax.get(i).min);
                    }
                }


                if (i == 3) {
                    step = 2;
                    i++;
                    continue;
                } else {
                    continue;
                }
            }

            if (step == 2) {
                if (!minmax.get(i).ismaxOverlap)
                    vertices.add(minmax.get(i).max);
                else {
                    if (i + 1 == 8) {
                        t = 2;
                        if (minmax.get(t).ismaxOverlap)
                            cutShape(i, true);
                        else
                            vertices.add(minmax.get(i).max);
                    } else {
                        if (minmax.get(i + 1).ismaxOverlap)
                            cutShape(i, true);
                        else
                            vertices.add(minmax.get(i).max);
                    }
                }
                if (i == 7) {
                    step = 3;
                    i = 2;
                } else {
                    continue;
                }
            }

            if (step == 3) {
                if (!minmax.get(i).ismaxOverlap)
                    vertices.add(minmax.get(i).max);
                else {
                    if (i - 1 == -1) {
                        t = 6;
                        if (minmax.get(t).isminOverlap)
                            cutShape(i, true);
                        else
                            vertices.add(minmax.get(i).max);
                    } else {
                        if (minmax.get(i - 1).ismaxOverlap)
                            cutShape(i, true);
                        else
                            vertices.add(minmax.get(i).max);
                    }
                }
                if (i == 0) {
                    step = 4;
                    i = 6;
                } else {
                    i -= 2;
                    continue;
                }
            }

            if (step == 4) {
                if (!minmax.get(i).isminOverlap)
                    vertices.add(minmax.get(i).min);
                else {
                    if (minmax.get(i - 1).isminOverlap)
                        cutShape(i, false);
                    else
                        vertices.add(minmax.get(i).min);

                }
                if (i == 5) {
                    break;
                } else {
                    i -= 2;
                }
            }
        }

        //shapes.remove(shapen);
        shapes.add(inputsToChop);
        shapes.add(vertices);

        //System.out.println(shapes);





    }

    private void cutShape(int index, boolean ismax) {

    }

    private void cutShapea(int index, boolean ismax) {

        if (ismax)
            vertices.add(minmax.get(index).max);
        else
            vertices.add(minmax.get(index).min);
        int n = index;

        boolean isforward;

        for (int i =0; true; i++){
            if (step ==1 || step == 4) {
                if (inputsToChop.get(i) == minmax.get(i).min) {
                    if (inputsToChop.get(i + 1) == minmax.get(i + 1).min) {
                        isforward = false;
                    } else {
                        isforward = true;
                    }
                    break;
                }
            }

            if (step ==2 || step == 3) {
                if (inputsToChop.get(i) == minmax.get(i).max) {
                    if (inputsToChop.get(i + 1) == minmax.get(i + 1).max) {
                        isforward = false;
                    } else {
                        isforward = true;
                    }
                    break;
                }
            }
        }

        if (!isforward){

        }
    }


    @Override
    public void resize(int width, int height) {
        port.update(width, height);
        cam.update();
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
