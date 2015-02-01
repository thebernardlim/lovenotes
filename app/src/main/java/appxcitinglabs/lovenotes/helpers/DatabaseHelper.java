package appxcitinglabs.lovenotes.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Bernard on 2/1/2015.
 * Source : http://www.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static String DB_PATH = "/data/data/appxcitinglabs.lovenotes/databases/";
    private static String DB_NAME = "LoveNotes.sqlite";
    private SQLiteDatabase myDatabase;
    private final Context myContext;

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDatabase() throws IOException{
        boolean dbExist = checkDatabase();
        if (dbExist){

        }else
        {
            this.getReadableDatabase();
            try
            {
                copyDatabase();
            }catch (IOException e){
                throw new Error("Error copying database " + e.getLocalizedMessage());
            }
        }
    }

    private boolean checkDatabase(){
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){
            System.err.println("Exception on initial check of db");
        }

        if (checkDB != null){
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    private void copyDatabase() throws IOException{
        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase() throws SQLiteException{

    //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    public Cursor GetAllNotes() {
        /*
        Cursor c = myDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        System.err.println("Count: " + c.getColumnCount());
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                System.out.println("Table Name=> " + c.getString(0));
                c.moveToNext();
            }
        }*/
        return myDatabase.rawQuery("SELECT * FROM notes", null);
    }

    @Override
    public synchronized void close() {

        if(myDatabase != null)
            myDatabase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
