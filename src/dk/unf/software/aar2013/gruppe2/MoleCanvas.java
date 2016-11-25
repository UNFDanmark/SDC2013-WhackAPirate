package dk.unf.software.aar2013.gruppe2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MoleCanvas extends View implements OnTouchListener {

	// GameOver gameOver = new GameOver();

	Bitmap barrel;
	private float barrelRadius;
	private float barrelX1;
	private float barrelX2;
	private float barrelX3;
	private float barrelY1;
	private float barrelY2;
	private float barrelY3;
	int bonusTime = 0;
	private int displayX;
	private int displayY;
	long elapsedTimeMillis;
	float elapsedTimeSec;
	Game game;
	private boolean isMoleActive = false;
	private boolean isMoleTouched = false;
	private int molePos = 0;
	Paint paint = new Paint();
	Paint paint2 = new Paint();
	Bitmap pirate;
	long startTime = System.currentTimeMillis();
	String string1 = "";
	String string2 = "";
	String string3 = "";
	Typeface tf = Typeface.create("Times New Roman", 1);
	int time = 59;
	int time2 = 59;
	private int touchX;
	private int touchY;
	Bitmap woodBG;
	Context mContext;
	Intent gameEnded;
//	SoundManager snd;
//	int punch1,punch2,punch3, punch4;

	public MoleCanvas(Context context) {
		super(context);
		setOnTouchListener(this);
		game = new Game();
		barrel = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.barrel);
		pirate = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.wacky);
		woodBG = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.woodgame);

		barrelRadius = barrel.getHeight() / 2;
		
		mContext = context;
		
