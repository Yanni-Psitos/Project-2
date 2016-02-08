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

        mHeroNameTv = (TextView)findViewById(R.id.heroNameTextView);
        mRealNameTv = (TextView)findViewById(R.id.realNameTextView);
        mPowersTv = (TextView)findViewById(R.id.powersTextView);
        mAdditionalDetailsTv = (TextView)findViewById(R.id.additionalDetailsTextView);

        mHelper = new SQLiteOpenHelper(this);


        int dataId = getIntent().getIntExtra("id",-1);

        if(dataId>=0){
            mCursor = mHelper.sea(dataId);
            mCursor.moveToFirst();
            mItemName.setText(mCursor.getString(mCursor.getColumnIndex(ShoppingSQLiteOpenHelper.COL_ITEM_NAME)));
            mDescription.setText(mCursor.getString(mCursor.getColumnIndex(ShoppingSQLiteOpenHelper.COL_ITEM_DESCRIPTION)));
            mPrice.setText(mCursor.getString(mCursor.getColumnIndex(ShoppingSQLiteOpenHelper.COL_ITEM_PRICE)));
            mType.setText(mCursor.getString(mCursor.getColumnIndex(ShoppingSQLiteOpenHelper.COL_ITEM_TYPE)));
            mExtra.setText(mCursor.getString(mCursor.getColumnIndex(ShoppingSQLiteOpenHelper.COL_ITEM_EXTRADETAIL)));

        }

    }
}
