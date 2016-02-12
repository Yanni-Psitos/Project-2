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
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values (null,'Deadpool', 'Wade Wilson', 'Accelerated Healing Factor, Trained Assassin, Marksman, Martial Artist, Enhanced Reflexes and Endurance, Breaks the 4th Wall, Totally Better Than Deathstroke', 'Unlike Wolverine’s natural healing factor, Deadpool’s is mentally driven to a partial extent. Due to the presence of this superhuman healing ability, many of Deadpool’s natural physical attributes have been enhanced. Deadpool’s musculature generates considerably less fatigue toxins than the muscles of an ordinary human being, granting him superhuman levels of stamina in all physical activities. His natural strength, agility and reflexes have been enhanced to levels that are beyond the natural limits of the human body. Deadpool’s agility and reaction time are superior to those of even the finest human athlete.')";
        String wolverineRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_ID+ ", "+COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values (null,'Wolverine','Logan Howlett','Accelerated Healing Factor, Retractable Claws, Adamantium Skeleton','Due to his extensive training as a soldier, a C.I.A. operative, a samurai, a spy, and a member of the X-Men, Wolverine is an exceptional hand-to-hand combatant, having mastered virtually every fighting style on Earth. He is also a trained expert in multiple types of weapons, vehicles, computer systems, explosives, and assassination techniques.')";
        String jokerRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_ID+ ", "+COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values (null,'Joker','Jack Napier','Insanity, Gadgets, Tricks','His crimes require no motivation other than his sadistic desire to show people the meaninglessness of life through pain and death, and the narcissism to see the world remade in his own image. He is constantly adapting his personality and his psychosis to respond to the world around him, sometimes a harmless trickster and at others a brutal mass-murderer.')";
        String batmanRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_ID+ ", "+COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values (null,'Batman','Bruce Wayne','Trained Martial Artist, Inexhaustible Wealth, Brilliant Deductive Skill, Gadgets','As a polymath, his knowledge and expertise in almost every discipline known to man is nearly unparalleled. Batman has been repeatedly described as having a genius-level intellect, one of the greatest martial artists in the DC Universe, and having peak human physical conditioning.')";
        String hulkRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_ID+ ", "+COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values (null,'Hulk','Bruce Banner','Incredible Strength, High Physical Damage Resistance, Healing Factor, Intellectual Genius as Bruce','Bruce was exposed to extreme amounts of gamma radiation that altered his DNA structure due to gamma rays and trauma passed down by his Father and caused him to become a giant green (at first grey) monster of incredible power whenever he starts to get angry.')";
        String kickassRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_ID+ ", "+COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values (null,'Kick-Ass','David Lizewski','Immunity To Pain, Martial Artist, Partial Metal Implants','After his first unsuccessful attempt at superheroics ends in being beaten, mugged and hit by a moving car, he has several metal plates and braces surgically implanted within his body during the course of his hospitalization and recovery. This, along with the nerve damage he suffers during the event, provides him with the ability to be almost immune to pain, giving him an edge while fighting. His only weapons are his twin batons that are wrapped in green electrical tape.')";
        String thorRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_ID+ ", "+COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values (null,'Thor','Thor Odinson','Incredible Strength, Long Lifespan, High Physical Damage Resistance, Storm Calling, Dimensional Travel, Flight','As the Norse god of thunder, Thor can summon the elements of the storm (lightning; rain; wind; snow) and uses Mjolnir as a tool to focus this ability, although the hammer cannot command artificial weather, only natural. He can cause these weather effects over the world and destroy entire buildings; by whirling his hammer he can lift entire buildings with the wind. Thor can also create small tornadoes by quickly whipping his cape in circles.As the son of the Earth goddess Gaea, Thor has shown some control over the Earth.')";
        String antmanRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_ID+ ", "+COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values (null,'Ant-Man','Scott Lang','Change Size At Will, Telepathic Communication With Insects, Subatomic Travel, Electronics Expertise','Using a gaseous form of Pym particles kept in a compartment in his belt, Ant-Man had the power to shrink himself (and other people and objects along with himself) to the size of an ant and return to normal. Over time, he has acquired the ability to change size at will, seemingly without the need for the gas.')";
        String gokuRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_ID+ ", "+COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values (null,'Goku','Kakarot','Incredible Strength, Extreme Speed And Reflexes, Flight, Teleportation, Harnessing Of Ki Energy, Super Saiyan Transformations','Goku can generate energy blasts from an inner energy called ki, notably in the form of the Kamehameha Wave. He can teleport anywhere he wants instantly via Instant Transmission. He is capable of unaided flight, and was capable of aided flight through the use of a small flying cloud called the Flying Nimbus only those with pure hearts can ride the cloud. He is capable of transforming into a Great Ape when he is looking at the moon and possesses his tail. Every time Goku resurrects he becomes more powerful.')";
        String deathstrokeRow = "INSERT INTO " + TABLE_NAME + " ("
                + COL_ID+ ", "+COL_HERO_NAME + ", " + COL_REAL_NAME + ", "
                + COL_POWERS + ", " + COL_ADDITIONAL_DETAIL +") Values (null,'Deathstroke','Slade Wilson','Trained Assassin, Marksman, Martial Artist, Enhanced Strength and Reflexes, Limited Healing Factor, Tactical Genius','A man is only as good as his word. But in the underground world of assassins, mercenaries, and guns-for-hire, words mean little. What matters is action and follow through—completing the mission. That’s what makes you good. Never failing—even when it comes to taking down the World’s Greatest Super Heroes, some of the most powerful beings in the world—that’s what makes you the best. That’s what makes you Deathstroke.')";

        db.execSQL(antmanRow);
        db.execSQL(batmanRow);
        db.execSQL(deadpoolRow);
        db.execSQL(deathstrokeRow);
        db.execSQL(gokuRow);
        db.execSQL(hulkRow);
        db.execSQL(jokerRow);
        db.execSQL(kickassRow);
        db.execSQL(thorRow);
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
