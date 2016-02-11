package ypsitos.neighborhoodapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TeamActivity extends AppCompatActivity {
    ImageView mImageView1,mImageView2,mImageView3,mImageView4;
    TextView mTextView1, mTextView2, mTextView3, mTextView4;
    Cursor mCursor1,mCursor2,mCursor3,mCursor4;
    SQLiteOpenHelper mHelper;
    Drawable mDeadpoolPic,mWolverinePic;
    Button backButton;
    SharedPreferences mSharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
    SharedPreferences.Editor mEditor = mSharedPreferences.edit();

    int id1 = mSharedPreferences.getInt("id1", -1);
    int id2 = mSharedPreferences.getInt("id2",-1);
    int id3 = mSharedPreferences.getInt("id3",-1);
    int id4 = mSharedPreferences.getInt("id4",-1);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        mHelper = new SQLiteOpenHelper(this);

        mCursor1 = mHelper.searchHeroListById(id1);
        mCursor2 = mHelper.searchHeroListById(id2);
        mCursor3 = mHelper.searchHeroListById(id3);
        mCursor4 = mHelper.searchHeroListById(id4);

        mImageView1 = (ImageView)findViewById(R.id.imageView1);
        mImageView2 = (ImageView)findViewById(R.id.imageView2);
        mImageView3 = (ImageView)findViewById(R.id.imageView3);
        mImageView4 = (ImageView)findViewById(R.id.imageView4);

        mDeadpoolPic = getResources().getDrawable(R.drawable.deadpool);
        mWolverinePic = getResources().getDrawable(R.drawable.wolverineactual);

        mTextView1 = (TextView)findViewById(R.id.textView1);
        mTextView2 = (TextView)findViewById(R.id.textView2);
        mTextView3 = (TextView)findViewById(R.id.textView3);
        mTextView4 = (TextView)findViewById(R.id.textView4);

        backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHome = new Intent(TeamActivity.this, MainActivity.class);
                startActivity(toHome);
            }
        });

        if(mSharedPreferences.equals(null)) {
            Toast.makeText(TeamActivity.this, "You Need To Add Heroes!", Toast.LENGTH_LONG).show();
        }


        if(mSharedPreferences.getInt("id1",0) >= 0){
            mCursor1.moveToFirst();
            mTextView1.setText(mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)));
            if(mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deadpool")){
                mImageView1.setImageDrawable(mDeadpoolPic);
            }else if(mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Wolverine")){
                mImageView1.setImageDrawable(mWolverinePic);
            }
        }

        if(mSharedPreferences.getInt("id2",0) >= 0){
            mCursor2.moveToFirst();
            mTextView2.setText(mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)));
            if(mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deadpool")){
                mImageView2.setImageDrawable(mDeadpoolPic);
            }else if(mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Wolverine")){
                mImageView2.setImageDrawable(mWolverinePic);
            }
        }

        if(mSharedPreferences.getInt("id3",0) >= 0){
            mCursor3.moveToFirst();
            mTextView3.setText(mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)));
            if(mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deadpool")){
                mImageView3.setImageDrawable(mDeadpoolPic);
            }else if(mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Wolverine")){
                mImageView3.setImageDrawable(mWolverinePic);
            }
        }

        if(mSharedPreferences.getInt("id4",0) >= 0){
            mCursor4.moveToFirst();
            mTextView4.setText(mCursor4.getString(mCursor4.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)));
            if(mCursor4.getString(mCursor4.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deadpool")){
                mImageView4.setImageDrawable(mDeadpoolPic);
            }else if(mCursor4.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Wolverine")){
                mImageView4.setImageDrawable(mWolverinePic);
            }
        }
    }
}
