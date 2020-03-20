package emu.cosc426.binaryconverter;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageButton[] switchObjects = new ImageButton[8];
    Switch[] switches = new Switch[8];
    ImageButton arrow;
    Boolean up = false;
    EditText decimal;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arrow = (ImageButton)findViewById(R.id.arrow);
        decimal = (EditText) findViewById(R.id.decimal);
        decimal.addTextChangedListener(new TextHandler());
        setSwitches();
        setHandlers();
    }
    public void setSwitches(){
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
    public void setHandlers(){
        for(int i=0; i<switchObjects.length; i++){
            switchObjects[i].setOnClickListener(new SwitchHandler(i));
        }
    }
    private class SwitchHandler implements View.OnClickListener{

        private int id;
        private int value;
        public SwitchHandler (int i){
            id = i;
            value = (int)Math.round(Math.pow(2, id));
        }
        @Override
        public void onClick(View v) {
            if(!up) {
                if (switches[id].isChecked()) {
                    switches[id].setChecked(false);
                    switchObjects[id].setImageResource(R.drawable.switchverticaloff);
                    total -= value;
                } else {
                    switches[id].setChecked(true);
                    switchObjects[id].setImageResource(R.drawable.switchverticalon);
                    total += value;
                }
                updateText();
            }
        }
    }
    private class TextHandler implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (up) {
                try {
                    String temp = decimal.getText().toString();
                    total = Integer.parseInt(temp);
                    updateSwitches();
                } catch (Exception e) {
                    resetSwitches();
                    total = 0;
                }
            }
        }
    }
    private class Switch{
        private int id;
        private int value;
        private boolean checked = false;

        public Switch(int i){
            id = i;
            value = (int)Math.round(Math.pow(2, id));
        }
        public int getId() {
            return id;
        }
        public void setChecked(boolean b){
            checked = b;
        }
        public boolean isChecked() {
            return checked;
        }
    }
    public void updateText(){
        decimal.setText(Integer.toString(total));
    }
    public void updateSwitches(){
        resetSwitches();
        if((total - 64) >= 0){
            total -= 64;
            switches[6].setChecked(true);
            switchObjects[6].setImageResource(R.drawable.switchverticalon);
        }
        if((total - 32) >= 0){
            total -= 32;
            switches[5].setChecked(true);
            switchObjects[5].setImageResource(R.drawable.switchverticalon);
        }
        if((total - 16) >= 0){
            total -= 16;
            switches[4].setChecked(true);
            switchObjects[4].setImageResource(R.drawable.switchverticalon);
        }
        if((total - 8) >= 0){
            total -= 8;
            switches[3].setChecked(true);
            switchObjects[3].setImageResource(R.drawable.switchverticalon);
        }
        if((total - 4) >= 0){
            total -= 4;
            switches[2].setChecked(true);
            switchObjects[2].setImageResource(R.drawable.switchverticalon);
        }
        if((total - 2) >= 0){
            total -= 2;
            switches[1].setChecked(true);
            switchObjects[1].setImageResource(R.drawable.switchverticalon);
        }
        if((total - 1) >= 0){
            total -= 1;
            switches[0].setChecked(true);
            switchObjects[0].setImageResource(R.drawable.switchverticalon);
        }
    }
    public void resetSwitches(){
        for(int i=0; i<switches.length; i++){
            switches[i].setChecked(false);
            switchObjects[i].setImageResource(R.drawable.switchverticaloff);
        }
    }

    public void flip(View v){
        if(up){
            up = false;
            arrow.setImageResource(R.drawable.arrowdown);
            decimal.setEnabled(false);
            resetSwitches();
            total = 0;
            decimal.setText("");
        }else{
            up = true;
            arrow.setImageResource(R.drawable.arrowup);
            decimal.setEnabled(true);
            resetSwitches();
            total = 0;
            decimal.setText("");
        }
    }

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
