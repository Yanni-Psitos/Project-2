package ypsitos.neighborhoodapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView mPowersTv,mAdditionalDetailsTv,mHeroNameTv,mRealNameTv;
    Cursor mCursor;
    SQLiteOpenHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mHelper = new SQLiteOpenHelper(this);

        mHeroNameTv = (TextView)findViewById(R.id.heroNameTextView);
        mRealNameTv = (TextView)findViewById(R.id.realNameTextView);
        mPowersTv = (TextView)findViewById(R.id.powersTextView);
        mAdditionalDetailsTv = (TextView)findViewById(R.id.additionalDetailsTextView);


        int dataId = getIntent().getIntExtra("id",-1);

        if(dataId>=0){
            mCursor = mHelper.searchHeroListById(dataId);
            mCursor.moveToFirst();
            mHeroNameTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_HERO_NAME)));
            mRealNameTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_REAL_NAME)));
            mPowersTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_POWERS)));
            mAdditionalDetailsTv.setText(mCursor.getString(mCursor.getColumnIndex(SQLiteOpenHelper.COL_ADDITIONAL_DETAIL)));

        }

    }
}
