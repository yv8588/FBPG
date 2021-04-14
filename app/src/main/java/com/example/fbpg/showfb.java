package com.example.fbpg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.fbpg.FBref.refStudents;

public class showfb extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView show;
    Spinner options;
    String[]Options={"by class number","by class","all","cant"};
    ArrayList<Student>students=new ArrayList<>();
    AlertDialog.Builder adb;
    String Class,ClassNumber;
    String info;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showfb);
        show=(TextView)findViewById(R.id.show);
        options=(Spinner)findViewById(R.id.options);
        ArrayAdapter<String>adp=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,Options);
        options.setAdapter(adp);
        options.setOnItemSelectedListener(this);
        show.setText("");

    }
    /**
     * when option selected makes the chosen query.
     * <p>
     * @param parent the list.
     * @param view the row view.
     * @param position the position in the array list.
     * @param id the row id.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        adb=new AlertDialog.Builder(this);
        final EditText et1=new EditText(this);
        switch (position){
            case 0:
                et1.setHint("the class number");
                adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
                    /**
                     * when clicked gets out and saves class name.
                     * <p>
                     * @param dialog the dialog.
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Class="";
                        String forUse = et1.getText().toString();
                        if (forUse.equals(null))
                            Toast.makeText(showfb.this, "enter class number", Toast.LENGTH_SHORT).show();
                        else {
                            ClassNumber = forUse;
                            dialog.dismiss();
                        }
                    }
                });
                adb.setView(et1);
                AlertDialog ad1=adb.create();
                ad1.show();
                pos=0;
                break;

            case 1:
                et1.setHint("the class name");
                adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
                    /**
                     * when clicked gets out and saves class name.
                     * <p>
                     * @param dialog the dialog.
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Class="";
                        String forUse = et1.getText().toString();
                        if (forUse.equals(null))
                            Toast.makeText(showfb.this, "enter class", Toast.LENGTH_SHORT).show();
                        else {
                            Class = forUse;
                            dialog.dismiss();
                        }
                    }
                });
                adb.setView(et1);
                AlertDialog ad2=adb.create();
                ad2.show();
                pos=1;
                break;

            case 2:
                ValueEventListener vel2=new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        students.clear();
                        for(DataSnapshot data:snapshot.getChildren()) {
                            Student tmp = data.getValue(Student.class);
                            if(tmp.isCan())
                                students.add(tmp);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
                Query query2 =  refStudents.orderByChild("sclass");
                query2.addListenerForSingleValueEvent(vel2);

                break;

            case 3:
                ValueEventListener vel3=new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        students.clear();
                        for(DataSnapshot data:snapshot.getChildren()) {
                            Student tmp = data.getValue(Student.class);
                            students.add(tmp);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
                Query query3=  refStudents.orderByChild("can").equalTo(false);
                query3.addListenerForSingleValueEvent(vel3);
                break;
        }
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
        if(s.equals("enter student")) {
            si = new Intent(this,MainActivity.class);
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

    public void sub(View view) {
        if(pos==0){
            ValueEventListener vel0=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    students.clear();
                    for(DataSnapshot data:snapshot.getChildren()) {
                        Student tmp = data.getValue(Student.class);
                        students.add(tmp);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            Toast.makeText(showfb.this, ClassNumber, Toast.LENGTH_SHORT).show();
            Query query0 =  refStudents.orderByChild("classNumber").equalTo(ClassNumber);
            query0.addListenerForSingleValueEvent(vel0);

        }
        else if(pos==1){
            ValueEventListener vel1=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    students.clear();
                    for(DataSnapshot data:snapshot.getChildren()) {
                        Student tmp = data.getValue(Student.class);
                        if(tmp.isCan())
                            students.add(tmp);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            Toast.makeText(showfb.this, Class, Toast.LENGTH_SHORT).show();
            Query query1 = refStudents.orderByChild("sclass").equalTo(Class);
            query1.addListenerForSingleValueEvent(vel1);

        }
        else {
            info = "";
            for (int i = 0; i < students.size(); i++) {
                info = info + "\n" + students.get(i).toString();
            }
            show.setText(info);
        }
    }
}