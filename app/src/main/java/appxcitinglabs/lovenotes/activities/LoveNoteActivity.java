package appxcitinglabs.lovenotes.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import appxcitinglabs.lovenotes.R;

public class LoveNoteActivity extends ActionBarActivity {

    String note = "";
    int noteID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View contentView = inflater.inflate(R.layout.activity_love_note, null, false);

        setContentView(R.layout.activity_love_note);

        Bundle extras = getIntent().getExtras();
        note = extras.getString("notes");
        noteID = extras.getInt("noteID");

        setTitle("Love Note #" + noteID);

        TextView txtLoveNote = (TextView) findViewById(R.id.txtLoveNote);
        txtLoveNote.setText(note);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");
        txtLoveNote.setTypeface(tf);

        System.out.println("Notes here : " + note );




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_love_note, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
