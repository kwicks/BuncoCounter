package kate.simkowick.com.buncocounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.security.spec.ECField;

/**
 * Created by kate on 10/9/17.
 */

public class customBunco extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_bunco);
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void changeBuncoToCustom(View view){
        // send value from EditText customBuncoEntered
        Intent i = new Intent(this, MainActivity.class);

        // get text from customBuncoEntered
        final EditText customBuncoEntered = (EditText) findViewById(R.id.customBuncoEntered);
        String userMessage = customBuncoEntered.getText().toString();


        if(TextUtils.isEmpty(userMessage)){
            userMessage = "21";
        }
        i.putExtra("buncoMessage", userMessage);


        startActivity(i);

    }
}