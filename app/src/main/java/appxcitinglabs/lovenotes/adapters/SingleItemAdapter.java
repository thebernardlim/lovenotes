package appxcitinglabs.lovenotes.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import appxcitinglabs.lovenotes.R;
import appxcitinglabs.lovenotes.classes.Note;

/**
 * Created by Bernard on 2/1/2015.
 */
public class SingleItemAdapter extends ArrayAdapter<Note> {

    private final Activity context;
    private final ArrayList<Note> noteList;

    public SingleItemAdapter(Activity context, ArrayList<Note> noteList)
    {
        super(context, R.layout.noteslistfragment_item_list, noteList);
        this.context = context;
        this.noteList = noteList;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.noteslistfragment_singleitem, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        Note currNote = noteList.get(position);
        boolean activated = currNote.isActivated();
        String ttl = "Week " + currNote.getWeekNo() + ((!activated) ? "[Locked]" : "");
        txtTitle.setText(ttl);


        //Picasso.with(context).load(imageID[position]).into(imageView);

        //imageView.setImageResource(imageID[position]);
        return rowView;
    }
}
