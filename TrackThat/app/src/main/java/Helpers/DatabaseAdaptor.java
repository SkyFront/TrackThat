package Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kelvinchan on 15-03-21.
 */


public class DatabaseAdaptor{

    DatabaseHelper helper;
    public DatabaseAdaptor(Context context){
        helper = new DatabaseHelper(context);
    }

    public long insertData(String trackName, int count){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(DatabaseHelper.NAME, trackName);
        contentvalues.put(DatabaseHelper.QUANTITY, count);
        long id = db.insert(DatabaseHelper.TABLE_NAME,null,contentvalues);
        return id;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "trackthatcounter";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "counterTable";
        private static final String UID = "_id";
        private static final String NAME = "trackItem";
        private static final String QUANTITY = "numberCounted";
        private Context context;
        private String log = "Helpers.DatabaseAdaptor";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            System.out.println(log + "DbHelper constructor");
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                String create = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        NAME + " VARCHAR(255)," + QUANTITY + " INTEGER);";

                db.execSQL(create);
                System.out.println(log + "DbHelper onCreate");
            } catch (android.database.SQLException e) {
                e.printStackTrace();
                Log.d(log, "onCreate error: " + e.getMessage());
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                String drop = "DROP TABLE  " + TABLE_NAME + " IF EXISTS";

                db.execSQL(drop);

                onCreate(db);
                System.out.println(log + "DbHelper onUpgrade");
            } catch (android.database.SQLException e) {
                e.printStackTrace();
                Log.d(log, "onUpdate error: " + e.getMessage());
            }
        }
    }
}