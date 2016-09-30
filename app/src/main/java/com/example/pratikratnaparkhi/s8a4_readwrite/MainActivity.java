package com.example.pratikratnaparkhi.s8a4_readwrite;

import android.os.Environment;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = (EditText) findViewById(R.id.Edittext1);
        textView = (TextView) findViewById(R.id.TextVie);
        textView.setVisibility(View.GONE);
    }


public void WriteExternalStorage(View view)



{

    String state;
    state = Environment.getExternalStorageState();

    if(Environment.MEDIA_MOUNTED.equals(state))
    {

        File Root = Environment.getExternalStorageDirectory();

        File Dir = new File(Root.getAbsolutePath() + "/MyAppFile");
        if(!Dir.exists())
        {
          Dir.mkdir();
        }

        File file  = new File(Dir,"Test.txt");
        String Message = editText.getText().toString();
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(Message.getBytes());
            fileOutputStream.close();
            editText.setText("");
            Toast.makeText(getApplicationContext(),"Message Saved",Toast.LENGTH_LONG).show();


        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    else
    {
        Toast.makeText(getApplicationContext(),"External Storage Not found", Toast.LENGTH_SHORT ).show();
    }



}


    public void ReadExternalStorage(View view)


    {
        File Root = Environment.getExternalStorageDirectory();
        File Dir = new File(Root.getAbsolutePath() + "/MyAppFile");
        File file  = new File(Dir,"Test.txt");
        String message;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((message=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(message + "\n");



            }
                textView.setText(stringBuffer.toString());
            textView.setVisibility(View.VISIBLE);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
