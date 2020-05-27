package emu.cosc426.binaryconverter;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RpsDecoder {
    private ImageButton[] switchObjects;
    private Switch[] switches;
    private RadioGroup c1;
    private int c1Code;
    private RadioGroup c2;
    private int c2Code;
    private RadioGroup c3;
    private int c3Code;
    private int imageOn;
    private int imageOff;
    private ImageButton arrow;
    private boolean up;

    public RpsDecoder(ImageButton[] arr, Switch[] arr2, ImageButton i){
        switchObjects = arr;
        switches = arr2;
        arrow = i;
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
    }
    //Sets the class A/Class B radio Buttons
    public void setRadios(RadioGroup group1, RadioGroup group2, RadioGroup group3){
        c1 = group1;
        c2 = group2;
        c3 = group3;
        RadioButton temp;
        for(int i=0; i<c1.getChildCount(); i++){
            temp = (RadioButton)c1.getChildAt(i);

            temp.setOnClickListener(new RadioHandler(1, i));
            temp.setClickable(false);
        }
        for(int i=0; i<c2.getChildCount(); i++){
            temp = (RadioButton)c2.getChildAt(i);

            temp.setOnClickListener(new RadioHandler(2, i));
            temp.setClickable(false);
        }
        for(int i=0; i<c3.getChildCount(); i++){
            temp = (RadioButton)c3.getChildAt(i);

            temp.setOnClickListener(new RadioHandler(3, i));
            temp.setClickable(false);
        }

    }
    private class RadioHandler implements View.OnClickListener{
        private int group;
        private int value;
        public RadioHandler(int g, int v){
            group = g;
            value = v;
        }
        public void onClick(View v){
            RadioButton temp;
            if(up){
                switch (group){
                    case 1:
                        c1Code = value;
                        for(int i=0; i<c1.getChildCount(); i++){
                            temp = (RadioButton)c1.getChildAt(i);
                            if(i != value){
                                temp.setChecked(false);
                            }else{
                                temp.setChecked(true);
                            }
                        }
                        break;
                    case 2:
                        c2Code = value;
                        for(int i=0; i<c2.getChildCount(); i++){
                            temp = (RadioButton)c2.getChildAt(i);
                            if(i != value){
                                temp.setChecked(false);
                            }else{
                                temp.setChecked(true);
                            }
                        }
                        break;
                    case 3:
                        c3Code = value;
                        for(int i=0; i<c3.getChildCount(); i++){
                            temp = (RadioButton)c3.getChildAt(i);
                            if(i != value){
                                temp.setChecked(false);
                            }else{
                                temp.setChecked(true);
                            }
                        }
                        break;
                }
                updateSwitches();
            }
        }
    }
    //Onclick listener for the switches
    private class SwitchHandler implements View.OnClickListener{

        private int id;
        private int group;
        private int value;
        public SwitchHandler (int i){
            id = i;
            //Determine which circuit group the switch belongs to and what its value it
            if(id == 1 || id == 2){
                group = 1;
                if(id == 1){
                    value = 2;
                }else{
                    value = 1;
                }
            }else if(id == 4 || id == 5){
                group = 2;
                if(id == 4){
                    value = 2;
                }else{
                    value = 1;
                }
            }else if(id == 6){
                group = 3;
                value = 1;
            }else {
                //There are 2 switches that do nothing
                value = 0;
            }
        }
        @Override
        public void onClick(View v) {
            if(!up) {
                //Increment or decrement the value based on if it is up or down
                if (switches[id].isChecked()) {
                    switches[id].setChecked(false);
                    switchObjects[id].setImageResource(imageOff);
                    switch (group){
                        case 1:
                            c1Code -= value;
                            break;
                        case 2:
                            c2Code -= value;
                            break;
                        case 3:
                            c3Code -= value;
                            break;
                    }
                } else {
                    switches[id].setChecked(true);
                    switchObjects[id].setImageResource(imageOn);
                    switch (group){
                        case 1:
                            c1Code += value;
                            break;
                        case 2:
                            c2Code += value;
                            break;
                        case 3:
                            c3Code += value;
                            break;
                    }
                }

                updateRadios();
            }
        }
    }
    private void updateRadios(){
        //Uncheck all the radios in each group
        RadioButton temp;
        for(int i=0; i<c1.getChildCount(); i++){
            temp = (RadioButton)c1.getChildAt(i);
            if(i == c1Code){
                temp.setChecked(true);
            }else{
                temp.setChecked(false);
            }

        }
        for(int i=0; i<c2.getChildCount(); i++){
            temp = (RadioButton)c2.getChildAt(i);
            if(i == c2Code){
                temp.setChecked(true);
            }else{
                temp.setChecked(false);
            }
        }
        for(int i=0; i<c3.getChildCount(); i++){
            temp = (RadioButton)c3.getChildAt(i);
            if(i == c3Code){
                temp.setChecked(true);
            }else{
                temp.setChecked(false);
            }
        }
        //Check the proper radio button based on the circuit code

    }
    public void resetSwitches(){
        //Set all the switches to off
        for(int i=0; i<switches.length; i++){
            switches[i].setChecked(false);
            switchObjects[i].setImageResource(imageOff);
        }

    }
    private void updateSwitches(){
        resetSwitches();
        switch (c1Code){
            case 1:
                switches[2].setChecked(true);
                switchObjects[2].setImageResource(imageOn);
                break;
            case 2:
                switches[1].setChecked(true);
                switchObjects[1].setImageResource(imageOn);
                break;
            case 3:
                switches[1].setChecked(true);
                switchObjects[1].setImageResource(imageOn);
                switches[2].setChecked(true);
                switchObjects[2].setImageResource(imageOn);
                break;
        }
        switch (c2Code){
            case 1:
                switches[5].setChecked(true);
                switchObjects[5].setImageResource(imageOn);
                break;
            case 2:
                switches[4].setChecked(true);
                switchObjects[4].setImageResource(imageOn);
                break;
            case 3:
                switches[4].setChecked(true);
                switchObjects[4].setImageResource(imageOn);
                switches[5].setChecked(true);
                switchObjects[5].setImageResource(imageOn);
                break;
        }
        switch (c3Code){
            case 1:
                switches[6].setChecked(true);
                switchObjects[6].setImageResource(imageOn);
                break;

        }
    }
    public void flip(){
        if(up){
            up = false;
            arrow.setImageResource(R.drawable.arrowdown);
        }else{
            up = true;
            arrow.setImageResource(R.drawable.arrowup);
        }
        RadioButton temp;
        for(int i=0; i<c1.getChildCount(); i++){
            temp = (RadioButton)c1.getChildAt(i);
            temp.setClickable(up);
            //temp.setChecked(false);
        }
        for(int i=0; i<c2.getChildCount(); i++){
            temp = (RadioButton)c2.getChildAt(i);
            temp.setClickable(up);
            //temp.setChecked(false);
        }
        for(int i=0; i<c3.getChildCount(); i++){
            temp = (RadioButton)c3.getChildAt(i);
            temp.setClickable(up);
            //temp.setChecked(false);
        }
        //resetSwitches();
        /*temp = (RadioButton)c1.getChildAt(0);
        temp.setChecked(true);
        temp = (RadioButton)c2.getChildAt(0);
        temp.setChecked(true);
        temp = (RadioButton)c3.getChildAt(0);
        temp.setChecked(true);*/
    }


}
