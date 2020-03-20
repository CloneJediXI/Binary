package emu.cosc426.binaryconverter;

public class Switch {
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
