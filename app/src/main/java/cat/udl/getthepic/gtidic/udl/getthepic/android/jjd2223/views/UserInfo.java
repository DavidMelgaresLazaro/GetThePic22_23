package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.views;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

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

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.helpers.GlobalInfo;
import cat.udl.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.R;

public class UserInfo extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    CollectionReference usuariosRef = db.collection("usuarios");
    String LastPoints;
    TextView lastpoints;
    TextView lastlogin;
    TextView maxGlobal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        lastlogin = findViewById(R.id.lastlogin);
        lastpoints = findViewById(R.id.lastpoints);
        maxGlobal = findViewById(R.id.maxglobal);
        lastlogin.setText(GlobalInfo.getInstance().getLastLogin().toString());

        displayUserPoints();
        displayWorldRecord();

        findViewById(R.id.menubutton).setOnClickListener(v ->returnmenu());

    }
    private void returnmenu()
    {
        Intent intent = new Intent(this, menu.class);
        finish();
    }

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
                            Log.d(TAG, "El máximo de puntos es: " + document.get("puntos"));
                            // Aquí puedes hacer lo que quieras con el máximo de puntos
                        }
                    } else {
                        Log.e(TAG, "Error al obtener el máximo de puntos", task.getException());
                    }
                }
            });
        }
}