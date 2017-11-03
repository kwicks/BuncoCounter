package kate.simkowick.com.buncocounter;

// counter to help you keep track of the bunco score
//  todo improve look, add color schemes to choose from

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    public int usPoints = 0;
    public int themPoints = 0;
    public int buncoPointsValue = 21;
    public undoItem subtractUndoPoints;
    public undoItem logPointsStacked;

    Stack<undoItem> undoPointsStack = new Stack<undoItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // For custom bunco score value. Allows class to except extra information from customBunco.java
        Bundle buncoData = getIntent().getExtras();
        if(buncoData== null) {
            return;
        }

        String buncoMessage = buncoData.getString("buncoMessage");
        final TextView customText = (TextView) findViewById(R.id.customText);
        customText.setText(buncoMessage);

        if(buncoMessage != null){
            int newInt = Integer.parseInt(buncoMessage);
            buncoPointsValue = newInt;
        }


    }

    // Displays points
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

    // Us Team: Adds Bunco points to Us Score
    public void addUsBunco(View view){
        //add new undoItem
        undoItem undoAddUsBunco = new undoItem();
        undoAddUsBunco.team = "Us";
        undoAddUsBunco.value = buncoPointsValue;

        if(checkNumbers(view, usPoints, buncoPointsValue)){
            usPoints = usPoints + buncoPointsValue;
            logPointsAdded(undoAddUsBunco);
            displayPoints(view);
        }
    }

    // Us Team: Adds 5 points to Us Score
    public void addFiveUsScore(View view){
        //add new undoItem
        undoItem undoAddFiveUsScore = new undoItem();
        undoAddFiveUsScore.team = "Us";
        undoAddFiveUsScore.value = 5;

        if(checkNumbers(view, usPoints, 5)) {
            usPoints = usPoints + 5;
            logPointsAdded(undoAddFiveUsScore);
            displayPoints(view);
        }
    }

    // Us Team: Adds one points to Us Score
    public void addOneUsScore(View view){
        //add new undoItem
        undoItem undoAddOneUsScore = new undoItem();
        undoAddOneUsScore.team = "Us";
        undoAddOneUsScore.value = 1;

        if(checkNumbers(view, usPoints, 1)) {
            usPoints = usPoints + 1;
            logPointsAdded(undoAddOneUsScore);
            displayPoints(view);
        }
    }

    // Them Team: Adds Bunco points to Them Score
    public void addThemBunco(View view){
        //add new undoItem
        undoItem undoAddThemBunco = new undoItem();
        undoAddThemBunco.team = "Them";
        undoAddThemBunco.value = buncoPointsValue;

        if(checkNumbers(view, themPoints, buncoPointsValue)) {
            themPoints = themPoints + buncoPointsValue;
            logPointsAdded(undoAddThemBunco);
            displayPoints(view);
        }
    }


    // Them Team: Adds 5 points to Them Score
    public void addFiveThemScore(View view){
        //add new undoItem
        undoItem undoAddFiveThemScore = new undoItem();
        undoAddFiveThemScore.team = "Them";
        undoAddFiveThemScore.value = 5;

        if(checkNumbers(view, themPoints, 5)) {
            themPoints = themPoints + 5;
            logPointsAdded(undoAddFiveThemScore);
            displayPoints(view);
        }
    }


    // Them Team: Adds one point to Them Score
    public void addOneThemScore(View view){
        //add new undoItem
        undoItem undoAddOneThemScore = new undoItem();
        undoAddOneThemScore.team = "Them";
        undoAddOneThemScore.value = 1;

        if(checkNumbers(view, themPoints, 1)) {
            themPoints = themPoints + 1;
            logPointsAdded(undoAddOneThemScore);
            displayPoints(view);
        }
    }

    // Resets for new game.  Points and undoStack
    public void resetGame(View view){
        usPoints = 0;
        themPoints = 0;
        while(!(undoPointsStack.empty())){
            undoPointsStack.pop();
        }
        displayPoints(view);
    }

    // Push untoItem onto stack
    public Stack<undoItem> logPointsAdded(undoItem pointsPlaceHolder){
        // track points added to us or them so can undo if wanted
        logPointsStacked = pointsPlaceHolder;
        undoPointsStack.push(pointsPlaceHolder);

        return undoPointsStack;

    }

    // Undo last move
    public void undoLast(View view){
        if(!(undoPointsStack.isEmpty())) {
            // find points last logged
            subtractUndoPoints = undoPointsStack.pop();

            //find team that was logged. if team is us, subtract points from usPoints
            if(subtractUndoPoints.team == "Us") {
                usPoints = usPoints - subtractUndoPoints.value;
                //tellMeType = "Us";
                // if team is them ( false ), subtract points from themPoints
            } else if(subtractUndoPoints.team == "Them") {
                themPoints = themPoints -subtractUndoPoints.value;
                //tellMeType = "Them";
            }

            displayPoints(view);

            // display change
            // Snackbar.make(view, subtractUndoPoints.value + " points were taken from " + subtractUndoPoints.team, Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();
        }// else {
         //   Snackbar.make(view, "Cannot undo", Snackbar.LENGTH_SHORT)
         //           .setAction("Action", null).show();
        //}



    }

    public void sorryMessage(View view){
        Snackbar.make(view, "Points must be under 10,000.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    // change to new screen where you can change the points a bunco! adds
    public void switchScreenCustomPoints(View view){

        Intent i = new Intent(this, customBunco.class);
        startActivity(i);


    }

}



