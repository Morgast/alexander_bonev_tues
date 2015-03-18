package com.example.victwo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

public class ClassAlertDialog {

	int action;
	
	String text, title, noteTitle, noteText;
	String oldPath, newPath;

	String fileName;
	
	Context context;

	public ClassAlertDialog(Context context, String title, int action) {
		this.context = context;
		this.title = title;
		this.action = action;
	}
	
	public void setOldPath(String path) {
		this.oldPath = path;
	}
	
	public void setNewPath(String path) {
		this.newPath = path;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setNoteTitle(String title) {
		this.noteTitle = title;
	}
	
	public void setNoteText(String text) {
		this.noteText = text;
	}
	
	public void createDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		builder.setTitle(title);
		
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				doTheAction();
			}
		});

		AlertDialog alert = builder.create();

		alert.show();

	}

	private void doTheAction() {
		Log.d("doTheAction_FILENAME", fileName);
		FileClass delete = new FileClass(fileName);
			delete.deleteNote();
	
		switch (action) {
		case 1:
			Log.d("OVERRWITE", "1");
			Log.d("ACTOIN_1_noteText", noteText);
			Log.d("doTheAction_noteTitle", noteTitle);
			new FileClass(noteText, noteTitle);
			
			break;
		case 2: 
			Log.d("OVERRWITE_OLDPATH", oldPath);
			Log.d("OVERRWITE_NEWPATH", newPath);
			FileClass rename = new FileClass(oldPath, newPath, 
										fileName);
			rename.renameFilesInDir();
			
			break;
		}
		
		endActivity();
	}

	private void endActivity() {
		Intent newNote = new Intent();
		newNote.setClass(context, MainActivity.class);
		((Activity)context).startActivity(newNote);
		((Activity)context).finish();
	}
	
}
