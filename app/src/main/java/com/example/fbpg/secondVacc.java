package com.example.fbpg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.fbpg.FBref.refStudents;
import static com.example.fbpg.R.layout.support_simple_spinner_dropdown_item;

public class secondVacc extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinames;
    EditText Place,Date;
    ArrayList<String>studName=new ArrayList<>();
    ArrayList<Student>student=new ArrayList<>();
    ArrayList<String>keys=new ArrayList<>();
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_vacc);
        spinames=(Spinner)findViewById(R.id.spinames);
        Place=(EditText)findViewById(R.id.Place);
        Date=(EditText)findViewById(R.id.Date);
        refStudents.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                for (DataSnapshot data : dS.getChildren()) {
                    Student stuTmp = data.getValue(Student.class);
                    String str=(String)data.getKey();
                        if (stuTmp.isCan()) {
                            student.add(stuTmp);
                            studName.add(stuTmp.getName());
                            keys.add(str);
                    }
                }
                ArrayAdapter<String> adp=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,studName);// spinner adapter.
                spinames.setAdapter(adp);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
        spinames.setOnItemSelectedListener(this);
    }
    /**
     * submits the person to the db.
     * <p>
     * @param view
     */
    public void submit(View view) {
        if(student.get(pos).isCan()){
            if(Date.getText().toString().equals("")||Place.getText().toString().equals(""))
                Toast.makeText(secondVacc.this, "enter all the Vaccine information", Toast.LENGTH_SHORT).show();
            else {
                student.get(pos).setVac2(new Vaccine(Place.getText().toString(),Date.getText().toString()));
                refStudents.child(keys.get(pos)).setValue(student.get(pos));
            }
        }
        else{
            Toast.makeText(secondVacc.this, "the person cant get vaccinated.", Toast.LENGTH_SHORT).show();
            student.get(pos).setVac2(new Vaccine("",""));
            student.get(pos).setVac1(new Vaccine("",""));
            refStudents.child(keys.get(pos)).setValue(student);
        }
        Date.setText("");
        Place.setText("");
    }

    @Override
    /**
     * when name selected on the spinner saves position.
     * <p>
     * @param position the position in the ArrayList.
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(secondVacc.this, "hello world", Toast.LENGTH_SHORT).show();
        pos=position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    /**
     * creates the xml general option menu.
     * <p>
     * @param menu the xml general menu.
     * @return true if the menu was created.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * when item go clicked in the option menu goes to the activity that was chosen
     * <p>
     * @param item the item in the menu that got clicked
     * @return true if was operated successfully
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent si;
        String s=item.getTitle().toString();
        if(s.equals("filterd data base")) {
            si = new Intent(this,showfb.class);
            startActivity(si);
        }
        else if (s.equals("credits")){
            si=new Intent(this,credits.class);
            startActivity(si);
        }
        else if(s.equals("update data base")){
            si=new Intent(this,update.class);
            startActivity(si);
        }
        else if(s.equals("enter student")){
            si=new Intent(this,MainActivity.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}