package emu.cosc426.binaryconverter;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageButton[] switches = new ImageButton[8];
    EditText decimal;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        decimal = (EditText) findViewById(R.id.decimal);
        setSwitches();
        setHandlers();
    }
    public void setSwitches(){
        switches[0] = (ImageButton)findViewById(R.id.s0);
        switches[1] = (ImageButton)findViewById(R.id.s1);
        switches[2] = (ImageButton)findViewById(R.id.s2);
        switches[3] = (ImageButton)findViewById(R.id.s3);
        switches[4] = (ImageButton)findViewById(R.id.s4);
        switches[5] = (ImageButton)findViewById(R.id.s5);
        switches[6] = (ImageButton)findViewById(R.id.s6);
        switches[7] = (ImageButton)findViewById(R.id.s7);
    }
    public void setHandlers(){
        for(int i=0; i<switches.length; i++){
            switches[i].setOnClickListener(new switchHandler(i));
        }
    }
    private class switchHandler implements View.OnClickListener{

        private int id;
        private int value;
        private boolean checked;
        public switchHandler (int i){
            id = i;
            value = (int)Math.round(Math.pow(2, id));
        }
        @Override
        public void onClick(View v) {
            if(checked){
                checked = false;
                switches[id].setImageResource(R.drawable.switchverticaloff);
                total -= value;
            }else{
                checked = true;
                switches[id].setImageResource(R.drawable.switchverticalon);
                total += value;
            }
            updateText();
        }
    }
    public void updateText(){
        decimal.setText(Integer.toString(total));
    }
    public void clicked(View v){
        ImageButton image = (ImageButton)v;
        image.setImageResource(R.drawable.switchverticalon);
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
