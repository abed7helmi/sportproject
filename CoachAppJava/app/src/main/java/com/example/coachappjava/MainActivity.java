package com.example.coachappjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;


public class MainActivity extends Activity {

    private Button btn;
    private TextView resulttext;
    private Button btnsend;
    private static final String TAG="MyTag";
    private String topic,clientID;
    private MqttAndroidClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init(){
        btn=findViewById(R.id.btn_Sub);
        resulttext=findViewById(R.id.resulttext);
        btnsend=findViewById(R.id.btn_send);
        clientID ="xxx";
        topic = "testtopic/coach";
         client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883",
                        clientID);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 connect();
                 //connectavailable();
                 //send(2L);
            }
        });

       /* btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                send();
            }
        });*/
    }


    private void connect(){
        try {

            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d(TAG, "onSuccess : le coach est prét");
                    sub();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }



    /*private void send(){
        topic = "testtopic/coachdispo";
        String payload = "1";
        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }*/

    private void sub(){
        try {
            client.subscribe(topic,0);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    //log
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Log.d(TAG,"topic :" + topic);
                    String resultmessagec=new String(message.getPayload());
                    Log.d(TAG,"message :" +resultmessagec);

                    resulttext.setText(resultmessagec);



                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //toast
                }
            });

        }catch(MqttException e){

        }

    }
}