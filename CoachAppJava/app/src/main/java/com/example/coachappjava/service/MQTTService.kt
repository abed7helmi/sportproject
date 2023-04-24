package com.example.coachappjava.service


import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


class MqttService(context: Context) {

    private val _mqttStatusLiveData: MutableLiveData<String> = MutableLiveData()

    companion object {
        const val TAG = "AndroidMqttClient"
        private var mqttClient: MqttClient? = null
        private val memoryPersistence = MemoryPersistence()
        private var host = "tcp://broker.hivemq.com:1883"
        private var clientId = "kotlin-client"


    }

    fun connect() {
        mqttClient = MqttClient(host, clientId, memoryPersistence)
        val options = MqttConnectOptions()
        options.isCleanSession = true
        try {
            mqttClient?.connect(options)
            Log.d("MQTTSERVICE", "Connection is success")
            //subscribe()
        } catch (e: MqttException) {
            Log.d("MQTTSERVICE", "Connection is failed")
            e.printStackTrace()

        }
    }

    fun subscribe(topic: String): MutableLiveData<String> {
        mqttClient?.subscribe(topic, 0)
        mqttClient?.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {
                // Gérer la perte de la connexion
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                if (topic != null && message != null) {
                    Log.d("MQTTSERVICE", "topic: $topic")
                    val payload = message.payload
                    val messageString = String(payload, Charsets.UTF_8)
                    Log.d("MQTTSERVICE", "message: $messageString")
                    _mqttStatusLiveData.postValue(messageString)
                }
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                // Ne rien faire
            }
        })
        return _mqttStatusLiveData
    }

    fun publishToTopic(topic: String, message: String) {
        try {
            val mqttMessage = MqttMessage(message.toByteArray(Charsets.UTF_8))
            Log.d("MQTTSERVICE", "message: $message")
            mqttClient?.publish(topic,mqttMessage)
        }catch (e:MqttException){
            e.printStackTrace()
        }

    }
}
