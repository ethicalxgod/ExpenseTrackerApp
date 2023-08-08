package com.example.myapplication;

import static android.provider.Telephony.Mms.Part.FILENAME;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Filehelper {
    public static final String filename = "Expenseinfo.dat";
    public static void writedata(ArrayList<String> item, Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            oas.writeObject(item);
            oas.close();

        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<String> readdata(Context context){
        ArrayList<String> itemlist = null;
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemlist = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            itemlist = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    return itemlist;
    }
}
