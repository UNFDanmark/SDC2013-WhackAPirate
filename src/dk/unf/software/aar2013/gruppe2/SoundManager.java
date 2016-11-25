package dk.unf.software.aar2013.gruppe2;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager {
	private Context pContext;		// Local copy of app context
	private SoundPool sndPool;		// Our SoundPool instance
	private float rate = 1.0f;		// Playback rate
	private float masterVolume = 1.0f;	// Master vloume level
	
	public SoundManager(Context appContext)
	{
	  sndPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 0);
	  pContext = appContext;
	}
	
	public int load(int sound_id)
	{
		return sndPool.load(pContext, sound_id, 1);
	}
	
	public void play(int sound_id)
	{
		sndPool.play(sound_id, masterVolume, masterVolume, 1, 0, rate); 	
	}
}
