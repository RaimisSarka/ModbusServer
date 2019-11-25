package com.example.modbusserver;

import androidx.appcompat.app.AppCompatActivity;
import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;

import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    public TextView mOutputTV;
    public TextView mIpTV;
    public EditText mRangeET;
    public EditText mOffsetET;
    public EditText mIpET;
    public Runnable readThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOutputTV = (TextView) findViewById(R.id.textView);
        mIpTV = (TextView) findViewById(R.id.textView2);
        mIpET = (EditText) findViewById(R.id.editText);
        mOffsetET = (EditText) findViewById(R.id.editText2);
        mRangeET = (EditText) findViewById(R.id.editText3);

        Button mReadButton = (Button) findViewById(R.id.button);
        //TODO make read every second thred

        mReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reading();
            }
        });


    }

    public void reading(){
        try {
            String[] params = {"80.50.0.41", "500", "10"};
            mOutputTV.setText("");
            mIpTV.setText(mIpET.getText().toString());
            params[0] = mIpTV.getText().toString();
            params[1] = mOffsetET.getText().toString();
            params[2] = mRangeET.getText().toString();
            mOutputTV.setText(new ReadFromModbus().execute(params).get());
        } catch (ExecutionException e){
            System.out.println(e.getMessage());
        } catch (InterruptedException ie){
            System.out.println(ie.getMessage());
        }
    }

}
