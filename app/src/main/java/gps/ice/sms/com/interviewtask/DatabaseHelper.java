package gps.ice.sms.com.interviewtask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mipstech i5 2 on 16-Feb-18.
 */

class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "APIResponse";
    public static final String TABLE_NAME1 = "Colors";
    // Table columns

    public static final String SUBJECT = "shapename";
    public static final String DESC = "colorname";

    public static final String SUBJECT1 = "shapename1";
    public static final String DESC1 = "colorname1";

    // Database Information
    static final String DB_NAME = "APIRES.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("  + SUBJECT + " TEXT NOT NULL, " + DESC + " TEXT);";
    private static final String CREATE_TABLE1 = "create table " + TABLE_NAME1 + "("  + SUBJECT1 + " TEXT NOT NULL, " + DESC1 + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE1);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);

    }
}
