package com.example.fidelmomolo.constraint;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper myDb;
    EditText edit_name,edit_surname,edit_marks,edit_id;
    Button add_data,button_view,button_update,button_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_name= findViewById(R.id.edit_name);
        edit_surname= findViewById(R.id.edit_surname);
        edit_marks= findViewById(R.id.edit_marks);
        edit_id= findViewById(R.id.edit_id);

        add_data= findViewById(R.id.add_data);
        button_view= findViewById(R.id.button_view);
        button_update= findViewById(R.id.update_data);
        button_delete= findViewById(R.id.delete_data);


       // the object is called when the onCreate method is executed creating the db and table
        myDb=new DataBaseHelper(this);

        addData();
        viewAll();
        deleteContent();
        upDate();
    }

    public void addData(){

        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isinserted=myDb.inserData(edit_name.getText().toString(),edit_surname.getText().toString(),edit_marks.getText().toString());

                if(isinserted==true){
                    Toast.makeText(MainActivity.this,"Data is Saved",Toast.LENGTH_LONG).show();

                }else {

                    Toast.makeText(MainActivity.this,"Data has not been saved",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public  void viewAll(){
        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=myDb.getAll();
                String[]fromfield=new String[] {"id"};
                int []towhichtxtfield=new int[]{android.R.id.text1};

                SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(MainActivity.this,android.R.layout.simple_list_item_1,cursor,fromfield,towhichtxtfield,0);

                ListView listView= findViewById(R.id.list55);
                listView.setAdapter(simpleCursorAdapter);




  /*
                if(results.getCount()==0){

                //   Toast.makeText(MainActivity.this,"NO data in the data base",Toast.LENGTH_LONG).show();

                    showBuilder("Error Message","Nothing found in the data Base");

                    return;
                }


                StringBuffer buffer=new StringBuffer();

                while (results.moveToNext()){
                    buffer.append("ID "+results.getString(0)+"\n");
                    buffer.append("Name "+results.getString(1)+"\n");
                    buffer.append("surname"+results.getString(2)+"\n");
                    buffer.append("Marks"+results.getString(3)+"\n\n");
                }


                showBuilder("content In data base",buffer.toString());
             */

            }
        });

    }


    public void showBuilder(String title, String Message){

         AlertDialog.Builder builder=new AlertDialog.Builder(this);
         builder.setCancelable(true);
         builder.setTitle(title);
         builder.setMessage(Message);

         builder.show();


    }


    public void deleteContent(){
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          //  String id=edit_id.getText().toString();

               myDb=new DataBaseHelper(MainActivity.this);

           /*  Integer result= myDb.deleteData();

             if(result==0){
                 Toast.makeText(MainActivity.this,"The data has not been deleted",Toast.LENGTH_LONG).show();
             }
             else { Toast.makeText(MainActivity.this,"you have deleted "+result+" rows",Toast.LENGTH_LONG).show();}*/

            }
        });

    }


    public void upDate(){
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number=myDb.updateData(edit_id.getText().toString(),edit_name.getText().toString(),edit_surname.getText().toString(),edit_marks.getText().toString());

                if (number==0){

                    Toast.makeText(MainActivity.this,"The data has not been updated",Toast.LENGTH_LONG).show();
                }

                else { Toast.makeText(MainActivity.this,"The data has been updated",Toast.LENGTH_LONG).show();}
            }
        });

    }




}
