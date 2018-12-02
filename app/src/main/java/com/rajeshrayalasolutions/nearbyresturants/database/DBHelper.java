package com.rajeshrayalasolutions.nearbyresturants.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rajeshrayalasolutions.nearbyresturants.Modal.PlacesDetails_Modal;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "resturants";
    public static final String REST_COLUMN_NAME = "name";
    public static final String REST_COLUMN_IMAGE = "image";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE table " + TABLE_NAME + "("+
                REST_COLUMN_NAME + " TEXT," +
                REST_COLUMN_IMAGE + " BLOB);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData (String name,byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("image", image);

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }


    public ArrayList<PlacesDetails_Modal> getAllCotacts() {
        ArrayList<PlacesDetails_Modal> array_list = new ArrayList<PlacesDetails_Modal>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String name = res.getString(0);
            byte[] image = res.getBlob(1);

            PlacesDetails_Modal placesDetails_modal= new PlacesDetails_Modal();
            placesDetails_modal.setName(name);
            placesDetails_modal.setLocationA(null);
            placesDetails_modal.setPhone_no("");
            placesDetails_modal.setPhotourl(null);
            placesDetails_modal.setImageFromBb(image);
            placesDetails_modal.setRating(4);
            array_list.add(placesDetails_modal);
            res.moveToNext();
        }
        return array_list;
    }
}