//		snd = new SoundManager(mContext);
//		
//		punch1 = snd.load(R.raw.punch1);
//		punch2 = snd.load(R.raw.punch2);
//		punch3 = snd.load(R.raw.punch3);
//		punch4 = snd.load(R.raw.punch4);

		Log.d("60", "Starting TimeUpdate()");

		new TimerThread().start();
		new MoleThread().start();
		

	}

	public void onDraw(Canvas canvas) {

		displayY = canvas.getHeight();
		displayX = canvas.getWidth();

		barrelX1 = (float) ((displayX / 6) - barrelRadius);
		barrelX2 = (float) ((displayX / 6) * 3 - barrelRadius);
		barrelX3 = (float) ((displayX / 6) * 5 - barrelRadius);
		barrelY1 = (float) ((displayX / 6) + ((displayY - displayX) / 2) - barrelRadius);
		barrelY2 = (float) ((displayX / 6) * 3 + ((displayY - displayX) / 2) - barrelRadius);
		barrelY3 = (float) ((displayX / 6) * 5 + ((displayY - displayX) / 2) - barrelRadius);

		// man kan finde middten af billedet og plasere billedet på samme måde
		// som man plaserer cirkler på
		// ved at tage barrel.gethight() og tage halvdelen af det for at finde
		// midten af højden og det samme med getwidth for midten af billdedet på
		// y

		paint.setColor(Color.YELLOW);

		// bg
		canvas.drawBitmap(woodBG, 0, 0, null);

		// draw barrels
		canvas.drawBitmap(barrel, barrelX1, barrelY1, null);
		canvas.drawBitmap(barrel, barrelX2, barrelY1, null);
		canvas.drawBitmap(barrel, barrelX3, barrelY1, null);
		canvas.drawBitmap(barrel, barrelX1, barrelY2, null);
		canvas.drawBitmap(barrel, barrelX2, barrelY2, null);
		canvas.drawBitmap(barrel, barrelX3, barrelY2, null);
		canvas.drawBitmap(barrel, barrelX1, barrelY3, null);
		canvas.drawBitmap(barrel, barrelX2, barrelY3, null);
		canvas.drawBitmap(barrel, barrelX3, barrelY3, null);

		Log.d("60", "draw at " + molePos);
		switch (molePos) {
		case 1:
			canvas.drawBitmap(pirate, barrelX1, barrelY1, null);
			break;
		case 2:
			canvas.drawBitmap(pirate, barrelX2, barrelY1, null);
			break;
		case 3:
			canvas.drawBitmap(pirate, barrelX3, barrelY1, null);
			break;
		case 4:
			canvas.drawBitmap(pirate, barrelX1, barrelY2, null);
			break;
		case 5:
			canvas.drawBitmap(pirate, barrelX2, barrelY2, null);
			break;
		case 6:
			canvas.drawBitmap(pirate, barrelX3, barrelY2, null);
			break;
		case 7:
			canvas.drawBitmap(pirate, barrelX1, barrelY3, null);
			break;
		case 8:
			canvas.drawBitmap(pirate, barrelX2, barrelY3, null);
			break;
		case 9:
			canvas.drawBitmap(pirate, barrelX3, barrelY3, null);
			break;

		}

		//
		paint.setTextSize((float) (displayY / 30));
		paint.setTypeface(tf);
		paint.setColor(Color.WHITE);

		// canvas.drawText("Streak: "+game.getStreak(), 20, 30, paint);
		//
		// canvas.drawText("StreakMax: "+game.getMaxStreak(), 150, 30, paint);
		//
		// canvas.drawText("moleRespawnTime: " + game.getMoleRespawnTime(), 20,
		// 70, paint);
		//
		// canvas.drawText("Score: " + game.getScore(), 20, 110, paint);

		canvas.drawText("Score:", barrelX1 + (barrelRadius / 2),
				(displayY / 10) / 2, paint);
		canvas.drawText("" + game.getScore(), barrelX1 + (barrelRadius / 2),
				(displayY / 10), paint);

		canvas.drawText("Time:", barrelX2 + (barrelRadius / 2),
				(displayY / 10) / 2, paint);
		canvas.drawText("" + time2, barrelX2 + (barrelRadius / 2),
				(displayY / 10), paint);

		canvas.drawText("Streak:", barrelX3 + (barrelRadius / 2),
				(displayY / 10) / 2, paint);
		canvas.drawText("" + game.getStreak(), barrelX3 + (barrelRadius / 2),
				(displayY / 10), paint);

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		touchX = (int) (event.getX());
		touchY = (int) (event.getY());
		int action = event.getAction();

		if (action == MotionEvent.ACTION_DOWN) {

			// row 1
			if (touchX >= barrelX1 && touchX < barrelX1 + barrel.getWidth()
					&& touchY >= barrelY1
					&& touchY < barrelY1 + barrel.getHeight()) {

				onMoleTouch(1);
			}

			if (touchX >= barrelX2 && touchX < barrelX2 + barrel.getWidth()
					&& touchY >= barrelY1
					&& touchY < barrelY1 + barrel.getHeight()) {

				onMoleTouch(2);

			}

			if (touchX >= barrelX3 && touchX < barrelX3 + barrel.getWidth()
					&& touchY >= barrelY1
					&& touchY < barrelY1 + barrel.getHeight()) {

				onMoleTouch(3);
			}

			// row 2
			if (touchX >= barrelX1 && touchX < barrelX1 + barrel.getWidth()
					&& touchY >= barrelY2
					&& touchY < barrelY2 + barrel.getHeight()) {

				onMoleTouch(4);
			}

			if (touchX >= barrelX2 && touchX < barrelX2 + barrel.getWidth()
					&& touchY >= barrelY2
					&& touchY < barrelY2 + barrel.getHeight()) {

				onMoleTouch(5);
			}

			if (touchX >= barrelX3 && touchX < barrelX3 + barrel.getWidth()
					&& touchY >= barrelY2
					&& touchY < barrelY2 + barrel.getHeight()) {

				onMoleTouch(6);
			}

			// row 3
			if (touchX >= barrelX1 && touchX < barrelX1 + barrel.getWidth()
					&& touchY >= barrelY3
					&& touchY < barrelY3 + barrel.getHeight()) {

				onMoleTouch(7);
			}

			if (touchX >= barrelX2 && touchX < barrelX2 + barrel.getWidth()
					&& touchY >= barrelY3
					&& touchY < barrelY3 + barrel.getHeight()) {

				onMoleTouch(8);
			}

			if (touchX >= barrelX3 && touchX < barrelX3 + barrel.getWidth()
					&& touchY >= barrelY3
					&& touchY < barrelY3 + barrel.getHeight()) {

				onMoleTouch(9);
			}

			string2 = "" + touchY;
			string3 = "" + touchX;
			invalidate();
		}
		return true;
	}

	public void onMoleTouch(int pos) {
		if (isMoleActive && molePos == pos) {
			isMoleActive = false;
			molePos = 0;
			game.addToStreak();
			game.addScore();
			
//			int i = (int) ((Math.random()*4)+1);
//			int id = 0;
//			if(i == 1){
//				id = punch1;
//			}
//			if(i == 1){
//				id = punch2;
//			}
//			if(i == 1){
//				id = punch3;
//			}
//			if(i == 1){
//				id = punch4;
//			}
//			
//			snd.play(id);
		}

	}

	public int getTimeElapsed() {
		elapsedTimeMillis = System.currentTimeMillis() - startTime;
		elapsedTimeSec = elapsedTimeMillis / 1000;
		return (int) elapsedTimeSec;
	}

	class MoleThread extends Thread {

		public void run() {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			while (getTimeElapsed() < time) {

				if (!isMoleActive) {
					Log.d("60", "isMoleActive = true");
					isMoleActive = true;

					molePos = game.chooseMolePosition();
					postInvalidate();

					Log.d("60", "Starting try/catch for Thread.sleep(1000)");
					try {
						Thread.sleep(game.getMoleActiveTime());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					Log.d("60", "isMoleActive = false");

					game.moleTimeShortner();

					if (isMoleActive) {
						game.resetStreak();
					}

					isMoleActive = false;
					molePos = 0;
					postInvalidate();
					Log.d("60", "Starting try/catch for Thread.sleep(500)");
					try {
						Thread.sleep(game.getMoleRespawnTime());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	class TimerThread extends Thread {

		public void run() {
			while (getTimeElapsed() < time) {
				if (game.getStreak() == 30) {
					bonusTime = bonusTime + 30;
				}

				if (game.getStreak() % 10 == 0 && game.getStreak() > 30) {
					bonusTime = bonusTime + 10;
				}
				time = 59 + bonusTime;
				time2 = 59 + bonusTime - getTimeElapsed();
				postInvalidate();

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			/*
			 * SharedPreferences prefs = (SharedPreferences) PreferenceManager
			 * .getDefaultSharedPreferences(mContext); int i; Editor Edit =
			 * prefs.edit(); for (i = 0; prefs.getInt("j" + i, 0) != 0; i++) {
			 * 
			 * } Edit.putInt("j" + i, game.getScore()); Edit.putInt("last",
			 * game.getScore()); Edit.commit();
			 */
			SharedPreferences prefs = (SharedPreferences) PreferenceManager
					.getDefaultSharedPreferences(mContext);
			int i;
			Editor Edit = prefs.edit();
			for (i = 0; prefs.getInt("j" + i, 0) != 0; i++) {

			}
			if (i > 5) {
				Edit.putInt("j5", game.getScore());
			}
			Edit.putInt("j" + i, game.getScore());
			Edit.putInt("last", game.getScore());
			Edit.commit();

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			gameEnded = new Intent(mContext, HigscoreActivity.class);
			mContext.startActivity(gameEnded);

		}

	}

}
