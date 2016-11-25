package dk.unf.software.aar2013.gruppe2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class pirateFactsActivity extends Activity {
	TextView facts;
	Button more;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.piratefcts);
		facts = (TextView) findViewById(R.id.Tv_piratefacts);
		more = (Button) findViewById(R.id.bt_piratefactsupdate);
		facts.setText(RandpirateFact());
		
		more.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				facts.setText(RandpirateFact());
				
			}
		});
		
	}
	public String RandpirateFact(){
		String Piratefacts[] = {"Only a fraction of the pirates buried their treasures. The Majority actually used their hard earned doubloons.", "Pirates believed that having women on board their ship was bad luck.", "Pirates believed that whistling on a ship would cause the weather to turn stormy (as in 'to whistle up a storm').", "Pirates would take over island ports and make them a safe haven for pirates.", "Although pirates have been around since the 15th century, most pirating happened between 1690 and 1720.", "On the Caribbean island of St Thomas you will find a place called 'Black Beard's Castle’. It is believed that this is where the famous pirate spent many hours looking out for approaching ships.", "Pirates believed that wearing pierced earrings would improve their eyesight.", "Almost all pirates stole their ships because they couldn't buy ships incase they got caught and sent to jail. Once they had taken over a ship they had to convert it for pirate life, this usually meant making more room for sailors to live on board and strengthening the decks to hold the weight of the heavy cannons.", "Ships sailing on their own often sailed close to warships or joined other convoys of ships to protect themselves from pirates. Pirates could only attack one ship at a time, so if the sailors traveled in groups there was less chance of their boat being the one that was attacked.", "Pirate Captain's would change out of their expensive, flashy clothes if there was a chance they might be captured. This way they could pretend they where only one of the crew, and not somebody important and hopefully escape.","The Jolly Roger flag, with its black background and white skull and crossbones, was designed to be scary. This flag was not used by all pirates, usually it was only flown by those sailing in the Spanish Main.","A common disease on a pirate ship was Gangrene. If a pirate got a cut on their arms or legs sometimes it got infected and the skin started to die - this is Gangrene. Then the only way to keep the pirate alive was to saw off the arm or leg.","Scurvy was caused by a lack of vitamin C, which is found in lemons, limes and oranges. Pirates knew they had it when their teeth started falling out, their skin went pale, their legs got very fat and when they had to keep racing off to the toilet. If, on the other hand, they only had a stomach ache and spent all their time going to the toilet, they probably had Dysentery.","Pirates would collect fresh food and drink when they landed, but because they didn't have fridges, freezers, or even cans of Baked Beans, the food had to be eaten quickly once they went back to sea or it would go stale and moldy. In fact if a ship had been at sea for a few months chances are that all the pirates had to eat were stale, dry crackers covered in weevils (a kind of beetle.)"};
		int rand = (int) (Math.random()*Piratefacts.length);
		return Piratefacts[rand];
	}
	
}
