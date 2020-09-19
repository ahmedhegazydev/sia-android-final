package com.sia.siaandroidapp.java.model;

public class Role {

    private String mainTitle;
    private String subTitle;
    private int image;
    private String signUpAs;
    private int selectedPos;

    public Role(String mainTitle, String subTitle, int image, String signUpAs) {
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.image = image;
        this.signUpAs = signUpAs;
    }

    public int getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
    }

    public String getSignUpAs() {
//        return SiaUtils.getSignUpAs(getSelectedPos());
        return signUpAs;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setSignUpAs(String signUpAs) {
        this.signUpAs = signUpAs;
    }
}
