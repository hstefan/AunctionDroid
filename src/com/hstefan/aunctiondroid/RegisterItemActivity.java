package com.hstefan.aunctiondroid;

import com.hstefan.aunctiondroid.db.DbHelper;
import com.hstefan.aunctiondroid.db.entities.User;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterItemActivity extends Activity{
	
	SQLiteDatabase database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_item);
		database = AunctionDroidActivity.getHelper().getWritableDatabase();
		
		setListners();
	}

	private void setListners() {
		Button reg = (Button)findViewById(R.id.confirm_register_button);
		reg.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				ContentValues item_val = new ContentValues();
				item_val.put("name", ((EditText)findViewById(R.id.item_edit_text)).getText().toString());
				long id_item = database.insert(DbHelper.ITEM_TABLE, null, item_val);
				Log.i("Created item", (((EditText)findViewById(R.id.item_edit_text)).getText().toString()));
				
				ContentValues user_item_val = new ContentValues();
				user_item_val.put("id_user", User.getActive().getId());
				user_item_val.put("id_item", id_item);
				database.insert(DbHelper.USER_ITEM_TABLE, null, user_item_val);
			}
		});
		
	}
}
