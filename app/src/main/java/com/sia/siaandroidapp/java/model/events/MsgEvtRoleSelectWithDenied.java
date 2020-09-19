package com.sia.siaandroidapp.java.model.events;

public class MsgEvtRoleSelectWithDenied {

    private boolean granted = true;

    public MsgEvtRoleSelectWithDenied(boolean granted) {
        this.granted = granted;
    }

    public boolean isGranted() {
        return granted;
    }

    public void setGranted(boolean granted) {
        this.granted = granted;
    }
}
