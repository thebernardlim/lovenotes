package appxcitinglabs.lovenotes.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import appxcitinglabs.lovenotes.R;
import appxcitinglabs.lovenotes.classes.Note;

/**
 * Created by Bernard on 2/1/2015.
 */
public class SingleItemAdapter extends ArrayAdapter<Note> {

    private final Activity context;
    private final ArrayList<Note> noteList;

    Integer[] imageID = {
            R.drawable.list_item_3_hor,
            R.drawable.list_item_6_hor,
            R.drawable.list_item_5_hor,
            R.drawable.list_item_4_hor,
            R.drawable.list_item_5_hor,
            R.drawable.list_item_4_hor,
            R.drawable.list_item_3_hor,
            R.drawable.list_item_2_hor,
            R.drawable.list_item_1_hor,
            R.drawable.list_item_2_hor,
            R.drawable.list_item_3_hor,
            R.drawable.list_item_4_hor

    };

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

        Note currNote = noteList.get(position);
        boolean activated = currNote.isActivated();

        //Set text
        String ttl = "Love Note #" + currNote.getWeekNo(); //+ ((!activated) ? "  [Locked]" : "");
        txtTitle.setText(ttl);

        Typeface tf = Typeface.createFromAsset(this.getContext().getAssets(), "fonts/JosefinSans-Regular.ttf");
        txtTitle.setTypeface(tf);

        //Set lock/unlock Image
        ImageView imageLock = (ImageView) rowView.findViewById(R.id.imgLock);
        if (!activated)
        {
            Picasso.with(context).load(R.drawable.lock).into(imageLock);
        }
        else
        {
            Picasso.with(context).load(R.drawable.unlock).into(imageLock);
        }

        //Set main image
        ImageView imageLoveNote = (ImageView) rowView.findViewById(R.id.imgLoveNote);
        Picasso.with(context).load(imageID[position]).into(imageLoveNote);

        //imageView.setImageResource(imageID[position]);
        return rowView;
    }
}
