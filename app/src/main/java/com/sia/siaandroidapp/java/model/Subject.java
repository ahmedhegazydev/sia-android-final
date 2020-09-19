package com.sia.siaandroidapp.java.model;

public class Subject {

    private String title;
    private int unSelectedImage;
    private int SelectedImage;
    private boolean isChecked;

    public Subject(String title, int unSelectedImage, int selectedImage) {
        this.title = title;
        this.unSelectedImage = unSelectedImage;
        SelectedImage = selectedImage;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getUnSelectedImage() {
        return unSelectedImage;
    }

    public void setUnSelectedImage(int unSelectedImage) {
        this.unSelectedImage = unSelectedImage;
    }

    public int getSelectedImage() {
        return SelectedImage;
    }

    public void setSelectedImage(int selectedImage) {
        SelectedImage = selectedImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
