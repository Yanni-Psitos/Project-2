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
    TextView mPowersTv,mAdditionalDetailsTv,mHeroNameTv,mRealNameTv;//Member variables for all views, drawables, cursor, and helper.
    ImageView mImageView;
    Cursor mCursor;
    SQLiteOpenHelper mHelper;
    Drawable mDeadpoolPic,mWolverinePic,mJokerPic,mBatmanPic,mHulkPic,mKickassPic,mThorPic,mAntmanPic,mGokuPic,mDeathstrokePic;
    FloatingActionButton mFavoriteButton,mRemoveButton;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //When my activity is created....
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
       mSharedPreferences = this.getSharedPreferences("prefs", MODE_PRIVATE); //Gets users team to persist when chosen or removed.
        mEditor = mSharedPreferences.edit();

        final int dataId = getIntent().getIntExtra("id",-1); //Gets the intent passed by the searchview and gets the extra passed, with default value -1, assigned to an int dataId.

        mHelper = new SQLiteOpenHelper(this); //mHelper becomes an instance of my helper class.

        mHeroNameTv = (TextView)findViewById(R.id.heroNameTextView); //Instantiating/referencing my views.
        mRealNameTv = (TextView)findViewById(R.id.realNameTextView);
        mPowersTv = (TextView)findViewById(R.id.powersTextView);
        mAdditionalDetailsTv = (TextView)findViewById(R.id.additionalDetailsTextView);
        mImageView = (ImageView)findViewById(R.id.imageView);


        mDeadpoolPic = getResources().getDrawable(R.drawable.deadpool); //Referencing my resource pictures
        mWolverinePic = getResources().getDrawable(R.drawable.wolverineactual);
        mJokerPic = getResources().getDrawable(R.drawable.joker1);
        mBatmanPic = getResources().getDrawable(R.drawable.batman1);
        mHulkPic = getResources().getDrawable(R.drawable.hulk1);
        mKickassPic = getResources().getDrawable(R.drawable.kickass1);
        mThorPic = getResources().getDrawable(R.drawable.thor1);
        mAntmanPic = getResources().getDrawable(R.drawable.antman1);
        mGokuPic = getResources().getDrawable(R.drawable.goku1);
        mDeathstrokePic = getResources().getDrawable(R.drawable.deathstroke1);


        if(dataId>=0){ //If the data passed (id) is greater than or equal to 0 (greater than -1, the default value) - then..
            mCursor = mHelper.searchHeroListById(dataId);
            mCursor.moveToFirst(); //Cursor is set to the id passed, and moves to first in the row.
            mHeroNameTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME))); //Sets the appropriate values to the views.
            mRealNameTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_REAL_NAME)));
            mPowersTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_POWERS)));
            mAdditionalDetailsTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_ADDITIONAL_DETAIL)));
            if(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deadpool")){ //Gets appropriate picture based on grabbed name and sets it to a view.
                mImageView.setImageDrawable(mDeadpoolPic);
            }else if(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Wolverine")){
                mImageView.setImageDrawable(mWolverinePic);
            }else if (mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Joker")) {
                mImageView.setImageDrawable(mJokerPic);
            } else if (mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Batman")) {
                mImageView.setImageDrawable(mBatmanPic);
            } else if (mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Hulk")) {
                mImageView.setImageDrawable(mHulkPic);
            } else if (mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Kick-Ass")) {
                mImageView.setImageDrawable(mKickassPic);
            } else if (mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Thor")) {
                mImageView.setImageDrawable(mThorPic);
            } else if (mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Ant-Man")) {
                mImageView.setImageDrawable(mAntmanPic);
            } else if (mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Goku")) {
                mImageView.setImageDrawable(mGokuPic);
            } else if (mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deathstroke")) {
                mImageView.setImageDrawable(mDeathstrokePic);
            }
        }


        mFavoriteButton = (FloatingActionButton)findViewById(R.id.addFab); //Instantiating and setting the floating action button.
        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //When clicked.. if the id is already stored, send a toast notifying user. If the ids 1-4 are no longer their default value, notify user that all spaces are full. Otherwise, fill appropriate space and add to the stored data.
                if (mSharedPreferences.getInt("id1", -1) == dataId || mSharedPreferences.getInt("id2", -1) == dataId || mSharedPreferences.getInt("id3", -1) == dataId || mSharedPreferences.getInt("id4", -1) == dataId) {
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " is already on your team!", Toast.LENGTH_SHORT).show();
                }
                else if (mSharedPreferences.getInt("id1",-1) != -1 && mSharedPreferences.getInt("id2",-1) != -1 && mSharedPreferences.getInt("id3",-1) != -1 && mSharedPreferences.getInt("id4",-1) != -1){
                    Toast.makeText(DetailActivity.this, "Your team is full!", Toast.LENGTH_SHORT).show();
                }
                else if (mSharedPreferences.getInt("id1", -1) != dataId && mSharedPreferences.getInt("id1",-1) == -1) {
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " joins your team!", Toast.LENGTH_SHORT).show();

                    mEditor.putInt("id1", dataId);
                    mEditor.commit();

                } else if (mSharedPreferences.getInt("id2", -1) != dataId && mSharedPreferences.getInt("id2",-1) == -1) {
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " joins your team!", Toast.LENGTH_SHORT).show();


                    mEditor.putInt("id2", dataId);
                    mEditor.commit();

                } else if (mSharedPreferences.getInt("id3", -1) != dataId && mSharedPreferences.getInt("id3",-1) == -1) {
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " joins your team!", Toast.LENGTH_SHORT).show();


                    mEditor.putInt("id3", dataId);
                    mEditor.commit();

                } else if (mSharedPreferences.getInt("id4", -1) != dataId && mSharedPreferences.getInt("id4",-1) == -1) {
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " joins your team!", Toast.LENGTH_SHORT).show();


                    mEditor.putInt("id4", dataId);
                    mEditor.commit();

                }
            }
        });

        mRemoveButton = (FloatingActionButton)findViewById(R.id.removeFab); //Instantiating and setting the floating action button.
        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //When clicked.. if the id matches a stored value, remove that value, otherwise, notify the user by toast that the value is not present.
                if (mSharedPreferences.getInt("id1", -1) == dataId ) {
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " has been removed from your team!", Toast.LENGTH_SHORT).show();
                    mEditor.remove("id1");
                    mEditor.commit();
                }else if(mSharedPreferences.getInt("id2", -1) == dataId){
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " has been removed from your team!", Toast.LENGTH_SHORT).show();
                    mEditor.remove("id2");
                    mEditor.commit();
                }else if(mSharedPreferences.getInt("id3", -1) == dataId){
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " has been removed from your team!", Toast.LENGTH_SHORT).show();
                    mEditor.remove("id3");
                    mEditor.commit();
                }else if(mSharedPreferences.getInt("id4", -1) == dataId){
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " has been removed from your team!", Toast.LENGTH_SHORT).show();
                    mEditor.remove("id4");
                    mEditor.commit();
                }else{
                    Toast.makeText(DetailActivity.this, mHeroNameTv.getText().toString() + " is not on your team yet!", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
