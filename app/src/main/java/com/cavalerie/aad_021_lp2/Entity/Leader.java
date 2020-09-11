package com.cavalerie.aad_021_lp2.Entity;

public class Leader {

    private String name;
    private String score;
    private String country;
    private String badgeUrl;
    private Boolean hoursOrSkill;

    public Leader(String name, String score, String country, String badgeUrl, Boolean hoursOrSkill) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
        this.hoursOrSkill = hoursOrSkill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public Boolean getHoursOrSkill() {
        return hoursOrSkill;
    }

    public void setHoursOrSkill(Boolean hoursOrSkill) {
        this.hoursOrSkill = hoursOrSkill;
    }
}
