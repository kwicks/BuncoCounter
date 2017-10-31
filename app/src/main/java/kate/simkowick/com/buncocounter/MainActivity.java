package kate.simkowick.com.buncocounter;

// counter to help you keep track of the bunco score
//  need to implement undo, improve look, put customizing in menu, add color schemes to choose from

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.*;


public class MainActivity extends AppCompatActivity {

    public int usPoints = 0;
    public int themPoints = 0;
    public int buncoPointsValue = 21;
    public int subtractUndoPoints;
    public boolean subtractUndoPointsType;
    public int logPointsStacked;
    public boolean logPointsType;
    public String tellMeType;
    public int onePoint = 1; // add to stack for undo value
    public int fivePoint = 5; // add to stack for undo value
    public int totalNumber;
    public int addingNumber;

    Stack<Integer> undoPointsStack = new Stack<Integer>();
    Stack<Boolean> undoPointsType = new Stack<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        undoPointsStack.push(0);
        undoPointsType.push(true);

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

    }

    public void displayPoints(View view){
        final TextView usScore = (TextView) findViewById(R.id.usScore);
        usScore.setText(String.valueOf(usPoints));

        final TextView themScore = (TextView) findViewById(R.id.themScore);
        themScore.setText(String.valueOf(themPoints));

    }

    // keeps points under 10000
    public boolean checkNumbers(View view, int totalNumber, int addingNumber){
        if((totalNumber + addingNumber) < 10000){
            return true;
        } else {
            sorryMessage(view);
            return false;
        }
    }
    public void addUsBunco(View view){
        if(checkNumbers(view, usPoints, buncoPointsValue)){
            usPoints = usPoints + buncoPointsValue;
            logPointsAdded(buncoPointsValue, true);
            displayPoints(view);
        }
    }

    public void addFiveUsScore(View view){
        if(checkNumbers(view, usPoints, 5)) {
            usPoints = usPoints + 5;
            logPointsAdded(fivePoint, true);
            displayPoints(view);
        }
    }

    public void addOneUsScore(View view){
        if(checkNumbers(view, usPoints, 1)) {
            usPoints = usPoints + 1;
            logPointsAdded(onePoint, true);
            displayPoints(view);
        }
    }

    public void addThemBunco(View view){
        if(checkNumbers(view, themPoints, buncoPointsValue)) {
            themPoints = themPoints + buncoPointsValue;
            logPointsAdded(buncoPointsValue, false);
            displayPoints(view);
        }
    }

    public void addFiveThemScore(View view){
        if(checkNumbers(view, themPoints, 5)) {
            themPoints = themPoints + 5;
            logPointsAdded(fivePoint, false);
            displayPoints(view);
        }
    }

    public void addOneThemScore(View view){
        if(checkNumbers(view, themPoints, 1)) {
            themPoints = themPoints + 1;
            logPointsAdded(onePoint, false);
            displayPoints(view);
        }
    }

    public void resetGame(View view){
        usPoints = 0;
        themPoints = 0;
        while(!(undoPointsStack.empty())){
            undoPointsStack.pop();
        }
        while(!(undoPointsType.empty())){
            undoPointsType.pop();
        }

        displayPoints(view);
    }

    public Stack<Integer> logPointsAdded(Integer pointsPlaceHolder, Boolean pointsplaceHolder2){
        // track points added to us or them so can undo if wanted
        logPointsStacked = pointsPlaceHolder;
        undoPointsStack.push(pointsPlaceHolder);

        // for type of point, true is us false is them
        logPointsType = pointsplaceHolder2;
        undoPointsType.push(pointsplaceHolder2);

        return undoPointsStack;

    }

    public void undoLast(View view){
        // track points added to us or them so can undo if wanted
        // if undoLast clicked

        if(undoPointsStack.size() > 0) {
            // find points last logged
            subtractUndoPoints = undoPointsStack.pop();
            //find team that was logged
            subtractUndoPointsType = undoPointsType.pop();

            // if team is us ( true ), subtract points from usPoints
            if(subtractUndoPointsType) {
                usPoints = usPoints - subtractUndoPoints;
                tellMeType = "Us";
                // if team is them ( false ), subtract points from themPoints
            } else if(!subtractUndoPointsType) {
                themPoints = themPoints -subtractUndoPoints;
                tellMeType = "Them";
            }

            displayPoints(view);
        }

        Snackbar.make(view, subtractUndoPoints + " points were taken from  " + tellMeType, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    public void sorryMessage(View view){
        Snackbar.make(view, "Points must be under 10,000.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public void switchScreenCustomPoints(View view){
        // change to new screen where you can change the points a bunco! adds

        Intent i = new Intent(this, customBunco.class);
        startActivity(i);


    }

}



