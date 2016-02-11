package ypsitos.neighborhoodapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    TextView mPowersTv,mAdditionalDetailsTv,mHeroNameTv,mRealNameTv;//Member variables for all views, cursor, and helper.
    ImageView mImageView;
    Cursor mCursor;
    SQLiteOpenHelper mHelper;
    Drawable mDeadpoolPic,mWolverinePic;
    FloatingActionButton mFavoriteButton;
    SharedPreferences mSharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
    SharedPreferences.Editor mEditor = mSharedPreferences.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) { //When my activity is created....
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final int dataId = getIntent().getIntExtra("id",-1); //Gets the intent passed by the searchview and gets the extra passed, with default value -1, assigned to an int dataId.

        mHelper = new SQLiteOpenHelper(this); //mHelper becomes an instance of my helper class.

        mHeroNameTv = (TextView)findViewById(R.id.heroNameTextView); //Instantiating/referencing my views.
        mRealNameTv = (TextView)findViewById(R.id.realNameTextView);
        mPowersTv = (TextView)findViewById(R.id.powersTextView);
        mAdditionalDetailsTv = (TextView)findViewById(R.id.additionalDetailsTextView);
        mImageView = (ImageView)findViewById(R.id.imageView);
        mDeadpoolPic = getResources().getDrawable(R.drawable.deadpool);
        mWolverinePic = getResources().getDrawable(R.drawable.wolverineactual);


        if(dataId>=0){ //If the data passed (id) is greater than or equal to 0...
            mCursor = mHelper.searchHeroListById(dataId);
            mCursor.moveToFirst();
            mHeroNameTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)));
            mRealNameTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_REAL_NAME)));
            mPowersTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_POWERS)));
            mAdditionalDetailsTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_ADDITIONAL_DETAIL)));
            if(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deadpool")){
                mImageView.setImageDrawable(mDeadpoolPic);
            }else if(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Wolverine")){
                mImageView.setImageDrawable(mWolverinePic);
            }
        }

        mFavoriteButton = (FloatingActionButton)findViewById(R.id.fab);
        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSharedPreferences.getInt("id1", 0) == dataId || mSharedPreferences.getInt("id2", 0) == dataId || mSharedPreferences.getInt("id3", 0) == dataId || mSharedPreferences.getInt("id4", 0) == dataId) {
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " is already in your team!", Toast.LENGTH_SHORT).show();
                } else if (mSharedPreferences.getInt("id1", 0) != dataId) {
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " is placed on your team!", Toast.LENGTH_SHORT).show();

                    mEditor.putInt("id1", dataId);
                    mEditor.commit();

                } else if (mSharedPreferences.getInt("id2", 0) != dataId) {
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " is placed on your team!", Toast.LENGTH_SHORT).show();


                    mEditor.putInt("id2", dataId);
                    mEditor.commit();

                } else if (mSharedPreferences.getInt("id3", 0) != dataId) {
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " is placed on your team!", Toast.LENGTH_SHORT).show();


                    mEditor.putInt("id3", dataId);
                    mEditor.commit();

                } else if (mSharedPreferences.getInt("id4", 0) != dataId) {
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " is placed on your team!", Toast.LENGTH_SHORT).show();


                    mEditor.putInt("id4", dataId);
                    mEditor.commit();

                }
            }
        });

    }
}
