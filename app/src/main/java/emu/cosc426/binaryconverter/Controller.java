package emu.cosc426.binaryconverter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Controller {
    private ImageButton[] switchObjects;
    private Switch[] switches;
    private ImageButton arrow;
    Boolean up = false;
    private EditText decimal;
    private RadioGroup abGroup;
    private RadioButton a;
    private RadioButton b;
    private boolean classB;
    private int total;
    private int imageOn;
    private int imageOff;
    public boolean useClassA;

    public Controller(ImageButton[] arr, Switch[] arr2, ImageButton i, EditText e){
        switchObjects = arr;
        switches = arr2;
        arrow = i;
        decimal = e;
        useClassA = true;
        setHandlers();
    }
    //Sets hat the on/off images are supposed to be
    public void setImages(int on, int off){
        imageOn = on;
        imageOff = off;
    }
    //Sets the onClick Listeners
    public void setHandlers(){
        for(int i=0; i<switchObjects.length; i++){
            switchObjects[i].setOnClickListener(new SwitchHandler(i));
        }
        decimal.addTextChangedListener(new TextHandler());

    }
    //Sets the class A/Class B radio Buttons
    public void setRadios(RadioGroup group, RadioButton a, RadioButton b){
        abGroup = group;
        this.a = a;
        this.b = b;
        this.a.setOnClickListener(new RadioHandler());
        this.b.setOnClickListener(new RadioHandler());
        this.a.setClickable(false);
        this.b.setClickable(false);
    }
    private class RadioHandler implements View.OnClickListener{
        public void onClick(View v){
            if(up){
                if(a.isChecked()){
                    classB = false;
                }else{
                    classB = true;
                }
                String temp = decimal.getText().toString();
                try{
                    total = Integer.parseInt(temp);
                }catch(Exception e){
                    total = 0;
                }

                updateSwitches();
            }
        }
    }
    //Onclick listener for the switches
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
                //Check if it is the class B Switch
                if(id == 7){
                    if (switches[id].isChecked()) {
                        switches[id].setChecked(false);
                        switchObjects[id].setImageResource(imageOff);
                        classB = false;
                    }else{
                        switches[id].setChecked(true);
                        switchObjects[id].setImageResource(imageOn);
                        classB = true;
                    }
                }else{
                    //Increment or decrement the value based on if it is up or down
                    if (switches[id].isChecked()) {
                        switches[id].setChecked(false);
                        switchObjects[id].setImageResource(imageOff);
                        total -= value;
                    } else {
                        switches[id].setChecked(true);
                        switchObjects[id].setImageResource(imageOn);
                        total += value;
                    }
                }

                updateText();
            }
        }
    }
    public void updateText(){
        //Sets the text in the output box
        if(total == 0){
            decimal.setText("");
        }else{
            decimal.setText(Integer.toString(total));
        }

        if(useClassA) {
            if (classB) {
                b.setChecked(true);
                a.setChecked(false);
            } else {
                b.setChecked(false);
                a.setChecked(true);
            }
        }
    }
    public void updateSwitches(){
        resetSwitches();
        //Set the switches needed to get the total to 0
        if((total - 64) >= 0){
            total -= 64;
            switches[6].setChecked(true);
            switchObjects[6].setImageResource(imageOn);
        }
        if((total - 32) >= 0){
            total -= 32;
            switches[5].setChecked(true);
            switchObjects[5].setImageResource(imageOn);
        }
        if((total - 16) >= 0){
            total -= 16;
            switches[4].setChecked(true);
            switchObjects[4].setImageResource(imageOn);
        }
        if((total - 8) >= 0){
            total -= 8;
            switches[3].setChecked(true);
            switchObjects[3].setImageResource(imageOn);
        }
        if((total - 4) >= 0){
            total -= 4;
            switches[2].setChecked(true);
            switchObjects[2].setImageResource(imageOn);
        }
        if((total - 2) >= 0){
            total -= 2;
            switches[1].setChecked(true);
            switchObjects[1].setImageResource(imageOn);
        }
        if((total - 1) >= 0){
            total -= 1;
            switches[0].setChecked(true);
            switchObjects[0].setImageResource(imageOn);
        }
        if(useClassA) {
            if (classB) {
                switches[7].setChecked(true);
                switchObjects[7].setImageResource(imageOn);
            }
        }
    }
    public void resetSwitches(){
        //Set all the switches to off
        for(int i=0; i<switches.length; i++){
            switches[i].setChecked(false);
            switchObjects[i].setImageResource(imageOff);
        }

    }
    public void flip(){
        //Called when the arrow is clicked
        //Enables or disables different parts of the app
        if(up){
            up = false;
            arrow.setImageResource(R.drawable.arrowdown);
            decimal.setEnabled(false);
            if(useClassA) {
                abGroup.setEnabled(false);
                a.setClickable(false);
                b.setClickable(false);
            }
            resetSwitches();
            total = 0;
            classB = false;
            updateText();
        }else{
            up = true;
            arrow.setImageResource(R.drawable.arrowup);
            decimal.setEnabled(true);
            if(useClassA) {
                abGroup.setEnabled(true);
                a.setClickable(true);
                b.setClickable(true);
            }
            resetSwitches();
            total = 0;
            classB = false;
            updateText();
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
                    //Determines of the number input is too large
                    if(useClassA){
                        //Class A counts as an extra switch so subtract it from the length
                        if(total >= Math.pow(2, (switches.length-1))){
                            total = 0;
                            decimal.setText("");
                        }
                    }else{
                        if(total >= Math.pow(2, switches.length)){
                            total = 0;
                            decimal.setText("");
                        }
                    }
                    updateSwitches();
                } catch (Exception e) {
                    resetSwitches();
                    total = 0;
                }
            }
        }
    }
}
