package com.example.victwo;

import java.io.File;

import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.elsys.vicnote.R;

public class MainActivity extends ActionBarActivity  {

	String path = Environment.getExternalStorageDirectory().toString() + "/CyberPen";
	String lastOfPath;
	File f = new File(path);
	
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        android.app.ActionBar bar = getActionBar();
	        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));    
	        bar.setTitle(Html.fromHtml("<font color='#000000'>CyberPen</font>"));
        }
        
        File photo = new File(Environment.getExternalStorageDirectory(),  "/CyberPen/Pic.jpg");
		if(photo.exists())
			photo.delete();
		
        File files[] = f.listFiles();
        if(f.listFiles() != null) {
        	//final String[] theNamesOfFiles = new String[files.length];
        
        	final String[] theNamesOfFiles = getNameOfFiles(files.length, files); 
	
	        ListView lv = (ListView) findViewById(R.id.notesList);
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
	        															theNamesOfFiles);
	        lv.setAdapter(adapter);
	        lv.setOnItemClickListener(new OnItemClickListener() {
	
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Log.d("LIST", "File: " + theNamesOfFiles[position]);
					
					Intent editNote = new Intent(MainActivity.this, NoteActivity.class);
					editNote.putExtra("noteName", theNamesOfFiles[position] + ".txt");
					startActivity(editNote);
					finish();
				}
			});
        }
    }

    private String[] getNameOfFiles(int notesNum, File files[]) {
    	final String[] theNamesOfFiles = new String[notesNum];
    	
    	for(int i = 0; i < theNamesOfFiles.length; i++) {
	        	lastOfPath = files[i].toString().split("/")[files[i].toString().split("/").length-1];
	        	theNamesOfFiles[i] = lastOfPath.replace(".txt","");
	        	Log.d("Files", "String: " + theNamesOfFiles[i]);
    	}
    	
		return theNamesOfFiles;	
    }
    
    private void openNewNoteActivity() {
    	Intent newNote = new Intent(MainActivity.this, NoteActivity.class);
		startActivity(newNote);
		finish();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_new) {
        	openNewNoteActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }    
}