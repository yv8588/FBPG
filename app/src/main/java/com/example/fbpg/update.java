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
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.fbpg.FBref.refStudents;

public class update extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText showName,showLastName,showClass,showClassNum,secondVacPlace,secondVacDate,firstVacPlace,firstVacDate;
    Switch can_get;
    Spinner stuName;
    Student student;
    ArrayList<Student>stud=new ArrayList<>();
    ArrayList<String>studName=new ArrayList<>();
    ArrayList<String>keys=new ArrayList<>();
    int pos;
    String name,lastName,Class,classNum,firstPlace,firstDate,secondPlace,secondDate;
    boolean can;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        showName=(EditText)findViewById(R.id.showName);
        showLastName=(EditText)findViewById(R.id.showLastName);
        showClass=(EditText)findViewById(R.id.showClass);
        showClassNum=(EditText)findViewById(R.id.showClassNum);
        secondVacPlace=(EditText)findViewById(R.id.secondVacPlace);
        secondVacDate=(EditText)findViewById(R.id.secondVacDate);
        firstVacPlace=(EditText)findViewById(R.id.firstVacPlace);
        firstVacDate=(EditText)findViewById(R.id.firstVacDate);
        can_get=(Switch)findViewById(R.id.can_get);
        stuName=(Spinner)findViewById(R.id.stuName);
        refStudents.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stud.clear();
                studName.clear();
                keys.clear();
                for (DataSnapshot data : dS.getChildren()) {
                    Student stuTmp = data.getValue(Student.class);
                    String str=(String)data.getKey();
                    String name=stuTmp.getName();
                    stud.add(stuTmp);
                    studName.add(name);
                    keys.add(str);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,studName);// spinner adapter.
        stuName.setAdapter(adp);
        stuName.setOnItemSelectedListener(this);



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
     * when item go clicked in the option menu goes to the activity that was chosen.
     * <p>
     * @param item the item in the menu that got clicked.
     * @return true if was operated successfully.
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
        else if(s.equals("enter student")){
            si=new Intent(this,MainActivity.class);
            startActivity(si);
        }
        else if(s.equals("second vaccine")){
            si=new Intent(this,secondVacc.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * submits the updated info ino the db.
     * <p>
     * @param view the button that got clicked.
     */
    public void sub(View view) {
        name=showName.getText().toString();
        lastName=showLastName.getText().toString();
        Class=showClass.getText().toString();
        classNum=showClassNum.getText().toString();
        can=can_get.isChecked();
        firstDate=firstVacDate.getText().toString();
        firstPlace=firstVacPlace.getText().toString();
        secondDate=secondVacDate.getText().toString();
        secondPlace=secondVacPlace.getText().toString();
        if(name.equals("")||Class.equals("")||classNum.equals("")||lastName.equals(""))
            Toast.makeText(update.this, "enter all the information", Toast.LENGTH_SHORT).show();
        else if(can_get.isChecked()){
            if(firstDate.equals("")||firstPlace.equals("")||secondDate.equals("")||secondPlace.equals(""))
                Toast.makeText(update.this, "enter all the Vaccine information", Toast.LENGTH_SHORT).show();
            else{
                student=new Student(name,lastName,Class,classNum,new Vaccine(firstPlace,firstDate),new Vaccine(secondPlace,secondDate),can);
                stud.set(pos,student);
                refStudents.child(keys.get(pos)).setValue(stud.get(pos));
            }
        }
        else {
            student=new Student(name,lastName,Class,classNum,new Vaccine(firstPlace,firstDate),new Vaccine(secondPlace,secondDate),can);
            stud.set(pos,student);
            refStudents.child(keys.get(pos)).setValue(stud.get(pos));
        }
    }

    /**
     * when name is chosen shows its info.
     * <p>
     * @param parent
     * @param view
     * @param position in the array list.
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        student=stud.get(position);
        name=student.getName();
        lastName=student.getLastName();
        Class=student.getSclass();
        classNum=student.getClassNumber();
        can=student.isCan();
        firstDate=student.getVac1().getDate();
        firstPlace=student.getVac1().getPlace();
        secondDate=student.getVac2().getDate();
        secondPlace=student.getVac2().getPlace();
        showLastName.setText(lastName);
        showName.setText(name);
        showClass.setText(Class);
        showClassNum.setText(classNum);
        firstVacPlace.setText(firstDate);
        firstVacPlace.setText(firstPlace);
        secondVacDate.setText(secondDate);
        secondVacPlace.setText(secondPlace);
        if(!can)
            Toast.makeText(update.this, "student cant get vaccinated info wont vaccine wont be saved", Toast.LENGTH_SHORT).show();
        can_get.setChecked(true);
        pos=position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}