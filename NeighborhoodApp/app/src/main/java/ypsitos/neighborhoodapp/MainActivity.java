package ypsitos.neighborhoodapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    ListView mHeroList;
    CursorAdapter mCursorAdapter;
    SQLiteOpenHelper mSqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleIntent(getIntent());
        mSqlHelper = new SQLiteOpenHelper(MainActivity.this);
        final Cursor cursor = mSqlHelper.getHeroList();
        mCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{SQLiteOpenHelper.COL_HERO_NAME}, new int[]{android.R.id.text1}, 0);


        mHeroList = (ListView) findViewById(R.id.heroListView);
        mHeroList.setAdapter(mCursorAdapter);

        mHeroList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toDetails = new Intent(MainActivity.this, DetailActivity.class);
                cursor.moveToPosition(position);
                toDetails.putExtra("id", cursor.getInt(cursor.getColumnIndex(mSqlHelper.COL_ID)));
                startActivity(toDetails);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        // Associates searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor cursor = mSqlHelper.searchHeroListForHeroName(query);
            mCursorAdapter.changeCursor(cursor);
            mCursorAdapter.notifyDataSetChanged();
        }
    }





}
