package me.kevinkang.sightreadingskillbuilder;

/**
 * Created by kevink97 on 7/8/16.
 */
public class Note {
    private String note;
    private int resId;

    public static String A = "A";
    public static String B = "B";
    public static String C = "C";
    public static String D = "D";
    public static String E = "E";
    public static String F = "F";
    public static String G = "G";

    public Note(String note, int resId) {
        this.note = note;
        this.resId = resId;
    }

    public String getNote() {
        return note;
    }

    public int getResId() {
        return resId;
    }
}
