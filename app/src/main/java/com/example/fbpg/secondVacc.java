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

public class secondVacc extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinames;
    EditText Place,Date;
    ArrayList<Student>stud=new ArrayList<>();
    ArrayList<String>studName=new ArrayList<>();
    ArrayList<Student>student=new ArrayList<>();
    ArrayList<String>keys=new ArrayList<>();
    ValueEventListener stuListener;
    Vaccine vac=new Vaccine();
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_vacc);
        spinames=(Spinner)findViewById(R.id.spinames);
        Place=(EditText)findViewById(R.id.Place);
        Date=(EditText)findViewById(R.id.Date);
          stuListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stud.clear();
                studName.clear();
                keys.clear();
                for (DataSnapshot data : dS.getChildren()) {
                    Student stuTmp = data.getValue(Student.class);
                    String str=(String)data.getKey();
                    String name=stuTmp.getName();
                    student.add(stuTmp);
                    studName.add(name);
                    keys.add(str);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };
        refStudents.addValueEventListener(stuListener);

        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,studName);// spinner adapter.
        spinames.setAdapter(adp);
        spinames.setOnItemSelectedListener(this);
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

    /**
     * submits the person to the db.
     * <p>
     * @param view
     */
    public void submit(View view) {
        vac.setDate(Date.getText().toString());
        vac.setPlace(Place.getText().toString());
        if(student.get(pos).isCan()){
            if(vac.getDate().equals("")||vac.getPlace().equals(""))
                Toast.makeText(secondVacc.this, "enter all the Vaccine information", Toast.LENGTH_SHORT).show();
            else {
                student.get(pos).setVac2(vac);
                refStudents.child(keys.get(pos)).setValue(student.get(pos));
            }
        }
        else{
            Toast.makeText(secondVacc.this, "the person cant get vaccinated.", Toast.LENGTH_SHORT).show();
            student.get(pos).setVac2(new Vaccine("",""));
            refStudents.child(keys.get(pos)).setValue(student);
        }
    }

    @Override
    /**
     * when name selected on the spinner saves position.
     * <p>
     * @param position the position in the ArrayList.
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pos=position;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onPause() {
        if (stuListener!=null) {
            refStudents.removeEventListener(stuListener);
        }

        super.onPause();
    }
}