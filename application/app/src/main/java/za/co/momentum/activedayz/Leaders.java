package za.co.momentum.activedayz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Leaders extends AppCompatActivity {
    Button buttonGlobal, buttonComs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaders);

        buttonGlobal = (Button) findViewById(R.id.globalL);
        // Capture button clicks
        buttonGlobal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Leaders.this,
                        LeadersGlobal.class);
                startActivity(myIntent);
            }
        });

        // Locate the button in activity_main.xml
        buttonComs = (Button) findViewById(R.id.comsL);
        // Capture button clicks
        buttonComs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Leaders.this,
                        LeadersComs.class);
                startActivity(myIntent);
            }
        });

    }
}