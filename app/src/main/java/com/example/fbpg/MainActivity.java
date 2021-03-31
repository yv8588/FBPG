
package com.example.fbpg;
/**
 * @author yoad wolfson.
 * @version 1.0
 * Fire base data base exercise.
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import static com.example.fbpg.FBref.refStudents;

public class MainActivity extends AppCompatActivity  {
    EditText name_et,lastName_et,class_et,classNum_et,date_et,place_et;
    Switch can;
    Vaccine vac1=new Vaccine(),vac2=new Vaccine();
    String name,lastName,Class,ClassNum;
    boolean canGet=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_et=(EditText)findViewById(R.id.name_et);
        lastName_et=(EditText)findViewById(R.id.lastName_et);
        class_et=(EditText)findViewById(R.id.class_et);
        classNum_et=(EditText)findViewById(R.id.classNum_et);
        can=(Switch)findViewById(R.id.can);
        place_et=(EditText)findViewById(R.id.place_et);
        date_et=(EditText)findViewById(R.id.date_et);

    }

    /**
     * push the info to data base on finish.
     * <p>
     * @param view the button that got clicked.
     */
    public void sub(View view) {
        name=name_et.getText().toString();
        lastName=lastName_et.getText().toString();
        Class=class_et.getText().toString();
        ClassNum=classNum_et.getText().toString();
        vac1.setDate(date_et.getText().toString());
        vac1.setPlace(place_et.getText().toString());
        if(name==null||lastName==null||Class==null||ClassNum==null)
            Toast.makeText(MainActivity.this, "enter all the Student information", Toast.LENGTH_SHORT).show();
        else if(canGet){
            if(vac1.getDate().equals("")||vac1.getPlace().equals(""))
                Toast.makeText(MainActivity.this, "enter all the Vaccine information", Toast.LENGTH_SHORT).show();
            else {
                Student student=new Student(name,lastName,Class,ClassNum,vac1,vac2,true);
                refStudents.push().setValue(student);
            }
        }
        else{
            Student student=new Student(name,lastName,Class,ClassNum,vac1,vac2,false);
            refStudents.push().setValue(student);
        }
        class_et.setText("");
        name_et.setText("");
        lastName_et.setText("");
        classNum_et.setText("");
        place_et.setText("");
        date_et.setText("");
        can.setChecked(false);
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
        else if(s.equals("second vaccine")){
            si=new Intent(this,secondVacc.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSwitch(View view) {
        canGet=!can.isChecked();

    }
}