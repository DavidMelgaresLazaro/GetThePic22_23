package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.GlobalInfo;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class UserInfo extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    CollectionReference usuariosRef = db.collection("usuarios");
    String LastPoints;
    TextView lastpoints,lastlogin,maxGlobal,maxGlobalTT,jugadormaxTT,jugadormax;


    private FirebaseAuth mAuth;
    EditText editUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_info);
        lastlogin = findViewById(R.id.lastlogin);
        lastpoints = findViewById(R.id.lastpoints);
        maxGlobal = findViewById(R.id.maxglobal);
        jugadormax = findViewById(R.id.maxglobaluser);
        maxGlobalTT = findViewById(R.id.maxglobalTT);
        jugadormaxTT = findViewById(R.id.maxglobaluserTT);

        editUserName = findViewById(R.id.editUserName);


        lastlogin.setText(GlobalInfo.getInstance().getLastLogin().toString());

        displayUserPoints();
        displayWorldRecord();
        displayWorldRecordTT();

        findViewById(R.id.menubutton).setOnClickListener(v ->returnmenu());
        findViewById(R.id.buttonCommit).setOnClickListener(v -> updateUsername());
    }
    private void returnmenu()
    {
        Intent intent = new Intent(this.getApplicationContext(), menu.class);
        startActivity(intent);
        finish();
    }

    /***
     * DisplayUserPoints agafa els punts que ha fet l'usuari
     */
    private void displayUserPoints() {
        if (currentUser != null) {
            DocumentReference usuarioRef = db.collection("usuarios").document(currentUser.getUid());

            usuarioRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        lastpoints.setText(documentSnapshot.getLong("points").toString());
                        // Aquí puedes acceder a los datos del usuario y hacer lo que necesites con ellos
                    } else {
                        Log.d(TAG, "No se encontró ningún documento para el usuario actual");
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Error al obtener el documento del usuario actual", e);
                }
            });
        }
    }


    /***
     * DisplayWorldRecord fa una query dins la referencia de la "taula" d'usuaris per a tal de veure quin
     * es el que te més punts i introdueix a la vista els punts i qui els ha fets
     */
    private void displayWorldRecord()
        {
            //Hem decidit que sigui Last Points en vers de PR ja que de moment no te sentit dins el context de la
            //nostra aplicació. Aquesta conta punts per els nivells passats.

            Query query = usuariosRef.orderBy("points", Query.Direction.DESCENDING).limit(1);
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            maxGlobal.setText(document.get("points").toString());
                            if(document.get("nombre") != null)
                            {
                                jugadormax.setText(document.get("nombre").toString());
                            }
                            else
                            {
                                jugadormax.setText("user");
                            }
                            Log.d(TAG, "El máximo de puntos es: " + document.get("puntos"));
                            // Aquí puedes hacer lo que quieras con el máximo de puntos
                        }
                    } else {
                        Log.e(TAG, "Error al obtener el máximo de puntos", task.getException());
                    }
                }
            });
        }

    private void displayWorldRecordTT()
    {
        //Hem decidit que sigui Last Points en vers de PR ja que de moment no te sentit dins el context de la
        //nostra aplicació. Aquesta conta punts per els nivells passats.

        Query query = usuariosRef.orderBy("Levels_TT", Query.Direction.DESCENDING).limit(1);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        maxGlobalTT.setText(document.get("Levels_TT").toString());
                        if(document.get("nombre")!= null)
                        {
                            jugadormaxTT.setText(document.get("nombre").toString());
                        }
                        else
                        {
                            jugadormaxTT.setText("user");
                        }
                        Log.d(TAG, "El máximo de puntos es: " + document.get("LevelsTT"));
                        // Aquí puedes hacer lo que quieras con el máximo de puntos
                    }
                } else {
                    Log.e(TAG, "Error al obtener el máximo de puntos", task.getException());
                }
            }
        });
    }
    private void updateUsername()
    {
        String UserName = editUserName.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DocumentReference userRef = db.collection("usuarios").document(currentUser.getUid());
        userRef.get().addOnCompleteListener(task -> {
            userRef.update("nombre", UserName)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(UserInfo.this, "Nom d'suari cambiat", Toast.LENGTH_SHORT).show();
                            GlobalInfo.getInstance().setSelfName(UserName);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UserInfo.this, "Hi ha hagut algun error al cambiar el nom!", Toast.LENGTH_SHORT).show();

                        }
                    });
        });
    }
}