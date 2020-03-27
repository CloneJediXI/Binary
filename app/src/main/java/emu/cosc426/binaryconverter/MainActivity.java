package emu.cosc426.binaryconverter;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView ctrlLabel = findViewById(R.id.ctrlLabel);
        TextView miniLabel = findViewById(R.id.miniLabel);

        ctrlLabel.setOnClickListener(new clickWatcher(R.id.ctrlLabel, this));
        miniLabel.setOnClickListener(new clickWatcher(R.id.miniLabel, this));

    }
    private class clickWatcher implements View.OnClickListener{

        int id;
        Context context;

        public clickWatcher(int i, Context c){
            id = i;
            context = c;
        }
        @Override
        public void onClick(View v) {
            Intent activity;
            switch(id){
                case R.id.ctrlLabel:
                    activity = new Intent(context, CtrlActivity.class);
                    startActivity(activity);
                    break;
                case R.id.miniLabel:
                    activity = new Intent(context, MiniActivity.class);
                    startActivity(activity);
                    break;
            }
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
        if (id == R.id.action_ctrl) {
            Intent activity = new Intent(this, CtrlActivity.class);
            startActivity(activity);
        }
        if (id == R.id.action_mini) {
            Intent activity = new Intent(this, MiniActivity.class);
            startActivity(activity);
        }

        return true;
    }
}
