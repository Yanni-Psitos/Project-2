package ypsitos.neighborhoodapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView mPowersTv,mAdditionalDetailsTv,mHeroNameTv,mRealNameTv;//Member variables for all views, cursor, and helper.
    ImageView mImageView;
    Cursor mCursor;
    SQLiteOpenHelper mHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) { //When my activity is created....
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mHelper = new SQLiteOpenHelper(this); //mHelper becomes an instance of my helper class.

        mHeroNameTv = (TextView)findViewById(R.id.heroNameTextView); //Instantiating/referencing my views.
        mRealNameTv = (TextView)findViewById(R.id.realNameTextView);
        mPowersTv = (TextView)findViewById(R.id.powersTextView);
        mAdditionalDetailsTv = (TextView)findViewById(R.id.additionalDetailsTextView);
        mImageView = (ImageView)findViewById(R.id.imageView);


        int dataId = getIntent().getIntExtra("id",-1); //Gets the intent passed by the searchview and gets the extra passed, with default value -1, assigned to an int dataId.

        if(dataId>=0){ //If the data passed (id) is greater than or equal to 0...
            mCursor = mHelper.searchHeroListById(dataId);
            mCursor.moveToFirst();
            mHeroNameTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)));
            mRealNameTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_REAL_NAME)));
            mPowersTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_POWERS)));
            mAdditionalDetailsTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_ADDITIONAL_DETAIL)));

        }

    }
}
