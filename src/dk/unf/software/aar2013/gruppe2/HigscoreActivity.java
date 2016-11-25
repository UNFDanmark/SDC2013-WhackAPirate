package dk.unf.software.aar2013.gruppe2;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HigscoreActivity extends Activity {
	TextView hiscore;
	Button reset;
	TextView lastscore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_higscore);
		hiscore = (TextView) findViewById(R.id.TextView);
		lastscore = (TextView) findViewById(R.id.last_score);
		reset = (Button) findViewById(R.id.reset);
		final Intent shhse = new Intent (this, SecretActivity.class );

		Button theButton = (Button)findViewById(R.id.secret);
		theButton.setVisibility(View.VISIBLE);
		theButton.setBackgroundColor(Color.TRANSPARENT);

		theButton.setOnClickListener(new OnClickListener()
		{   
		    @Override
		    public void onClick(View v)
		    {
		        startActivity(shhse);
		    }
		});
		
		
		SharedPreferences prefs = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		final ArrayList<Integer> highscores = new ArrayList<Integer>();
		final Editor Edit = prefs.edit();
		for (int i = 0; i<6; i++)
		{
			highscores.add(prefs.getInt("j" + i, 0));
			Edit.commit();
		}
		
		
		
		Collections.sort(highscores);
		Collections.reverse(highscores);
		String string = "";
		for (int i =0;i<5;i++)
		{
			string = string + highscores.get(i) + "\n";
		}
		
		int lasc = prefs.getInt("last",0);
		
		lastscore.setText("your latest score was:"+lasc);
		
		hiscore.setText(string);
		
		
		
		
		
		Edit.commit();
		

		reset.setOnClickListener(new OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				clear();
				Edit.commit();
				hiscore.setText("0\n0\n0\n0\n0");
				lastscore.setText("your latest score was:0");
			}
		});
		
		
	}
	
	public void setHighScore(int score){
		SharedPreferences prefs = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		int i;
		Editor Edit = prefs.edit();
		for(i = 0;prefs.getInt("j"+i, 0) != 0;i++){
			
		}
		if (i >5){
			Edit.putInt("j5", score);
		}
		Edit.putInt("j"+i,score);
		Edit.putInt("last",score);
		Edit.commit();

	}
	
	
	public void clear(){
		SharedPreferences prefs = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Editor Edit = prefs.edit();
		Edit.clear();
		Edit.commit();

	}

}