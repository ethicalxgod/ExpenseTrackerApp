package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ExpenseName;
    ListView listView;
    Button add;
    EditText amount;
    ArrayList<String> itemlist = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpenseName = findViewById(R.id.editExpenseName);
        add = findViewById(R.id.buttonAddExpense);
        amount = findViewById(R.id.editTextExpenseAmount);
        listView = findViewById(R.id.listViewExpenses);

        itemlist = Filehelper.readdata(this);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemlist);
        listView.setAdapter(arrayAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String expencename= ExpenseName.getText().toString();
                String expenceamount = amount.getText().toString();
                if(!expenceamount.isEmpty() && !expencename.isEmpty()){
                String expence = expencename +" - ₹" + expenceamount;
                itemlist.add(expence);
                ExpenseName.setText("");
                amount.setText("");
                Filehelper.writedata(itemlist,getApplicationContext());
                arrayAdapter.notifyDataSetChanged();}
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Delete");
                alert.setMessage("Do want to delete this expense from list?");
                alert.setCancelable(false);
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        itemlist.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                        Filehelper.writedata(itemlist,getApplicationContext());
                    }
                });
                AlertDialog alert_dialog = alert.create();
                alert_dialog.show();
            }
        });
    }

}