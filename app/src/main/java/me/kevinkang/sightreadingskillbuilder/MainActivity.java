package me.kevinkang.sightreadingskillbuilder;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Note> mNotes;
    private List<Note> mAllNotes;
    private ImageView noteImage;
    private Note currentNote;
    private Random random;
    private TextView totalText;
    private TextView completedText;
    private Chronometer chrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteImage = (ImageView) findViewById(R.id.noteImage);
        totalText = (TextView) findViewById(R.id.totalText);
        completedText = (TextView) findViewById(R.id.completed);
        chrono = (Chronometer) findViewById(R.id.chrono);

        noteImage.setImageResource(R.drawable.placeholder);
        // noteImage.setImageResource(--id--);
        initializeAllNotes();

        if (mNotes == null) {
            mNotes = new ArrayList<Note>();
        }
        random = new Random();


    }

    public void initializeAllNotes() {
        mAllNotes = new ArrayList<Note>();
        Note a1 = new Note(Note.A, R.drawable.a1);
        Note a2 = new Note(Note.A, R.drawable.a2);
        Note a3 = new Note(Note.A, R.drawable.a3);
        Note b1 = new Note(Note.B, R.drawable.b1);
        Note b2 = new Note(Note.B, R.drawable.b2);
        Note b3 = new Note(Note.B, R.drawable.b3);
        Note c1 = new Note(Note.C, R.drawable.c1);
        Note c2 = new Note(Note.C, R.drawable.c2);
        Note c3 = new Note(Note.C, R.drawable.c3);
        Note d1 = new Note(Note.D, R.drawable.d1);
        Note d2 = new Note(Note.D, R.drawable.d2);
        Note d3 = new Note(Note.D, R.drawable.d3);
        Note e1 = new Note(Note.E, R.drawable.e1);
        Note e2 = new Note(Note.E, R.drawable.e2);
        Note e3 = new Note(Note.E, R.drawable.e3);
        Note f1 = new Note(Note.F, R.drawable.f1);
        Note f2 = new Note(Note.F, R.drawable.f2);
        Note f3 = new Note(Note.F, R.drawable.f3);
        Note g1 = new Note(Note.G, R.drawable.g1);
        Note g2 = new Note(Note.G, R.drawable.g2);
        Note g3 = new Note(Note.G, R.drawable.g3);

        mAllNotes.add(a1);
        mAllNotes.add(a2);
        mAllNotes.add(a3);
        mAllNotes.add(b1);
        mAllNotes.add(b2);
        mAllNotes.add(b3);
        mAllNotes.add(c1);
        mAllNotes.add(c2);
        mAllNotes.add(c3);
        mAllNotes.add(d1);
        mAllNotes.add(d2);
        mAllNotes.add(d3);
        mAllNotes.add(e1);
        mAllNotes.add(e2);
        mAllNotes.add(e3);
        mAllNotes.add(f1);
        mAllNotes.add(f2);
        mAllNotes.add(f3);
        mAllNotes.add(g1);
        mAllNotes.add(g2);
        mAllNotes.add(g3);
    }

    public void checkIfCorrect(View view) {
        Button button = (Button) view;
        if (button.getText().toString().equals(currentNote.getNote())) {
            if (updateScore() >= mAllNotes.size()) {
                chrono.stop();
                Toast.makeText(getApplicationContext(), "Yay! You completed this exercise in " +
                        chrono.getText().toString() + "! Try to" +
                        " be even faster next time!", Toast.LENGTH_LONG).show();
            } else {
                updateNote();
                Toast.makeText(getApplicationContext(), "Yay! You got it correct! Moving on!",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "That was incorrect. We will come back" +
                    " to this. Moving on!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void intiateGame(View view) {
        mNotes.clear();
        mNotes.addAll(mAllNotes);
        updateScore();
        updateNote();
        Toast.makeText(getApplicationContext(), "Started new game",
                Toast.LENGTH_LONG).show();
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();
    }

    public int updateScore() {
        totalText.setText("" + mAllNotes.size());
        int difference = mAllNotes.size() - mNotes.size();
        completedText.setText("" + difference);
        return difference;
    }

    public void updateNote() {
        int i = random.nextInt(mNotes.size());
        currentNote = mNotes.remove(i);
        noteImage.setImageResource(currentNote.getResId());
    }
}
