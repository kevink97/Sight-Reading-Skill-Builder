package me.kevinkang.sightreadingskillbuilder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private List<Note> mNotes;
    private List<Note> mAllNotes;
    private ImageView noteImage;
    private Note currentNote;
    private Random random;
    private TextView totalText;
    private TextView completedText;
    private Chronometer chrono;
    private List<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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
        updateUIHS();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void updateUIHS() {
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
        TextView textView = (TextView) findViewById(R.id.score_value_text);
        int defaultValue = Integer.parseInt(getResources()
                .getString(R.string.high_score_default));
        int currentHS = sp.getInt(getString(R.string.high_score_key), defaultValue);
        int minutes = currentHS / 60;
        int seconds = currentHS % 60;
        textView.setText("" + minutes + ":" + seconds);
    }

    private void initializeAllNotes() {
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

        Button buttA = (Button) findViewById(R.id.noteA);
        Button buttB = (Button) findViewById(R.id.noteB);
        Button buttC = (Button) findViewById(R.id.noteC);
        Button buttD = (Button) findViewById(R.id.noteD);
        Button buttE = (Button) findViewById(R.id.noteE);
        Button buttF = (Button) findViewById(R.id.noteF);
        Button buttG = (Button) findViewById(R.id.noteG);
        buttA.setEnabled(false);
        buttB.setEnabled(false);
        buttC.setEnabled(false);
        buttD.setEnabled(false);
        buttE.setEnabled(false);
        buttF.setEnabled(false);
        buttG.setEnabled(false);

        buttons = new ArrayList<Button>();
        buttons.add(buttA);
        buttons.add(buttB);
        buttons.add(buttC);
        buttons.add(buttD);
        buttons.add(buttE);
        buttons.add(buttF);
        buttons.add(buttG);
    }

    public void checkIfCorrect(View view) {
        Button button = (Button) view;
        TextView textView = (TextView) findViewById(R.id.compliment);
        if (button.getText().toString().equals(currentNote.getNote())) {
            textView.setTextColor(Color.parseColor("#FF00E676"));
            textView.setText("Good Job!");
            if (updateScore() >= mAllNotes.size()) {
                chrono.stop();
                String seconds = convertChronoToSeconds(chrono.getText().toString());
                updateHighScore(seconds);
            } else {
                updateNote();
            }
        } else {
            textView.setTextColor(Color.parseColor("#FFFF3D00"));
            textView.setText("Incorrect");
            mNotes.add(currentNote);
            updateNote();
        }
    }

    private String convertChronoToSeconds(String chrono) {
        String[] time = chrono.split(":");
        int seconds = 0;
        if (time.length == 2) {
            seconds += Integer.parseInt(time[1]);
            seconds += Integer.parseInt(time[0]) * 60;
        } else if (time.length == 1) {
            seconds += Integer.parseInt(time[0]);
        }
        return "" + seconds;
    }

    private void updateHighScore(String seconds) {
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        int newHS = Integer.parseInt(seconds);
        int defaultValue = Integer.parseInt(getResources()
                .getString(R.string.high_score_default));

        if (sp.contains(getString(R.string.high_score_key))) {
            int currentHS = sp.getInt(getString(R.string.high_score_key), defaultValue);
            if (currentHS <= newHS) {
                builder.setTitle("Better luck next time!");
                builder.setMessage("You completed it in " + newHS + " seconds. " +
                        "You did not beat your previous record (" +
                        + currentHS + " seconds). " +
                        "Keep trying to improve your time!");
            } else {
                builder.setTitle("Congratulation!");
                builder.setMessage("You completed it in " + newHS + " seconds. " +
                        "You beat your previous score (" +
                        + currentHS + " seconds). " +
                        "We will update your new high score for you! " +
                        "Keep trying to improve your time!");
                editor.putInt(getString(R.string.high_score_key), newHS);
                editor.commit();
                updateUIHS();
            }
        } else {
            builder.setTitle("Congratulation!");
            builder.setMessage("You completed it in " + newHS + " seconds. " +
                    "We will update your new high score for you! " +
                    "Keep trying to improve your time!");
            editor.putInt(getString(R.string.high_score_key), newHS);
            editor.commit();
            updateUIHS();
        }
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void intiateGame(View view) {
        mNotes.clear();
        mNotes.addAll(mAllNotes);
        updateScore();
        updateNote();
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setEnabled(true);
        }
        TextView textView = (TextView) findViewById(R.id.compliment);
        textView.setTextColor(Color.parseColor("#FF1DE9B6"));
        textView.setText("New Game!");
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();
    }

    private int updateScore() {
        totalText.setText("" + mAllNotes.size());
        int difference = mAllNotes.size() - mNotes.size();
        completedText.setText("" + difference);
        return difference;
    }

    private void updateNote() {
        int i = random.nextInt(mNotes.size());
        currentNote = mNotes.remove(i);
        noteImage.setImageResource(currentNote.getResId());
    }
}
