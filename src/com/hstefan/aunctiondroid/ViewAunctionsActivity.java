package com.hstefan.aunctiondroid;

import com.hstefan.aunctiondroid.db.DbHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ViewAunctionsActivity extends Activity {
	
	SQLiteDatabase myDb;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.bidlist);
		myDb = AunctionDroidActivity.getHelper().getWritableDatabase();
		setListners();
	}

	@Override
	protected void onResume() {
		super.onResume();
		setListAdapter();
	}

	private void setListAdapter() {
		StringBuilder str_builder = new StringBuilder("SELECT ");
		
		str_builder.append(DbHelper.ITEM_TABLE);
		str_builder.append(".id as _id,");
		str_builder.append(DbHelper.ITEM_TABLE + ".name,");
		str_builder.append(DbHelper.AUNCTIONS_TABLE + ".price");
		
		str_builder.append(" FROM ");
		str_builder.append(DbHelper.ITEM_TABLE + ",");
		str_builder.append(DbHelper.USER_TABLE + ",");
		str_builder.append(DbHelper.USER_ITEM_TABLE + ",");
		str_builder.append(DbHelper.AUNCTIONS_TABLE);
		
		str_builder.append(" WHERE ");
		
		str_builder.append("(" + DbHelper.ITEM_TABLE + ".id = ");
		str_builder.append(DbHelper.USER_ITEM_TABLE + ".id_item" + ") AND ");
		str_builder.append("(" + DbHelper.USER_ITEM_TABLE + ".id_user = ");
		str_builder.append(DbHelper.USER_TABLE + ".id) AND (");
		str_builder.append(DbHelper.AUNCTIONS_TABLE + ".id_user = ");
		str_builder.append(DbHelper.USER_TABLE + ".id) AND (" );
		str_builder.append(DbHelper.AUNCTIONS_TABLE + ".id_item = ");
		str_builder.append(DbHelper.ITEM_TABLE + ".id)" );
		
		//Now Playing: Rush - Marathon - Power Windows - 1985 - Classic Prog - MP3 - 320 kbps - 6:10 min
		
		Cursor cursor = myDb.rawQuery(str_builder.toString(), null);
		System.out.println(cursor.toString());
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.bidlist_row, cursor, 
				new String[] {"name", "price"}, new int[] {R.id.bid_item_name_id, R.id.bid_item_price_text_id});
		ListView list = (ListView)findViewById(R.id.bid_list_view);
		if(list != null)
			list.setAdapter(adapter);
		else
			Log.d("Adapter", "failed to atach");
		//Now Playing: Metallica - Fade To Black - Ride The Lightning - 1984 - Rock - MP3 - 320 kbps - 6:57 min
		Log.d("Rows resulted in query", Long.toString(cursor.getCount()));
	}
	
	private void setListners() {
		
	}
}
