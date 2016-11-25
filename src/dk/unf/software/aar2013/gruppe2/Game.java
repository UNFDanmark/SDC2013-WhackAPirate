package dk.unf.software.aar2013.gruppe2;

import android.util.Log;

public class Game {

	private int highestStreak = 0;
	private int moleAccChoose = 8;
	private int moleActiveTime = 1000;
	private int moleRespawnTime;
	private int score = 0;
	private int scoreNum = 100;
	private int streak = 0;

	public void addScore() {
		score = score + scoreNum;

		if (streak % 5 == 0) {
			scoreNum = 100 + streak * 10;

		}

		if (highestStreak < streak) {
			highestStreak = streak;
		}

	}

	public int chooseMolePosition() {

		int random = (int) ((Math.random() * 9) + 1);

		Log.d("60", "random: " + random);

		return random;

	}

	public int getScore() {
		return score;
	}

	public int getStreak() {
		return streak;
	}

	public void addToStreak() {
		streak++;

	}

	public void resetStreak() {
		streak = 0;

		scoreNum = 100;

		moleActiveTime = 1000;

	}

	public int getMaxStreak() {
		return highestStreak;
	}

	public int getMoleActiveTime() {
		return moleActiveTime;
	}

	public int getMoleRespawnTime() {

		switch (streak) {

		case 5:
			moleAccChoose = 6;
			break;
		case 10:
			moleAccChoose = 5;
			break;
		case 15:
			moleAccChoose = 4;
			break;
		case 20:
			moleAccChoose = 3;
			break;
		case 30:
			moleAccChoose = 2;
			break;
		case 40:
			moleAccChoose = 1;
			break;

		}

		moleRespawnTime = ((int) (Math.random() * 2) + moleAccChoose) * 100;

		return moleRespawnTime;
	}

	public void moleTimeShortner() {
		if (streak % 5 == 0 && moleActiveTime > 500) {
			moleActiveTime = moleActiveTime - 100;

		}

	}


}
