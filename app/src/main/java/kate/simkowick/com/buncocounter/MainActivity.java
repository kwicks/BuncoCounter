package kate.simkowick.com.buncocounter;

// counter to help you keep track of the bunco score
//  need to implement undo, improve look, put customizing in menu, add color schemes to choose from

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Element;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import javax.xml.xpath.XPath;

public class MainActivity extends AppCompatActivity {

    public int usPoints = 0;
    public int themPoints = 0;
    public int buncoPointsValue = 21;
    public int newInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //allows class to except extra information from customBunco.java
        Bundle buncoData = getIntent().getExtras();
        if(buncoData== null) {
            return;
        }

        String buncoMessage = buncoData.getString("buncoMessage");
        final TextView customText = (TextView) findViewById(R.id.customText);
        customText.setText(buncoMessage);

        if(buncoMessage != null){
            //if(customText != null && !customText.trim().isEmpty()){
            int newInt = Integer.parseInt(buncoMessage);

            buncoPointsValue = newInt;
        }
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame(view);

            }
        });

        FloatingActionButton pref = (FloatingActionButton) findViewById(R.id.pref);
        pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchScreenCustomPoints(view);

            }
        });*/


    }

    public void addUsBunco(View view){
        usPoints = usPoints + buncoPointsValue;
        if(usPoints > 99) {
           // change textAppearance to ="@android:style/TextAppearance.Material.Display2"
        }
        final TextView usScore = (TextView) findViewById(R.id.usScore);
        usScore.setText(String.valueOf(usPoints));
        logPointsAdded(view);
    }

    public void addFiveUsScore(View view){
        usPoints = usPoints + 5;
        final TextView usScore = (TextView) findViewById(R.id.usScore);
        usScore.setText(String.valueOf(usPoints));
    }

    public void addOneUsScore(View view){
        usPoints = usPoints + 1;
        final TextView usScore = (TextView) findViewById(R.id.usScore);
        usScore.setText(String.valueOf(usPoints));
    }

    public void addThemBunco(View view){
        themPoints = themPoints + buncoPointsValue;
        final TextView themScore = (TextView) findViewById(R.id.themScore);
        themScore.setText(String.valueOf(themPoints));
    }

    public void addFiveThemScore(View view){
        themPoints = themPoints + 5;
        final TextView themScore = (TextView) findViewById(R.id.themScore);
        themScore.setText(String.valueOf(themPoints));
    }

    public void addOneThemScore(View view){
        themPoints = themPoints + 1;
        final TextView themScore = (TextView) findViewById(R.id.themScore);
        themScore.setText(String.valueOf(themPoints));
    }

    public void resetGame(View view){
        usPoints = 0;
        themPoints = 0;
        final TextView usScore = (TextView) findViewById(R.id.usScore);
        usScore.setText(String.valueOf(usPoints));
        final TextView themScore = (TextView) findViewById(R.id.themScore);
        themScore.setText(String.valueOf(themPoints));
    }

    public void logPointsAdded(View view){
        // track points added to us or them so can undo if wanted
        Snackbar.make(view, "Log coming soon", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void undoLast(View view){
        // track points added to us or them so can undo if wanted
        // if undoLast clicked

        Snackbar.make(view, "Undo feature coming soon", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void switchScreenCustomPoints(View view){
        // change to new screen where you can change the points a bunco! adds
/*
        Snackbar.make(view, "Custom feature coming soon", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
*/

        Intent i = new Intent(this, customBunco.class);
        startActivity(i);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

           // resetGame(view);
            return true;
        } /*else if(id == R.id.action_settings2) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}



