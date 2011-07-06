package com.hstefan.aunctiondroid;

import com.hstefan.aunctiondroid.db.DbHelper;
import com.hstefan.aunctiondroid.db.entities.User;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ProfileActivity extends Activity {
	SQLiteDatabase myDb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		Log.i("Session started:", User.getActive().getEmail() + ":" + User.getActive().getPass());
		myDb = AunctionDroidActivity.getHelper().getWritableDatabase();
		
		setListners();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("Profile", "Resumed");
		StringBuilder str_builder = new StringBuilder("SELECT ");
		
		str_builder.append(DbHelper.ITEM_TABLE);
		str_builder.append(".id as _id,");
		str_builder.append(DbHelper.ITEM_TABLE + ".name");
		
		str_builder.append(" FROM ");
		str_builder.append(DbHelper.ITEM_TABLE + ",");
		str_builder.append(DbHelper.USER_TABLE + ",");
		str_builder.append(DbHelper.USER_ITEM_TABLE);
		
		str_builder.append(" WHERE ");
		
		str_builder.append("(" + DbHelper.ITEM_TABLE + ".id = ");
		str_builder.append(DbHelper.USER_ITEM_TABLE + ".id_item" + ")" + " AND");
		str_builder.append(" (" + DbHelper.USER_ITEM_TABLE + ".id_user = ");
		str_builder.append(DbHelper.USER_TABLE + ".id)");
		
		Cursor cursor = myDb.rawQuery(str_builder.toString(), null);
		System.out.println(cursor.toString());
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.item_row, cursor, 
				new String[] {"name"}, new int[] {R.id.item_name});
		ListView list = (ListView)findViewById(R.id.profile_items_list_id);
		if(list != null)
			list.setAdapter(adapter);
		else
			Log.d("Adapter", "failed to atach");
		//Now Playing: Metallica - Fade To Black - Ride The Lightning - 1984 - Rock - MP3 - 320 kbps - 6:57 min
		Log.d("Rows resulted in query", Long.toString(cursor.getCount()));
	}
	
	private void setListners() {
		Button reg_button = (Button)findViewById(R.id.register_button_id);
		reg_button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(ProfileActivity.this, RegisterItemActivity.class);
				startActivity(intent);
			}
		});
	}
	
}
