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
    ImageView mImageView1, mImageView2, mImageView3, mImageView4;
    TextView mTextView1, mTextView2, mTextView3, mTextView4;
    Cursor mCursor1, mCursor2, mCursor3, mCursor4;
    SQLiteOpenHelper mHelper;
    Drawable mDeadpoolPic, mWolverinePic, mJokerPic, mBatmanPic, mHulkPic, mKickassPic, mThorPic, mAntmanPic, mGokuPic, mDeathstrokePic;
    Button backButton;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        mHelper = new SQLiteOpenHelper(this);
        mSharedPreferences = this.getSharedPreferences("prefs", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        final int id1 = mSharedPreferences.getInt("id1", -1);
        final int id2 = mSharedPreferences.getInt("id2", -1);
        final int id3 = mSharedPreferences.getInt("id3", -1);
        final int id4 = mSharedPreferences.getInt("id4", -1);

        mCursor1 = mHelper.searchHeroListById(id1);
        mCursor2 = mHelper.searchHeroListById(id2);
        mCursor3 = mHelper.searchHeroListById(id3);
        mCursor4 = mHelper.searchHeroListById(id4);

        mImageView1 = (ImageView) findViewById(R.id.imageView1);
        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mImageView3 = (ImageView) findViewById(R.id.imageView3);
        mImageView4 = (ImageView) findViewById(R.id.imageView4);

        mDeadpoolPic = getResources().getDrawable(R.drawable.deadpool);
        mWolverinePic = getResources().getDrawable(R.drawable.wolverineactual);
        mJokerPic = getResources().getDrawable(R.drawable.joker1);
        mBatmanPic = getResources().getDrawable(R.drawable.batman1);
        mHulkPic = getResources().getDrawable(R.drawable.hulk1);
        mKickassPic = getResources().getDrawable(R.drawable.kickass1);
        mThorPic = getResources().getDrawable(R.drawable.thor1);
        mAntmanPic = getResources().getDrawable(R.drawable.antman1);
        mGokuPic = getResources().getDrawable(R.drawable.goku1);
        mDeathstrokePic = getResources().getDrawable(R.drawable.deathstroke1);

        mTextView1 = (TextView) findViewById(R.id.textView1);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mTextView3 = (TextView) findViewById(R.id.textView3);
        mTextView4 = (TextView) findViewById(R.id.textView4);

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHome = new Intent(TeamActivity.this, MainActivity.class);
                startActivity(toHome);
            }
        });

        mImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDetail = new Intent(TeamActivity.this, MainActivity.class);
                toDetail.putExtra("id", id1);
                startActivity(toDetail);
            }
        });

        mImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDetail = new Intent(TeamActivity.this, MainActivity.class);
                toDetail.putExtra("id", id2);
                startActivity(toDetail);
            }
        });

        mImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDetail = new Intent(TeamActivity.this, MainActivity.class);
                toDetail.putExtra("id", id3);
                startActivity(toDetail);
            }
        });

        mImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDetail = new Intent(TeamActivity.this, MainActivity.class);
                toDetail.putExtra("id", id4);
                startActivity(toDetail);
            }
        });

        mImageView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mEditor.remove("id1");
                return false;
            }
        });

        mImageView2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mEditor.remove("id2");
                return false;
            }
        });

        mImageView3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mEditor.remove("id3");
                return false;
            }
        });

        mImageView4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mEditor.remove("id4");
                return false;
            }
        });


        if (mSharedPreferences.equals(null)
                ) {
            Toast.makeText(TeamActivity.this, "You Need To Add Heroes!", Toast.LENGTH_LONG).show();
        }

        if (id1 >= 0) {
            mCursor1.moveToFirst();
            mTextView1.setText(mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)));
            if (mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deadpool")) {
                mImageView1.setImageDrawable(mDeadpoolPic);
            } else if (mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Wolverine")) {
                mImageView1.setImageDrawable(mWolverinePic);
            } else if (mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Joker")) {
                mImageView1.setImageDrawable(mJokerPic);
            } else if (mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Batman")) {
                mImageView1.setImageDrawable(mBatmanPic);
            } else if (mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Hulk")) {
                mImageView1.setImageDrawable(mHulkPic);
            } else if (mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Kick-Ass")) {
                mImageView1.setImageDrawable(mKickassPic);
            } else if (mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Thor")) {
                mImageView1.setImageDrawable(mThorPic);
            } else if (mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Ant-Man")) {
                mImageView1.setImageDrawable(mAntmanPic);
            } else if (mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Goku")) {
                mImageView1.setImageDrawable(mGokuPic);
            } else if (mCursor1.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deathstroke")) {
                mImageView1.setImageDrawable(mDeathstrokePic);
            }
        }

        if (id2 >= 0) {
            mCursor2.moveToFirst();
            mTextView2.setText(mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)));
            if (mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deadpool")) {
                mImageView2.setImageDrawable(mDeadpoolPic);
            } else if (mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Wolverine")) {
                mImageView2.setImageDrawable(mWolverinePic);
            } else if (mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Joker")) {
                mImageView2.setImageDrawable(mJokerPic);
            } else if (mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Batman")) {
                mImageView2.setImageDrawable(mBatmanPic);
            } else if (mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Hulk")) {
                mImageView2.setImageDrawable(mHulkPic);
            } else if (mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Kick-Ass")) {
                mImageView2.setImageDrawable(mKickassPic);
            } else if (mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Thor")) {
                mImageView2.setImageDrawable(mThorPic);
            } else if (mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Ant-Man")) {
                mImageView2.setImageDrawable(mAntmanPic);
            } else if (mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Goku")) {
                mImageView2.setImageDrawable(mGokuPic);
            } else if (mCursor2.getString(mCursor2.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deathstroke")) {
                mImageView2.setImageDrawable(mDeathstrokePic);
            }
        }

        if (id3 >= 0) {
            mCursor3.moveToFirst();
            mTextView3.setText(mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)));
            if (mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deadpool")) {
                mImageView3.setImageDrawable(mDeadpoolPic);
            } else if (mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Wolverine")) {
                mImageView3.setImageDrawable(mWolverinePic);
            } else if (mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Joker")) {
                mImageView3.setImageDrawable(mJokerPic);
            } else if (mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Batman")) {
                mImageView3.setImageDrawable(mBatmanPic);
            } else if (mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Hulk")) {
                mImageView3.setImageDrawable(mHulkPic);
            } else if (mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Kick-Ass")) {
                mImageView3.setImageDrawable(mKickassPic);
            } else if (mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Thor")) {
                mImageView3.setImageDrawable(mThorPic);
            } else if (mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Ant-Man")) {
                mImageView3.setImageDrawable(mAntmanPic);
            } else if (mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Goku")) {
                mImageView3.setImageDrawable(mGokuPic);
            } else if (mCursor3.getString(mCursor3.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deathstroke")) {
                mImageView3.setImageDrawable(mDeathstrokePic);
            }
        }

        if (id4 >= 0) {
            mCursor4.moveToFirst();
            mTextView4.setText(mCursor4.getString(mCursor4.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)));
            if (mCursor4.getString(mCursor4.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deadpool")) {
                mImageView4.setImageDrawable(mDeadpoolPic);
            } else if (mCursor4.getString(mCursor1.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Wolverine")) {
                mImageView4.setImageDrawable(mWolverinePic);
            } else if (mCursor4.getString(mCursor4.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Joker")) {
                mImageView4.setImageDrawable(mJokerPic);
            } else if (mCursor4.getString(mCursor4.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Batman")) {
                mImageView4.setImageDrawable(mBatmanPic);
            } else if (mCursor4.getString(mCursor4.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Hulk")) {
                mImageView4.setImageDrawable(mHulkPic);
            } else if (mCursor4.getString(mCursor4.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Kick-Ass")) {
                mImageView4.setImageDrawable(mKickassPic);
            } else if (mCursor4.getString(mCursor4.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Thor")) {
                mImageView4.setImageDrawable(mThorPic);
            } else if (mCursor4.getString(mCursor4.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Ant-Man")) {
                mImageView4.setImageDrawable(mAntmanPic);
            } else if (mCursor4.getString(mCursor4.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Goku")) {
                mImageView4.setImageDrawable(mGokuPic);
            } else if (mCursor4.getString(mCursor4.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)).contains("Deathstroke")) {
                mImageView4.setImageDrawable(mDeathstrokePic);
            }
        }
    }
}

