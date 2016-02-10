package ypsitos.neighborhoodapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by YPsitos on 2/4/16.
 */
public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1; //Database reference number
    public static final String DATABASE_NAME = "HERO.DB"; //Database name, required for creation
    public static final String TABLE_NAME = "HERO_LIST"; //Database's table name, required for creation
    public static final String COL_ID = "_id"; //Id's to give each row a unique identifier value
    public static final String COL_HERO_NAME = "HERO_NAME"; //Hero name column for virtual table
    public static final String COL_REAL_NAME = "REAL_NAME"; //Real name column for virtual table
    public static final String COL_POWERS = "POWERS"; //Hero's powers
    public static final String COL_ADDITIONAL_DETAIL = "ADDITIONAL_DETAILS"; //Summary/additional information about hero


    public static final String[] HERO_COLUMNS = {COL_ID, COL_HERO_NAME, COL_REAL_NAME, COL_POWERS,COL_ADDITIONAL_DETAIL}; //Columns stored in an array for easy access

    private static final String CREATE_HERO_LIST_TABLE = //String to place into a query.
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_HERO_NAME + " TEXT, " +
                    COL_REAL_NAME + " TEXT, " +
                    COL_POWERS + " TEXT, " +
                    COL_ADDITIONAL_DETAIL + " TEXT)";


    public SQLiteOpenHelper(Context context) { //Creates an instance of my helper class to allow other classes to use it.
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //On create, creates my virtual table, along with appropriate rows and columns.
        db.execSQL(CREATE_HERO_LIST_TABLE);
        String deadpoolRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_ID+", " + COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values (null,'Deadpool', 'Wade Wilson', 'Accelerated Healing Factor, Trained Assassin, Breaks the 4th Wall, Chimichangas', 'Unlike Wolverine’s natural healing factor, Deadpool’s is mentally driven to a partial extent. Due to the presence of this superhuman healing ability, many of Deadpool’s natural physical attributes have been enhanced. Deadpool’s musculature generates considerably less fatigue toxins than the muscles of an ordinary human being, granting him superhuman levels of stamina in all physical activities. His natural strength, agility and reflexes have been enhanced to levels that are beyond the natural limits of the human body. Deadpool’s agility and reaction time are superior to those of even the finest human athlete.')";
        String wolverineRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_ID+ ", "+COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values (null,'Wolverine','Logan Howlett','Accelerated Healing Factor, Retractable Claws, Adamantium Skeleton','Due to his extensive training as a soldier, a C.I.A. operative, a samurai, a spy, and a member of the X-Men, Wolverine is an exceptional hand-to-hand combatant, having mastered virtually every fighting style on Earth. He is also a trained expert in multiple types of weapons, vehicles, computer systems, explosives, and assassination techniques.')";
        db.execSQL(deadpoolRow);
        db.execSQL(wolverineRow);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //When upgrading the database, the previous table gets dropped and recreated.
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        this.onCreate(db);
    }


    public Cursor getHeroList(){ //Method to allow a returned cursor with the proper information (from my database) attached, based on the user's query.
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                HERO_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;

    }


    public Cursor searchHeroList(String query){ //Method to allow a returned cursor with the proper information (from my database) attached, based on the user's query.
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                HERO_COLUMNS, // b. column names
                COL_HERO_NAME + " LIKE ? OR " + COL_REAL_NAME + " LIKE ? OR " + COL_POWERS + " LIKE ? ", // c. selections
                new String[]{("%" + query + "%"),("%" + query + "%"),("%" + query + "%")}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }
    public Cursor searchHeroListById(int id){ //Method to allow a returned cursor with the proper information (from my database) attached, based on the user's query.
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                HERO_COLUMNS, // b. column names
                COL_ID + " LIKE ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }
}
