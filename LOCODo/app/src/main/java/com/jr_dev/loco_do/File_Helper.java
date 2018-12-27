package com.jr_dev.loco_do;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;

public class File_Helper {
    public static final String FILENAME ="listinfo.dat";

    public static void writeData(ArrayList<String> items, Context context) throws FileNotFoundException {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readData(Context context){
        ArrayList<String> returnList = null;
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            returnList = (ArrayList<String>) ois.readObject();
        }
        catch (FileNotFoundException e)
        {
            returnList = new ArrayList<>();
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return returnList;
    }
}
