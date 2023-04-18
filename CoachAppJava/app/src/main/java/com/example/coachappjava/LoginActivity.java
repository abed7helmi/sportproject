package com.example.coachappjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        String[] data = {"1 : CoachSedak","2 : CoachMounir"};

        ListView listView = findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item, R.id.text_view, data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Récupération de l'élément sélectionné
                String item = (String) adapterView.getItemAtPosition(position);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                // Ajout d'un extra à l'intention
                if (item=="1 : CoachSedak"){
                    intent.putExtra("selected_coach_id", 1L);
                }else{
                    intent.putExtra("selected_coach_id", 2L);
                }


                // Démarrage de l'activité
                startActivity(intent);
            }
        });

    }
}