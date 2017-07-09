package za.co.momentum.activedayz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Azzuen on 2017/07/06.
 */

public class MyComs extends AppCompatActivity {
    Button buttonPanda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycoms);

        // Locate the button in activity_main.xml
        buttonPanda = (Button) findViewById(R.id.panda);
        // Capture button clicks
        buttonPanda.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MyComs.this,
                        Pandas.class);
                startActivity(myIntent);
            }
        });
    }
}
