package com.example.parkmap_database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataAdapter mDbHelper = new DataAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor data = mDbHelper.getTestData();

        // Todo: Extract SQL data from 'data' and turn it into points in google map

        tableLayout=(TableLayout)findViewById(R.id.tableLayout);

        data.moveToFirst();
        do {
            View tableRow = LayoutInflater.from(this).inflate(R.layout.table_item,null,false);
            TextView name  = (TextView) tableRow.findViewById(R.id.name);
            TextView latitude  = (TextView) tableRow.findViewById(R.id.Latitude);
            TextView longitude  = (TextView) tableRow.findViewById(R.id.Longitude);
            TextView price  = (TextView) tableRow.findViewById(R.id.Price);


            name.setText(data.getString(1));
            latitude.setText(data.getString(2));
            longitude.setText(data.getString(3));
            price.setText(data.getString(4));
            tableLayout.addView(tableRow);

        } while (data.moveToNext());
        data.close();

        mDbHelper.close();
    }
}
