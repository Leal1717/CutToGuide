package com.kunal;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kunal.MainLoading.MainLoadingScreen;
import com.kunal.temp.temp;

import java.util.LinkedList;
import java.util.Scanner;

public class MainGame extends Game {


	/*
	instagram : https://www.instagram.com/kusaflow/?igshid=8y5b86e3yp6f
	twitter : https://twitter.com/kusaflow?s=08
	youtube Tutorials : https://youtu.be/hD6K6XK_AFY
	 */
    public MainGame() {
    }

    public MainGame(AdVideoInterface adv, openOtherApps openApp, InterstitialAd interstitialAd) {
		AllVariables.adv = adv;
		AllVariables.openApps = openApp;
		AllVariables.InterstitialAdOperator = interstitialAd;
	}

	@Override
	public void create () {
	    AllVariables.batch = new SpriteBatch();
		AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
		AllVariables.witdth_translation = (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
		AllVariables.bitmapFont = new BitmapFont();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter prams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        prams.size = 24;
        prams.color = Color.ORANGE;
        AllVariables.bitmapFont = generator.generateFont(prams);

        //for testing only
		//if (Gdx.files.local("TextFilesToDelete/LevelAreaInfo").exists()) {
		//	Gdx.files.local("TextFilesToDelete/LevelAreaInfo").delete();
		//}

			//creating local file
		if (!Gdx.files.local("TextFilesToDelete/areas/area1").exists()){
			FileHandle savedata = Gdx.files.local("TextFilesToDelete/areas/area1");
			String data = "1\n1\n20\n000000000000000000000000000000\n" + "$";
			savedata.writeString(data,true);
			System.out.println("writing");
		}
		//creating local file
		if (!Gdx.files.local("TextFilesToDelete/areas/area2").exists()){
			FileHandle savedata = Gdx.files.local("TextFilesToDelete/areas/area2");
			String data = "2\n1\n20\n000000000000000000000000000000\n" + "$";
			savedata.writeString(data,true);
			System.out.println("writing");
		}
		//creating local file
		/*if (!Gdx.files.local("TextFilesToDelete/areas/area3").exists()){
			FileHandle savedata = Gdx.files.local("TextFilesToDelete/areas/area3");
			String data = "3\n1\n20\n000000000000000000000000000000\n" + "$";
			savedata.writeString(data,true);
			System.out.println("writing");
		}*/
		//creating local file
		/*if (!Gdx.files.local("TextFilesToDelete/areas/area4").exists()){
			FileHandle savedata = Gdx.files.local("TextFilesToDelete/areas/area4");
			String data = "4\n1\n20\n000000000000000000000000000000\n" + "$";
			savedata.writeString(data,true);
			System.out.println("writing");
		}*/
		//creating local file
		/*if (!Gdx.files.local("TextFilesToDelete/areas/area5").exists()){
			FileHandle savedata = Gdx.files.local("TextFilesToDelete/areas/area5");
			String data = "5\n1\n20\n000000000000000000000000000000\n" + "$";
			savedata.writeString(data,true);
			System.out.println("writing");
		}*/
		//creating local file
		/*if (!Gdx.files.local("TextFilesToDelete/areas/area6").exists()){
			FileHandle savedata = Gdx.files.local("TextFilesToDelete/areas/area6");
			String data = "6\n1\n20\n000000000000000000000000000000\n" + "$";
			savedata.writeString(data,true);
			System.out.println("writing");
		}*/
		//hint logs
		//for area1
		if (!Gdx.files.local("TextFilesToDelete/hints/area1/log").exists()){
			FileHandle savedata = Gdx.files.local("TextFilesToDelete/hints/area1/log");
			String data = "000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000";
			savedata.writeString(data,false);
			System.out.println("writing");
		}

		//area 2 hint 2
		if (!Gdx.files.local("TextFilesToDelete/hints/area2/log").exists()){
			FileHandle savedata = Gdx.files.local("TextFilesToDelete/hints/area2/log");
			String data = "000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000\n" +
					"000";
			savedata.writeString(data,false);
			System.out.println("writing");
		}



		if (!Gdx.files.local("TextFilesToDelete/kusaCoin").exists()){
			FileHandle kusaCoin = Gdx.files.local("TextFilesToDelete/kusaCoin");
			String data = "10";

			kusaCoin.writeString(data, false);
			AllVariables.kusaCoin = 10;
		}else {
			FileHandle kusaCoin = Gdx.files.local("TextFilesToDelete/kusaCoin");
			AllVariables.kusaCoin = new Long(kusaCoin.readString());
		}


		try {
			if (!Gdx.files.local("TextFilesToDelete/followme").exists()) {
				FileHandle follow = Gdx.files.local("TextFilesToDelete/followme");
				String data = "0\n0";
				follow.writeString(data, false);
			} else {
				FileHandle follow = Gdx.files.local("TextFilesToDelete/followme");
				char[] d = follow.readString().toCharArray();
				if (d[0] == '1') {
					AllVariables.showRewardForInsta = false;
				} else {
					AllVariables.showRewardForInsta = true;
				}

				if (d[2] == '1') {
					AllVariables.showRewardFortwitter = false;
				} else {
					AllVariables.showRewardFortwitter = true;
				}
			}
		}catch (Exception e){}

		AllVariables.unlockedCoin = new LinkedList<Byte>();

		AllVariables.unlockedBar = new LinkedList<Byte>();

		AllVariables.unlockedWheel = new LinkedList<Byte>();

		if (!Gdx.files.local("TextFilesToDelete/LockUnlock").exists()){
			FileHandle lu = Gdx.files.local("TextFilesToDelete/LockUnlock");
			String data = "0\n0\n0\n0#\n0#\n0#\n";
			lu.writeString(data, false);
			AllVariables.tyreType = 0;
			AllVariables.coinType = 0;
			AllVariables.bodyOfCycle = 0;
			AllVariables.unlockedBar.add((byte) 0);
			AllVariables.unlockedCoin.add((byte) 0);
			AllVariables.unlockedWheel.add((byte) 0);
		}else {
			FileHandle lu = Gdx.files.local("TextFilesToDelete/LockUnlock");
			char[] data = lu.readString().toCharArray();
			String temp;
			short tracker=0;
			boolean looper;

			//for bar
			looper = true;
			temp = "";
			while (looper){
				if(data[tracker] == '\n'){
					looper = false;
					AllVariables.bodyOfCycle = Byte.valueOf(temp);
				}else {
					temp += data[tracker];
				}
				tracker++;
			}


			//for coin
			looper = true;
			temp = "";
			while (looper){
				if(data[tracker] == '\n'){
					looper = false;
					AllVariables.coinType= Byte.valueOf(temp);
				}else {
					temp += data[tracker];
				}
				tracker++;
			}


			//for wheel
			looper = true;
			temp = "";
			while (looper){
				if(data[tracker] == '\n'){
					looper = false;
					AllVariables.tyreType= Byte.valueOf(temp);
				}else {
					temp += data[tracker];
				}
				tracker++;
			}


			//for unlocked bar
			while (data[tracker] != '\n'){
				temp="";
				while (data[tracker] != '#'){
					temp+=data[tracker];
					tracker++;
				}

				AllVariables.unlockedBar.add(Byte.valueOf(temp));
				temp="";
				tracker++;
			}
			tracker++;

			//for unlocked coin
			while (data[tracker] != '\n'){
				temp="";
				while (data[tracker] != '#'){
					temp+=data[tracker];
					tracker++;
				}

				AllVariables.unlockedCoin.add(Byte.valueOf(temp));
				temp="";
				tracker++;
			}
			tracker++;

			//for unlocked wheel
			while (data[tracker] != '\n'){
				temp="";
				while (data[tracker] != '#'){
					temp+=data[tracker];
					tracker++;
				}

				AllVariables.unlockedWheel.add(Byte.valueOf(temp));
				temp="";
				tracker++;
			}
		}

		if (AllVariables.kusaCoin <= 0){
			AllVariables.kusaCoin *=-1;
		}

		//this.setScreen(new MainLoadingScreen(this));
		this.setScreen(new temp(this));


	}

	@Override
	public void render () {
	    super.render();
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	}

	@Override
	public void dispose () {
		AllVariables.batch.dispose();
		AllVariables.bitmapFont.dispose();


	}
}
