package com.hstefan.aunctiondroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.hstefan.aunctiondroid.db.DbHelper;
import com.hstefan.aunctiondroid.db.entities.User;

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
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_item_row, menu);
	    return true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("Profile", "Resumed");
		setListAdapter();
	}

	private void setListAdapter() {
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
		
		Button buy_button = (Button)findViewById(R.id.buy_button_id);
		
		buy_button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(ProfileActivity.this, ViewAunctionsActivity.class);
				startActivity(intent);
			}
		});
		
		ListView list_view = (ListView)findViewById(R.id.profile_items_list_id);
		list_view.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(final AdapterView<?> adapter, View v, final int pos,
					long id) {
				final CharSequence[] items = {"Delete", "Sell"};

				AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
				builder.setTitle("Pick a color");
				builder.setItems(items, new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int item) {
				        if(item == 0) {
				        	onItemDeletion(adapter, pos);
				        } else if(item == 1) {
				        	onItemSell(adapter, pos);
				        }
				    }
				});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
	}
	
	private void onItemDeletion(AdapterView<?> adapter, int pos) {
		long id = adapter.getItemIdAtPosition(pos);
		System.out.println("IDDDDDDDDDDDDD " + Long.toString(id));
		myDb.delete(DbHelper.ITEM_TABLE, "id=?", new String[]{Long.toString(id)});
		myDb.delete(DbHelper.USER_ITEM_TABLE, "id_item=?", new String[]{Long.toString(id)});
		setListAdapter();
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch(id) {
		case 0:
			dialog = new Dialog(this);
			dialog.setContentView(R.layout.create_auction);
			dialog.setTitle("Create aunction");
			
			return dialog;
		}
		return dialog;
	}

	
	private void onItemSell(AdapterView<?> adapter, int pos) {
		showDialog(0);
	}
	
}
