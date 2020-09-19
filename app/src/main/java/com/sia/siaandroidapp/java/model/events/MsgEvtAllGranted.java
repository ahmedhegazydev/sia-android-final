package com.sia.siaandroidapp.java.model.events;

public class MsgEvtAllGranted {

    private boolean granted = true;

    public MsgEvtAllGranted(boolean granted) {
        this.granted = granted;
    }

    public boolean isGranted() {
        return granted;
    }

    public void setGranted(boolean granted) {
        this.granted = granted;
    }
}
