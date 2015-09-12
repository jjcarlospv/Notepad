package pe.area51.notepad;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    public static final String EXTRA_NOTE = "note";

    private ListFragment.NotesListViewAdapter notesListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListFragment listFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.activity_main_fragment_list);
        listFragment.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onNoteSelected((Note) parent.getItemAtPosition(position));
            }
        });
        notesListViewAdapter = (ListFragment.NotesListViewAdapter) listFragment.getListView().getAdapter();
        notesListViewAdapter.addAll(getNotes());
    }

    public void onNoteSelected(final Note note) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ((ContentFragment) getFragmentManager().findFragmentById(R.id.activity_main_fragment_content)).showNote(note);
        } else {
            startActivity(new Intent(this, ContentActivity.class).putExtra(EXTRA_NOTE, note));
        }
    }

    private List<Note> getNotes() {
   return NotesContract.manyfromCursor(getContentResolver().query(NotesContract.URI,null,null,null,null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_add_message:

                final int count = notesListViewAdapter.getCount();
                final String title = "Test Title "+(count+1);
                final String content = "Test Content "+(count+1);
                final long creationUnixTime = System.currentTimeMillis();
                final long modificationUnixTime = System.currentTimeMillis();

                final ContentValues noteContentValues = new ContentValues();
                noteContentValues.put(NotesContract.MODIFICATION_UNIX_TIME, modificationUnixTime);
                noteContentValues.put(NotesContract.TITLE, title);
                noteContentValues.put(NotesContract.CONTENT, content);
                noteContentValues.put(NotesContract.MODIFICATION_UNIX_TIME, modificationUnixTime);

                final long localId = Integer.valueOf(getContentResolver().insert(NotesContract.URI, noteContentValues).getPathSegments().get(1));
                final Note note = new Note(null, localId, creationUnixTime, modificationUnixTime, title, content);

                notesListViewAdapter.add(note);
                return true;

            case R.id.menu_main_clear_list:
                getContentResolver().delete(NotesContract.URI, null, null);

                notesListViewAdapter.clear();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
