package ypsitos.neighborhoodapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
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

public class MainActivity extends AppCompatActivity { //Member variables for all views, cursor, and helper.
    ListView mHeroList;
    CursorAdapter mCursorAdapter;
    SQLiteOpenHelper mSqlHelper;
    Cursor mCursor;
    FloatingActionButton mToFavoritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSqlHelper = new SQLiteOpenHelper(MainActivity.this); //Instantiated the helper and set the cursor to the getHeroList method. Also, created a SimpleCursorAdapter to allow the data from the cursor to be shown.
        mCursor = mSqlHelper.getHeroList();
        mCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, mCursor, new String[]{SQLiteOpenHelper.COL_HERO_NAME}, new int[]{android.R.id.text1}, 0);



        mHeroList = (ListView) findViewById(R.id.heroListView); //Referencing the list view.
        mHeroList.setAdapter(mCursorAdapter); //Sets the adapter to the listview in order to output cursor data into the view.

        mHeroList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //When clicked... change activities to the detailactivity and allow the id to be passed to show appropriate data.
                Intent toDetails = new Intent(MainActivity.this, DetailActivity.class);
                mCursor.moveToPosition(position);
                toDetails.putExtra("id", mCursor.getInt(mCursor.getColumnIndex(mSqlHelper.COL_ID)));
                startActivity(toDetails);
            }
        });


        mToFavoritesButton = (FloatingActionButton)findViewById(R.id.fab);
        mToFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //When clicked.. sends user to the team activity.
                Intent toFavoritesActivity = new Intent(MainActivity.this,TeamActivity.class);
                startActivity(toFavoritesActivity);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) { //When the menu is created, references all the search services and views to allow query.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        // Associates searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Hero, Name, or Power");
        return true;



    }

    @Override
    protected void onNewIntent(Intent intent) { //When a new intent is grabbed, it is handled.
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) { //Handles intent by getting the query passed by the search view, and changes the cursor to include the data allowed by the methods.
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mCursor = mSqlHelper.searchHeroList(query);
            mCursorAdapter.changeCursor(mCursor);
        }
    }

    @Override
    public void onBackPressed() { //When back is pressed, the cursor changes to include original data.
        super.onBackPressed();
        mCursor = mSqlHelper.getHeroList();
        mCursorAdapter.changeCursor(mCursor);
    }


}
