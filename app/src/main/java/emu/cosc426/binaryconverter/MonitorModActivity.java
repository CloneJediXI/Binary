package emu.cosc426.binaryconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MonitorModActivity extends AppCompatActivity {

    ImageButton[] switchObjects = new ImageButton[8];
    Switch[] switches = new Switch[8];
    ImageButton arrow;
    Boolean up = false;
    EditText decimal;
    RadioGroup abGroup;
    RadioButton a;
    RadioButton b;
    int total;

    Controller c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitormod);

        arrow = (ImageButton)findViewById(R.id.arrow);
        decimal = (EditText) findViewById(R.id.decimal);

        abGroup = findViewById(R.id.group);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        //Make the Controller Object
        setSwitches();
        c = new Controller(switchObjects, switches, arrow, decimal);
        c.setImages(R.drawable.switchhorizon, R.drawable.switchhorizoff);
        c.setRadios(abGroup, a, b);

        PhotoViewAttacher photoAttacher;
        photoAttacher= new PhotoViewAttacher((ImageView) findViewById(R.id.image));
        photoAttacher.update();
    }
    public void setSwitches(){
        //Get references to the switches
        switchObjects[0] = (ImageButton)findViewById(R.id.s0);
        switchObjects[1] = (ImageButton)findViewById(R.id.s1);
        switchObjects[2] = (ImageButton)findViewById(R.id.s2);
        switchObjects[3] = (ImageButton)findViewById(R.id.s3);
        switchObjects[4] = (ImageButton)findViewById(R.id.s4);
        switchObjects[5] = (ImageButton)findViewById(R.id.s5);
        switchObjects[6] = (ImageButton)findViewById(R.id.s6);
        switchObjects[7] = (ImageButton)findViewById(R.id.s7);
        switches[0] = new Switch(0);
        switches[1] = new Switch(1);
        switches[2] = new Switch(2);
        switches[3] = new Switch(3);
        switches[4] = new Switch(4);
        switches[5] = new Switch(5);
        switches[6] = new Switch(6);
        switches[7] = new Switch(7);
    }
    public void flip(View v){
        c.flip();
    }

    public void back(View v){ finish();}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_home) {
            Intent activity = new Intent(this, MainActivity.class);
            startActivity(activity);
        }else if (id == R.id.action_manual) {
            Intent activity = new Intent(this, ManualActivity.class);
            startActivity(activity);
        }

        return super.onOptionsItemSelected(item);
    }
}