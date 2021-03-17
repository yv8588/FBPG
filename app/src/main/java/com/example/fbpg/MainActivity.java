
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText name_et,lastName_et,class_et,classNum_et;
    Spinner spinner;
    AlertDialog.Builder adb;
    String[]spin={"first vaccine","cant get vaccinated"};
    boolean canGet=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_et=(EditText)findViewById(R.id.name_et);
        lastName_et=(EditText)findViewById(R.id.lastName_et);
        class_et=(EditText)findViewById(R.id.class_et);
        classNum_et=(EditText)findViewById(R.id.classNum_et);
        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adp=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,spin);// spinner adapter.
        spinner.setAdapter(adp);

    }

    public void sub(View view) {
        if(canGet){

        }
        else{

        }
    }

    /**
     * when item clicked in the spinner updates vaccine information.
     * <p>
     * @param parent the adapter.
     * @param view the view got clicked.
     * @param position the position in the adapter.
     * @param id the row id.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position==0){
            adb=new AlertDialog.Builder(this);
            adb.setTitle("First vaccine info");
            final EditText place=new EditText(this);
            place.setHint("vaccine place");
            final DatePicker date=new DatePicker(this);
            adb.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                     Vaccine vaccine=new Vaccine(place.getText().toString(),)
                }
            });
            AlertDialog ad=adb.create();
            ad.show();

        }
        else{
          canGet=false;
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
}