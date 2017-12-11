package com.example.fidelmomolo.constraint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fidel M Omolo on 12/4/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1="id";
    public static final String COL_2="name";
    public static final String COL_3="surname";
    public static final String COL_4="marks";



    public DataBaseHelper(Context context) {

        //whenever the constructor is called the database is created
        super(context, DATABASE_NAME, null, 1);
       // SQLiteDatabase db=this.getWritableDatabase(); //initiates process for creating new table and database
            //context.deleteDatabase(DATABASE_NAME);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //this is where the table is created

        db.execSQL("create table "+TABLE_NAME+" (id integer primary key autoincrement, name text, surname text, marks integer)");// the method executes sql queries

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);// deletes table if it exists
        onCreate(db);// calls the onCreate method above and creates new table

    }

    public boolean inserData(String name,String surname, String marks){
        SQLiteDatabase db=this.getWritableDatabase();

        //used to insert values in the table first parameter is for colunm name and second is for the value
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);

      long result=db.insert(TABLE_NAME,null,contentValues);

        return result != -1;

    }

    public Cursor getAll(){
      SQLiteDatabase db=this.getWritableDatabase();
        Cursor results=db.rawQuery("select * from "+TABLE_NAME,null);

        return results;

    }


    public Integer deleteData(){
        SQLiteDatabase db=this.getWritableDatabase();

    //   Integer number= db.delete(TABLE_NAME,"ID= ?",new String[]{id});
        Integer number= db.delete(TABLE_NAME,null,new String[]{});


       return number;
    }



    public int updateData(String id,String name,String surname, String marks){
        SQLiteDatabase db=this.getWritableDatabase();

        //used to insert values in the table first parameter is for colunm name and second is for the value
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);

        int results=db.update(TABLE_NAME,contentValues,"ID= ?", new String[]{id});


        return results;
    }




}
