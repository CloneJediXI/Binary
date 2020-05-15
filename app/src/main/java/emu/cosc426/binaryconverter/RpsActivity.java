package emu.cosc426.binaryconverter;

import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RpsActivity extends AppCompatActivity {

    ImageButton[] switchObjects = new ImageButton[7];
    Switch[] switches = new Switch[7];
    ImageButton arrow;
    EditText decimal;

    Controller c;

    RelativeLayout addressWrapper;
    RelativeLayout decodeWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rps);

        arrow = findViewById(R.id.arrow);
        decimal =  findViewById(R.id.decimal);

        //Make the Controller Object
        setSwitches();
        c = new Controller(switchObjects, switches, arrow, decimal);
        c.setImages(R.drawable.switchverticaloffw, R.drawable.switchverticalonw);

        c.useClassA = false;
        ImageView dipswitch = findViewById(R.id.image2);
        //Sets on Click Listeners
        dipswitch.setOnClickListener(new RpsActivity.clickWatcher(R.drawable.rpsright, R.drawable.rpsleft, dipswitch));

        addressWrapper = findViewById(R.id.addresswrapper);
        decodeWrapper = findViewById(R.id.decodewrapper);
        addressWrapper.setVisibility(View.VISIBLE);
        decodeWrapper.setVisibility(View.GONE);

    }
    public void setSwitches(){
        //Get references to the switches
        switchObjects[0] = findViewById(R.id.s72);
        switchObjects[1] = findViewById(R.id.s62);
        switchObjects[2] = findViewById(R.id.s52);
        switchObjects[3] = findViewById(R.id.s42);
        switchObjects[4] = findViewById(R.id.s32);
        switchObjects[5] = findViewById(R.id.s22);
        switchObjects[6] = findViewById(R.id.s12);

        switches[0] = new Switch(7);
        switches[1] = new Switch(6);
        switches[2] = new Switch(5);
        switches[3] = new Switch(4);
        switches[4] = new Switch(3);
        switches[5] = new Switch(2);
        switches[6] = new Switch(1);

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
        }

        return super.onOptionsItemSelected(item);
    }
    private class clickWatcher implements View.OnClickListener{

        int imageRight;
        int imageLeft;
        boolean right;
        ImageView image;

        public clickWatcher(int right, int left, ImageView image){
            imageLeft = left;
            imageRight = right;
            this.right = false;
            this.image = image;
        }
        @Override
        public void onClick(View v) {
            if(right){
                image.setImageResource(imageLeft);
                right = false;
                addressWrapper.setVisibility(View.VISIBLE);
                decodeWrapper.setVisibility(View.GONE);
            }else{
                image.setImageResource(imageRight);
                right = true;
                addressWrapper.setVisibility(View.GONE);
                decodeWrapper.setVisibility(View.VISIBLE);
            }
        }
    }
}
