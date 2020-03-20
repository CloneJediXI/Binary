package emu.cosc426.binaryconverter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Controller {
    ImageButton[] switchObjects;
    Switch[] switches;
    ImageButton arrow;
    Boolean up = false;
    EditText decimal;
    int total;

    public Controller(ImageButton[] arr, Switch[] arr2, ImageButton i, EditText e){
        switchObjects = arr;
        switches = arr2;
        arrow = i;
        decimal = e;
        setHandlers();
    }
    public void setHandlers(){
        for(int i=0; i<switchObjects.length; i++){
            switchObjects[i].setOnClickListener(new SwitchHandler(i));
        }
        decimal.addTextChangedListener(new TextHandler());
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
    public void flip(){
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
    private class TextHandler implements TextWatcher {

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
}
