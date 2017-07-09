package za.co.momentum.activedayz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Community extends AppCompatActivity {
    Button buttonMy, buttonCreate, buttonJoin, buttonInv, buttonChallenge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        // Locate the button in activity_main.xml
        buttonMy = (Button) findViewById(R.id.my_coms);
        // Capture button clicks
        buttonMy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Community.this,
                        MyComs.class);
                startActivity(myIntent);
            }
        });

        // Locate the button in activity_main.xml
        buttonCreate = (Button) findViewById(R.id.create);
        // Capture button clicks
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Community.this,
                        CreateComs.class);
                startActivity(myIntent);
            }
        });

        // Locate the button in activity_main.xml
        buttonJoin = (Button) findViewById(R.id.join);
        // Capture button clicks
        buttonJoin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Community.this,
                        JoinComs.class);
                startActivity(myIntent);
            }
        });

        // Locate the button in activity_main.xml
        buttonInv = (Button) findViewById(R.id.invite);
        // Capture button clicks
        buttonInv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Community.this,
                        InviteComs.class);
                startActivity(myIntent);
            }
        });

        // Locate the button in activity_main.xml
        buttonChallenge = (Button) findViewById(R.id.challenge);
        // Capture button clicks
        buttonChallenge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Community.this,
                        ChallengeComs.class);
                startActivity(myIntent);
            }
        });



    }
}