package com.example.fbpg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
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
        else if (s.equals("enter student")){
            si=new Intent(this,MainActivity.class);
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