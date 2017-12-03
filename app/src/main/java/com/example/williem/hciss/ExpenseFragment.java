package com.example.williem.hciss;


import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseFragment extends Fragment {


    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DatabaseHelper dbcenter;
    TextView sumtv;
    double sumstring = 0;
    public ExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_expense, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbcenter = new DatabaseHelper(getActivity());

        final ListView listview = (ListView) view.findViewById(R.id.listviews);
        sumtv= (TextView)view.findViewById(R.id.tvs);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
//                String selectedFromList =(String) (listview.getItemAtPosition(myItemInt));
                Intent i=new Intent(getActivity(),EditActivity.class);

                String s = listview.getItemAtPosition(myItemInt).toString();
                SQLiteCursor cursor = (SQLiteCursor) listview.getItemAtPosition(myItemInt);
                String selectedItem = cursor.getString(0);

                System.out.println(selectedItem);

                String strName = null;
                i.putExtra("id", selectedItem);
                startActivity(i);

            }


        });

// TodoDatabaseHandler is a SQLiteOpenHelper class connecting to SQLite
        DatabaseHelper handler = new DatabaseHelper(getActivity());
// Get access to the underlying writeable database
        SQLiteDatabase db = handler.getWritableDatabase();
// Query for items from the database and get a cursor back
        Cursor todoCursor = handler.getTodayListContents();

        // Find ListView to populate
        ListView lvItems = (ListView) view.findViewById(R.id.listviews);
// Setup cursor adapter using cursor from last step
        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(getActivity(), todoCursor);
// Attach cursor adapter to the ListView
        lvItems.setAdapter(todoAdapter);


        int jum=dbcenter.sumAllToday();
        String numberAsStrings = Integer.toString(jum);
        sumtv.setText("Pengeluaran Hari ini : "+numberAsStrings);



    }
}