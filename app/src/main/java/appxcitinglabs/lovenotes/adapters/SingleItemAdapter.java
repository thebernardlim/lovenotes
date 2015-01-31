package appxcitinglabs.lovenotes.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import appxcitinglabs.lovenotes.R;

/**
 * Created by Bernard on 2/1/2015.
 */
public class SingleItemAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] textList;
    private final Integer[] imageID;

    public SingleItemAdapter(Activity context, String[] textList, Integer[] imageID)
    {
        super(context, R.layout.noteslistfragment_item_list, textList);
        this.context = context;
        this.textList = textList;
        this.imageID = imageID;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.noteslistfragment_singleitem, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(textList[position]);

        Picasso.with(context).load(imageID[position]).into(imageView);

        //imageView.setImageResource(imageID[position]);
        return rowView;
    }
}
