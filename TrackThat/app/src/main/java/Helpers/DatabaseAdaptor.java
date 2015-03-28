package Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        contentvalues.put(DatabaseHelper.KEY_NAME, trackName);
        contentvalues.put(DatabaseHelper.KEY_QUANTITY, count);
        long id = db.insert(DatabaseHelper.TABLE_NAME,null,contentvalues);
        return id;
    }

    public String getAllData(){
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {DatabaseHelper.KEY_UID, DatabaseHelper.KEY_NAME, DatabaseHelper.KEY_QUANTITY};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer data = new StringBuffer();
        while(cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_UID);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_NAME);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_QUANTITY);

            int cid = cursor.getInt(index1);
            String title = cursor.getString(index2);
            int quantity = cursor.getInt(index3);

            data.append(cid+" "+title+" "+quantity+"\n");
        }

        return data.toString();
    }

    public String getQuantity(String name){
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {DatabaseHelper.KEY_NAME, DatabaseHelper.KEY_QUANTITY};
        String[] selectionArgs = {name};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,columns,
                                 DatabaseHelper.KEY_NAME+" =?",selectionArgs,null,null,null);
        StringBuffer data = new StringBuffer();
        while(cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_QUANTITY);
            int quantity = cursor.getInt(index1);

            data.append(quantity+"\n");
        }
        return data.toString();
    }

    public int updateQualtity(String oldName, int newQuantity){

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(DatabaseHelper.KEY_NAME, newQuantity);
        String[] whereArgs = {oldName};
        int count = db.update(DatabaseHelper.TABLE_NAME, content, DatabaseHelper.KEY_NAME+" =?", whereArgs);
        return count;
    }

    public int deleteRow(String rowName){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs = {rowName};
        int count = db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.KEY_NAME+" =?", whereArgs);
        return count;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "trackthatcounter";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "counterTable";
        private static final String KEY_UID = "_id";
        private static final String KEY_NAME = "trackItem";
        private static final String KEY_QUANTITY = "numberCounted";
        private Context context;
        private String log = "Helpers.DatabaseAdaptor";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            System.out.println(log + "DbHelper constructor");
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                String create = "CREATE TABLE " + TABLE_NAME + " (" + KEY_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        KEY_NAME + " VARCHAR(255)," + KEY_QUANTITY + " INTEGER);";

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