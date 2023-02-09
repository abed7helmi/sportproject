package com.example.coachappjava;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.gson.Gson;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;




import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.Response;



public class MainActivity extends Activity {

    private Button btnSub;
    private TextView resulttext;
    private Button btnUnsb;
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
        btnSub=findViewById(R.id.btn_Sub);
        resulttext=findViewById(R.id.resulttext);
        btnUnsb=findViewById(R.id.btn_send);
        clientID ="xxx";
        topic = "testtopic/coach";
        client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883",
                        clientID);




        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 connect();
                 Long selectedCoach = getIntent().getLongExtra("selected_coach_id", 0L);
                 SaveCoachDTO saveCoachDTO = new SaveCoachDTO(selectedCoach,"","");
                 sendSubscribePostRequest(saveCoachDTO);
                 btnUnsb.setVisibility(View.VISIBLE);
                 btnSub.setVisibility(View.GONE);



            }
        });

        btnUnsb.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Long selectedCoach = getIntent().getLongExtra("selected_coach_id", 0L);
                SaveCoachDTO saveCoachDTO = new SaveCoachDTO(selectedCoach,"","");
                sendUnsubscribePostRequest(saveCoachDTO);
                btnSub.setVisibility(View.VISIBLE);
                btnUnsb.setVisibility(View.GONE);
            }
        } );


    }


    public void sendSubscribePostRequest(SaveCoachDTO saveCoachDTO) {
        final String url = "http://10.0.2.2:9999/notification-channel-manager-service/subscribeCoach";

        Gson gson = new Gson();

        final String requestBody = gson.toJson(saveCoachDTO);


        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestBody);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle failure
                Log.v("test inscrit coach ","inscrit coach non envoyé : "+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    // Handle unsuccessful response
                    Log.v("test inscrit coach "," bravo envoyé");
                } else {
                    // Handle successful response
                    Log.v("test inscrit coach ","inscrit coach envé : pas de retour ");
                }
            }
        });
    }

    public void sendUnsubscribePostRequest(SaveCoachDTO saveCoachDTO) {
        final String url = "http://10.0.2.2:9999/notification-channel-manager-service/unsubscribeCoach";

        Gson gson = new Gson();
        /*SaveCoachDTO save2 = new SaveCoachDTO();
        save2.setIdCoach(2L);
        save2.setEmail("test@yahoo.fr");
        save2.setCoachFirstName("salah");*/


        final String requestBody = gson.toJson(saveCoachDTO);
        //final String requestBody = gson.toJson(save2);

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestBody);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle failure
                Log.v("test unsubscribe coach ","unsubscribe coach non envoyé : "+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    // Handle unsuccessful response
                    Log.v("test unsubscribe coach "," bravo envoyé");
                } else {
                    // Handle successful response
                    Log.v("test unsubscribe coach ","unsubscribe coach envé : pas de retour ");
                }
            }
        });
    }


    private void connect(){
        try {



            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d("test mqtt", "onSuccess : le coach est prét");
                    sub();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d("test mqtt", "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("test fermuture","fermuture de l'app");
        // votre code pour exécuter la fonction ici
        Long selectedCoach = getIntent().getLongExtra("selected_coach_id", 0L);
        SaveCoachDTO saveCoachDTO = new SaveCoachDTO(selectedCoach,"","");
        sendUnsubscribePostRequest(saveCoachDTO);
    }


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