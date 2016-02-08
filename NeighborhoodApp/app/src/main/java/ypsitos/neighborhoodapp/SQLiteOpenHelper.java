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
    private static final int DATABASE_VERSION = 1; //Database reference number
    private static final String DATABASE_NAME = "HERO.DB"; //Database name, required for creation
    private static final String TABLE_NAME = "LOCATION_LIST"; //Database's table name, required for creation
    public static final String COL_ID = "_id"; //Id's to give each row a unique identifier value
    public static final String COL_HERO_NAME = "HERO_NAME"; //Hero name column for virtual table
    private static final String COL_REAL_NAME = "REAL_NAME"; //Real name column for virtual table
    private static final String COL_POWERS = "POWERS"; //Hero's powers
    private static final String COL_ADDITIONAL_DETAIL = "ADDITIONAL_DETAILS"; //Summary/additional information about hero


    public static final String[] HERO_COLUMNS = {COL_ID, COL_HERO_NAME, COL_REAL_NAME, COL_POWERS};

    private static final String CREATE_HERO_LIST_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_HERO_NAME + " TEXT, " +
                    COL_REAL_NAME + " TEXT, " +
                    COL_POWERS + " TEXT, " +
                    COL_ADDITIONAL_DETAIL + " TEXT)";


    public SQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String deadpoolRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values ('Deadpool', 'Wade Wilson', 'Accelerated Healing Factor, Trained Assassin, Breaks the 4th Wall, Chimichangas', 'Unlike Wolverine’s natural healing factor, Deadpool’s is mentally driven to a partial extent. Due to the presence of this superhuman healing ability, many of Deadpool's natural physical attributes have been enhanced. Deadpool's musculature generates considerably less fatigue toxins than the muscles of an ordinary human being, granting him superhuman levels of stamina in all physical activities. His natural strength, agility and reflexes have been enhanced to levels that are beyond the natural limits of the human body. Deadpool's agility and reaction time are superior to those of even the finest human athlete.)";
        String wolverineRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values ('Wolverine','Logan Howlett','Accelerated Healing Factor, Retractable Claws, Adamantium Skeleton','Due to his extensive training as a soldier, a C.I.A. operative, a samurai, a spy, and a member of the X-Men, Wolverine is an exceptional hand-to-hand combatant, having mastered virtually every fighting style on Earth. He is also a trained expert in multiple types of weapons, vehicles, computer systems, explosives, and assassination techniques.')";
        db.execSQL(CREATE_HERO_LIST_TABLE);
        db.execSQL(deadpoolRow);
        db.execSQL(wolverineRow);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        this.onCreate(db);
    }


    public Cursor getHeroList(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                HERO_COLUMNS,
                null,
                null,
                null,
                null,
                null,
                null);

        return cursor;

    }


    public Cursor searchHeroListForHeroName(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                null, // b. column names
                COL_HERO_NAME + " LIKE ?", // c. selections
                new String[]{"%" + query + "%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }
}
