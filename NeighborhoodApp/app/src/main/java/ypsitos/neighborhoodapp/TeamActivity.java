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
    Cursor mCursor;
    SQLiteOpenHelper mHelper;
    Drawable mDeadpoolPic,mWolverinePic;
    Button backButton;
    SharedPreferences mSharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
    SharedPreferences.Editor mEditor = mSharedPreferences.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

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
            Toast.makeText(TeamActivity.this, "You Need To Add Heros!", Toast.LENGTH_LONG).show();
        }


        mCursor = mHelper.searchHeroListById(dataId);
        mCursor.moveToFirst();
        mHeroNameTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)));
        mRealNameTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_REAL_NAME)));
        mPowersTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_POWERS)));
        mAdditionalDetailsTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_ADDITIONAL_DETAIL)));


        
        if(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deadpool")){
            mImageView1.setImageDrawable(mDeadpoolPic);
        }else if(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Wolverine")){
            mImageView1.setImageDrawable(mWolverinePic);
        }
    }
}
