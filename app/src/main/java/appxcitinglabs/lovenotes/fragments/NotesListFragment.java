package appxcitinglabs.lovenotes.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import appxcitinglabs.lovenotes.R;
import appxcitinglabs.lovenotes.activities.LoveNoteActivity;
import appxcitinglabs.lovenotes.adapters.SingleItemAdapter;
import appxcitinglabs.lovenotes.classes.Note;
import appxcitinglabs.lovenotes.fragments.dummy.DummyContent;
import appxcitinglabs.lovenotes.helpers.DatabaseHelper;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class NotesListFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Note> noteList;

    String[] txtList = {
            "Week 1",
            "Week 2",
            "Week 3",
            "Week 4",
            "Week 5",
            "Week 6",
            "Week 1",
            "Week 2",
            "Week 3",
            "Week 4",
            "Week 5",
            "Week 6"
    };

    Integer[] imageID = {
            R.drawable.list_item_1_hor,
            R.drawable.list_item_2_hor,
            R.drawable.list_item_3_hor,
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

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static NotesListFragment newInstance(String param1, String param2) {
        NotesListFragment fragment = new NotesListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotesListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        PopulateNoteList();


        // TODO: Change Adapter to display your content
        //mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
        //        android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);

    }

    private void PopulateNoteList()
    {
        DatabaseHelper myDBHelper  = new DatabaseHelper(this.getActivity());
        try
        {
            myDBHelper.createDatabase();
        }
        catch(IOException ioe){
            throw new Error("Unable to create database");
        }

        try{
            myDBHelper.openDatabase();
            noteList = new ArrayList<Note>();

            Cursor c = myDBHelper.GetAllNotes();
            if (c.moveToFirst()) {
                do {
                    try
                    {
                        Note note = new Note();

                        note.setWeekNo(c.getInt(c.getColumnIndex("week_no")));

                        //Note
                        note.setNote(c.getString(c.getColumnIndex("notes")));

                        //Date inteprets month wrongly



                        //Date Activate
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");//,Locale.getDefault());
                        String date = c.getString(c.getColumnIndex("activate_date"));
                        note.setDateActivate(dateFormat.parse(date));


                        //Boolean
                        boolean activated = c.getInt(c.getColumnIndex("activated")) != 0;
                        note.setActivated(activated);

                        noteList.add(note);

                        //System.out.println("NOTE : " + note + " DATE: " + dateActivate.toString() + " ACTIVATED: " + activated);
                    }
                    catch(Exception ex)
                    {
                        throw new Error("Error querying database. Message: " + ex.getLocalizedMessage());
                    }

                } while (c.moveToNext());
            }
        }
        catch(SQLiteException sqle){
            throw sqle;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.noteslistfragment_item_list, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        //((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        SingleItemAdapter adapter = new SingleItemAdapter(getActivity(), noteList); // txtList, imageID);
        mListView.setAdapter(adapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        //Output string here
        //TextView txtTest = (TextView) view.findViewById(R.id.txtTest);
        //txtTest.setText(mParam1);

        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.

            Note currentNote = noteList.get(position);

            Date dateActivate = currentNote.getDateActivate();
            DateTime jodaDtActivate = new DateTime(dateActivate);
            DateTime jodaDtCurrent = new DateTime();

            int days = Days.daysBetween(jodaDtCurrent, jodaDtActivate).getDays();
            System.err.println("Days Difference: " + days);

            boolean activated = currentNote.isActivated();
            if (activated)
            {
                Intent intent = new Intent(getView().getContext(), LoveNoteActivity.class);
                intent.putExtra("noteID", currentNote.getWeekNo());
                intent.putExtra("notes", currentNote.getNote());

                startActivity(intent);
            }
            else
            {
                Toast.makeText(getView().getContext(), String.format(getString(R.string.loveNoteUnactivated), days), Toast.LENGTH_SHORT).show();
            }

            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);

        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
