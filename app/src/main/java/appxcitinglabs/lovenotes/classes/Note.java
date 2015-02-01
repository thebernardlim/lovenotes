package appxcitinglabs.lovenotes.classes;

import java.util.Date;

/**
 * Created by Bernard on 2/1/2015.
 */
public class Note {
    int weekNo;
    String note;
    Date dateActivate;
    boolean activated;

    public Note(){}

    public Note(int weekNo, String note, Date dateActivate, boolean activated)
    {
        this.weekNo = weekNo;
        this.note = note;
        this.dateActivate = dateActivate;
        this.activated = activated;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDateActivate() {
        return dateActivate;
    }

    public void setDateActivate(Date dateActivate) {
        this.dateActivate = dateActivate;
    }

}
