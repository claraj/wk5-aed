package com.example.hello.addemphasisdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class AddEmphasis extends Activity {


	CharSequence[] mEmphasisChoices = { "Capitalize", "Add Exclamation Points", "Add Smiley Face"};
	boolean[] mInitialSelection = new boolean[mEmphasisChoices.length]; //boolean default is false
	boolean[] mUserChoices = new boolean[mEmphasisChoices.length];

	ArrayList mSelectedItems;


	boolean mEmphText = false;


	EditText myText;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_emphasis);

		Button addEmp = (Button) findViewById(R.id.addemph);
		myText = (EditText) findViewById(R.id.textentry);

		addEmp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String text = myText.getText().toString();

				if (text == null || text.trim().length() == 0) {
					Toast.makeText(AddEmphasis.this, "Type in some text", Toast.LENGTH_SHORT).show();
					return;
				}


				//launch dialog box to choose between 3 different forms of emphasis

				AddEmphasis.this.onCreateDialog(0, null).show();



			}
		});

	}


	protected String addEmphasisToText(){

		String emphasisedText = new String(myText.getText().toString());

		if (mUserChoices[0] == true) {
			//capitalize
			emphasisedText = emphasisedText.toUpperCase();
		}

		if (mUserChoices[1] == true) {
			//add !!!!!!
			emphasisedText = emphasisedText + "!!!!";

		}

		if (mUserChoices[2] == true) {
			emphasisedText = emphasisedText + " :) ";
			//emphasisedText = emphasisedText + "\u1f603";
		}

		Toast.makeText(this, emphasisedText, Toast.LENGTH_SHORT).show();

		return emphasisedText;


	}



	protected void showEmphasizedTextInDialog() {

	//	Toast.makeText(this, theText, Toast.LENGTH_SHORT).show();


		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		String emphasised = addEmphasisToText();

		builder.setTitle("Your emphasised text is...");

		builder.setMessage(emphasised);

		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			});

			builder.create().show();


		}






	@Override
	protected Dialog onCreateDialog(int i, Bundle savedInstanceState){


		mSelectedItems = new ArrayList();

		AlertDialog.Builder builder = new AlertDialog.Builder(this);


		builder.setTitle("What emphasis would you like?");

		builder.setMultiChoiceItems(mEmphasisChoices, mInitialSelection, new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				//make a note of the item checked
				mUserChoices[which] = isChecked;
			}
		});

		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//blank mUserChoices
				mUserChoices = new boolean[mEmphasisChoices.length]; //reset to false

			}
		});

		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//User choices saved, used to emphasise text.
				showEmphasizedTextInDialog();

			}
		});

		return builder.create();


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_add_emphasis, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
