package me.kevinkang.sightreadingskillbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
    }

    public void goToGame(View view) {
        startActivity(new Intent(this, GameActivity.class));
    }


    public void goToInstruction(View view) {
        startActivity(new Intent(this, InstructionActivity.class));
    }

    /* TODO: implement this
    public void goToAbout(View view) {
        startActivity(new Intent(this, AboutActivity.class));
    }
    */
}
