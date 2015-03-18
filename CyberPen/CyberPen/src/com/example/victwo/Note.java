package com.example.victwo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class Note {

	String path;
	String title, textData = "";
	
	public Note(Bundle b) {
		title = getExtraTitle(b);
		path = Environment.getExternalStorageDirectory().toString() + "/CyberPen/" + title.replace(".txt","");
		textData = readTextData();
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getTextData() {
		return textData;
	}
	
	private String readTextData() {

		String data = "";
        FileInputStream fis = null;
        Log.d("FilePATH", path + "/" + title);
        
        try {
        	Log.d("FilePATH", path + "/" + title);

        	fis = new FileInputStream (new File(path + "/" + title));
            byte[] dataArray = new byte[fis.available()];
            
            if(dataArray.length != 0) {
                while(fis.read(dataArray) != -1){
                	data = new String(dataArray);
                    Log.d("LOOP", data);
                }
                Log.d("COLLECTED", data);
            }
            
            fis.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
        	Log.d("EXCEPTION", "58" + data);
        	e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.d("EXCEPTION", "62" + data);
        	e.printStackTrace();                
        }
		
		return data;
	}
	
	private String getExtraTitle(Bundle b) {
		Bundle bnd = b;
		String title = bnd.getString("noteName");

		return title;
	}
	
}