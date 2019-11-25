package com.example.modbusserver;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import de.re.easymodbus.modbusclient.ModbusClient;

public class ReadFromModbus extends AsyncTask<String, Void, String> {
    private Exception e;

    @Override
    protected String doInBackground(String... address) {
        ModbusClient mClient = new ModbusClient();
        String res = "";
        try {
            mClient.Connect(address[0], 502);
            int[] input = mClient.ReadHoldingRegisters(Integer.valueOf(address[1]), Integer.valueOf(address[2]));
            for (int i = 0; i < input.length; i++) {
                res = res + String.valueOf(Integer.valueOf(address[1])+i) + ":" + String.valueOf(input[i]) + "\n";
                System.out.println(String.valueOf(input[i]));
            }
        return res;

        } catch (Exception e) {
            this.e = e;

            return null;
        } finally {

        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        System.out.println("Got - " + s);
    }
}
