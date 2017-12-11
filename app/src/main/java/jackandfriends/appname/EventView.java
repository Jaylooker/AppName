package jackandfriends.appname;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class EventView extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static final String TAG = "EventView";
    TextView Location;
    TextView Title;
    TextView Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("event");

        Location = (TextView) findViewById(R.id.txtviewlocation);
        Title = (TextView) findViewById(R.id.txtviewtitle);
        Description = (TextView) findViewById(R.id.txtviewdescription);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value= getIntent().getStringExtra("key");
                Toast.makeText(EventView.this, "" + value,
                        Toast.LENGTH_SHORT).show();
                Iterable<DataSnapshot> snaps = dataSnapshot.getChildren();
                for(DataSnapshot i : snaps) {
                    Iterable<DataSnapshot> y = i.getChildren();
                    for (DataSnapshot keyvalue : y)  {
                        if (keyvalue.getKey().equals("Location")) {
                            Location.setText(keyvalue.getValue().toString());
                        }
                        if (keyvalue.getKey().equals("Title")) {
                            Title.setText(keyvalue.getValue().toString());
                        }
                        if (keyvalue.getKey().equals("Description")) {
                            Description.setText(keyvalue.getValue().toString());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnhome) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}