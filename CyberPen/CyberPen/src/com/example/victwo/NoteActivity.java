package com.example.victwo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.util.Linkify;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.Toast;

import com.elsys.vicnote.R;

public class NoteActivity extends ActionBarActivity implements OnClickListener {
	
	boolean play = false;
	boolean record = true;
	boolean editedNoteExists = false;
	
	EditText textData, textTitle;

	String text, title = "";
	String onOpenedNoteTitle = "";
	String path = Environment.getExternalStorageDirectory().toString() + "/CyberPen";
	
	
	InputMethodManager imm;
	
	private static final int SELECT_PHOTO = 100;
	private static int TAKE_PICTURE = 1;    
	File photo;
	ImageView imageView;
	
	Button overBitmapButton;
	Button galleryButton;
	Button deleteButton;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		overBitmapButton = (Button)findViewById(R.id.buttonOverBitmap);
		galleryButton = (Button)findViewById(R.id.gallery_button);
		deleteButton = (Button)findViewById(R.id.delete_button);
		textTitle = (EditText) findViewById(R.id.editTitle);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			android.app.ActionBar bar = getActionBar();
			bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
		}
		
		galleryButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("GALLERY", "BUTTON PRESSED");
				Intent image = new Intent(NoteActivity.this, ImageViewActivity.class);
				startActivity(image);
			}
		});
		
		if(isExistingNote()) {
			Bundle b = getIntent().getExtras();
			Note note = new Note(b);
			editedNoteExists = true;
			
			title = note.getTitle();
			text = note.getTextData();
			onOpenedNoteTitle = title.replace(".txt", "");
			textTitle.setText(title.replace(".txt", ""));

            if(!text.equals("")) {
            	initTextData();	            
	            
	            
	            textData.setText(text);
            }
            
            Log.d("ON_OPENED", onOpenedNoteTitle);
            
			Log.d("EDIT", "File: " + title);
			
			
			String folder = title.replace(".txt","");
			final File file = new File(Environment.getExternalStorageDirectory(), "CyberPen/Pic.jpg");
			
			if(isExistingPhoto(folder)){
				photo = new File(Environment.getExternalStorageDirectory(), "CyberPen/" + folder + "/Pic.jpg" );
				setBitmapImage();
				
				overBitmapButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.setAction(android.content.Intent.ACTION_VIEW); intent.setDataAndType(Uri.parse(photo.getAbsolutePath()),"image/*");

						startActivity(intent);
					}
				});
				
				deleteButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Log.d("DELETE", "BUTTON PRESSED");
						photo.delete();
						imageView.setImageDrawable(null);
					}
				});
				
			}else if(file.exists()){
	        	setBitmapImage();
				
				overBitmapButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.setAction(android.content.Intent.ACTION_VIEW); intent.setDataAndType(Uri.parse(file.getAbsolutePath()),"image/*");

						startActivity(intent);
					}
				});
	        }
		}
		
		

		//---------------------------------------------------------------------

		textTitle.setHint("Enter title..");
		
		textData = (EditText) findViewById(R.id.editNote);
		textData.setHint("Enter your note..");
		textData.setMovementMethod(EnhancedMovementMethod.getInstance());
		textData.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				textData.setFocusableInTouchMode(true);
				imm.showSoftInput(textData, InputMethodManager.SHOW_FORCED);
				return false;
			}
		});
		
		
	}

	
	public void onResume(Bundle savedInstanceState){
        super.onResume();
        Log.d("ON_START", "CALLED");
        final File photo = new File(Environment.getExternalStorageDirectory(), "CyberPen/Pic.jpg");
        if(photo.exists()){
        	setBitmapImage();
			
			overBitmapButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setAction(android.content.Intent.ACTION_VIEW); intent.setDataAndType(Uri.parse(photo.getAbsolutePath()),"image/*");

					startActivity(intent);
				}
			});
        }
        
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.d("ON_PAUSE", "CALLED");
		textTitle.requestFocus();
		super.onPause();
	}

	private void initTextData() {
		// TODO Auto-generated method stub
		textData = (EditText) findViewById(R.id.editNote);
        textData.setLinksClickable(true);
        textData.setAutoLinkMask(Linkify.WEB_URLS);
        //textData.requestFocusFromTouch();
        textData.setScroller(new Scroller(this));
        textData.setMaxLines(10);
        textData.setVerticalScrollBarEnabled(true);
        textData.setMovementMethod(EnhancedMovementMethod.getInstance());
	}

	@SuppressLint("SimpleDateFormat")
	private String getDateAndTime() {
		String dateTime = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());
		Log.d("GET DATE", dateTime);
		return dateTime;
	}
	
	private boolean isExistingNote() {
		if(getIntent() != null && getIntent().getExtras() != null)
			return true;
		else
			return false;
	}
	
	private boolean isExistingPhoto(String folder){
		File photo = new File(Environment.getExternalStorageDirectory(), "CyberPen/" + folder + "/Pic.jpg"); //+ textTitle.getText().toString().replace(".txt", "") + ".jpg");
		if(photo.exists())
			return true;
		return false;
	}
	
	private void toastShow(String message) {
		Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}
	
	private void endActivity() {
			//imm.hideSoftInputFromInputMethod(textData.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
			Intent newNote = new Intent(NoteActivity.this, MainActivity.class);
			startActivity(newNote);
			finish();
		}
	
	private void saveNote() {
		setTitle();
		text = textData.getText().toString();
		
		if(!editedNoteExists) {
			
			if (dirExists(path + "/" + title.replace(".txt", ""))) {
				overwrite();
				return;
			}
		}
		
		new FileClass(text, title);
		toastShow("Saved.");	
		endActivity();
	}
	
	private void overwrite() {
		ClassAlertDialog cad = new ClassAlertDialog(this, "\""
				+ title.replace(".txt", "")
				+ "\" already exists. Would you like to overwrite it?", 1);

		cad.setFileName(title.replace(".txt", ""));
		cad.setNoteTitle(title);
		cad.setNoteText(text);
		cad.createDialog();
	}
	
	private boolean dirExists(String checkPath) {
		FileClass fc = new FileClass();
		
		if(fc.isExistingFileOrDirectory(checkPath)) {
			return true;
		} else {
			return false;
		}
	}
	
	private void setTitle() {
		title = textTitle.getText().toString();
		
		if(title.equals("")) {
			title = getDateAndTime();
		}

	}
	
	private boolean renameNote() {
		if(dirExists(path + "/" + textTitle.getText().toString())) {
			Log.d("RENAME_DIR", "EXISTS");
			setTitle();
			ClassAlertDialog cad = new ClassAlertDialog(this, "\"" 
					+ title.replace(".txt", "")
					+ "\" already exists. Would you like to overwrite it?", 2);
			
			cad.setFileName(title.replace(".txt", ""));
			cad.setOldPath(path + "/" + onOpenedNoteTitle);
			cad.setNewPath(path + "/" + textTitle.getText());
			cad.createDialog();
			//saveNote();
			return false;
		} else {
			Log.d("RENAME_DIR", "DOES NOT EXIST");
		}
		
		FileClass rename = new FileClass(path + "/" + title.replace(".txt", ""), 
				path + "/" + textTitle.getText(), textTitle.getText().toString());
		
		rename.renameFilesInDir();
		return true;
	}
	
	@Override	
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.note, menu);
		return true;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			File photo = new File(Environment.getExternalStorageDirectory(),  "/CyberPen/Pic.jpg");
			if(photo.exists())
				photo.delete();
			endActivity();
			Log.d("BACK", "Back button pressed");
		}
		return super.onKeyUp(keyCode, event);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		switch(id) {				
			case R.id.action_camera:
				takePhoto();
				return true;
				
			case R.id.action_audio:
				
//				if(popup.popupAudio) {
//					setTitle();
//					String audioPath = path + "/" + title.replace(".txt", "") + "/" + title.replace(".txt", "") + ".3gpp";
//					
//					Log.d("AUDIO_PATH", audioPath);
//					audio.setPath(audioPath);
//					
//					popup.init(getApplicationContext());
//					popup.popupInit();
//					popup.showPopUp(this.findViewById(R.id.action_audio));
//					
//					popup.popupAudio = false;
//				}else {
//					popup.popupDismiss();
//					popup.popupAudio = true;
//				}
//				return true;
				
			case R.id.action_delete:
				ClassAlertDialog dialog = new ClassAlertDialog(this,
						"Are you sure you want to delete this note?", 0);
				
				dialog.setFileName(title);
				dialog.createDialog();

				return true;

			case R.id.action_save:
				textTitle = (EditText) findViewById(R.id.editTitle);
				String tmpTitle = title.replace(".txt", "");
				
				if((!tmpTitle.equals("") && !tmpTitle.equals(textTitle.getText().toString()))) {
					if(renameNote());
					else
						return true;
					
					tmpTitle = null;
					endActivity();
				}else {
					saveNote();
				}
				
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	public int takePhoto() {
	    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
	    photo = new File(Environment.getExternalStorageDirectory(),  "/CyberPen/Pic.jpg");
	    intent.putExtra(MediaStore.EXTRA_OUTPUT,
	            Uri.fromFile(photo));
	    startActivityForResult(intent, TAKE_PICTURE);
	    return 1;
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    if(TAKE_PICTURE == 1)
	        if (resultCode == Activity.RESULT_OK) {
	        	setBitmapImage();
	        }
	}
	
	public void setBitmapImage(){
		Uri selectedImage = Uri.fromFile(photo);
        getContentResolver().notifyChange(selectedImage, null);
        imageView = (ImageView) findViewById(R.id.ivReturnedPic);
        ContentResolver cr = getContentResolver();
        Bitmap bitmap;
        try {
             bitmap = android.provider.MediaStore.Images.Media
             .getBitmap(cr, selectedImage);

            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                    .show();
            Log.e("Camera", e.toString());
        }
	}
	
	/*protected void chooseImage(int requestCode, int resultCode, Intent imageReturnedIntent) { 
	    super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

	    switch(requestCode) { 
	    case SELECT_PHOTO:
	        if(resultCode == RESULT_OK){  
	            Uri selectedImage = imageReturnedIntent.getData();
	            InputStream imageStream = null;
				try {
					imageStream = getContentResolver().openInputStream(selectedImage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
	        }
	    }
	}*/
}