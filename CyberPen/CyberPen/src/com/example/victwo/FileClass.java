package com.example.victwo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Environment;
import android.util.Log;

public class FileClass {

	String textData, textTitle;
	String oldPath, newPath;
	int duration;
	
	public FileClass() {
		//do nothing
	}
	
	public FileClass(String title) {
		this.textTitle = title;
	}
	
	public FileClass(String oldPath, String newPath, String newTitle) {
		this.oldPath = oldPath;
		this.newPath = newPath;
		this.textTitle = newTitle;
	}
	
	public FileClass(String text, String title) {
		super();
		this.textData = text;
		this.textTitle = title;
		
		Log.d("OVERWRITE", "SAVE");
		
		SaveNote(textData, textTitle);
	}
	
	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}
	
	public boolean isExistingFileOrDirectory(String path) {
		File file = new File(path);
		
		if(file.exists())
			return true;
		else
			return false;
	}
	
	public void renameFilesInDir() {
		File folder = new File(oldPath);
		File[] listOfFiles = folder.listFiles();
		
		for(int count = 0; count < listOfFiles.length; ++count) {
			if(listOfFiles[count].isFile() && listOfFiles[count].getName().split("\\.")[0].equals("Pic.jpg")) {
				Log.d("LISTED", listOfFiles[count].getName().split("\\.")[0]);
				Log.d("RENAMED_FILE", oldPath + "/" + textTitle + "." +	listOfFiles[count].getName().split("\\.")[1]);
		
				File renamedFile = new File(oldPath + "/" + listOfFiles[count].getName());
				renamedFile.renameTo(new File(oldPath + "/" + textTitle + "." +
										listOfFiles[count].getName().split("\\.")[1] ));
				
			}
		}
		
		File renamedFile = new File(oldPath);
		renamedFile.renameTo(new File(newPath));
	}
	
	private void mkdirIfMissing(File file) {
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	
	public void deleteNote() {
		Log.d("TEXT_TITLE", textTitle);
		String notePath = Environment.getExternalStorageDirectory().toString() + "/CyberPen/" + textTitle.replace(".txt","");
		Log.d("DELETE_DIR", notePath);
		Log.d("Another", Environment.getExternalStorageDirectory().toString() + "/CyberPen/");
		if(!notePath.equals(Environment.getExternalStorageDirectory().toString() + "/CyberPen/")){
			File dirDel = new File(notePath);
			deleteRecursion(dirDel);
		}
	}
	
	@SuppressWarnings("unused")
	private boolean isUrl(String str) {
		URL url;
		
		try {
			url = new URL(str);
		} catch (MalformedURLException e) {
			return false;
		}
		
		return true;
	}
	
	private boolean deleteRecursion(File file) {
		if (file != null) {
			if (file.isDirectory()) {
				String[] children = file.list();
				for (int i = 0; i < children.length; i++) {
					boolean success = deleteRecursion(new File(file,
							children[i]));
					if (!success) {
						return false;
					}
				}
			}
			Log.d("DELETING", file.getName());
			return file.delete();
		}
		return false;
	}
	
	private void SaveNote(String text, String title) {
		try {
			title = title.trim();
			File root = new File(Environment.getExternalStorageDirectory(), "CyberPen/" + title);
			Log.v("Saving", "to " + root);
			File photo = new File(Environment.getExternalStorageDirectory(), "CyberPen/Pic.jpg");
			
			mkdirIfMissing(root);
			
			File forSave = new File(root, title + ".txt");
	        FileWriter writer = new FileWriter(forSave);
	        Log.v("SAVE", "saving " + title);
	        
	        if(isUrl(textData.substring(textData.lastIndexOf(" ") + 1))) {
	        	textData += (" " + "\n");
	        }
	        
	        writer.append(textData);
	        writer.flush();
	        writer.close();
	        
	        if(photo.exists() && photo.renameTo(new File(root + "/Pic.jpg"))){
	        		Log.d("Saving", "photo");
	        }
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}