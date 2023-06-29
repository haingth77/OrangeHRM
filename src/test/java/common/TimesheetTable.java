package common;

public class TimesheetTable {
    String project;
    String activity;
    String monday;
    String tuesday;
    String wednesday;
    String thursday;
    String friday;
    String satuday;
    String sunday;
    String total;

    public TimesheetTable(String project, String activity, String monday, String tuesday, String wednesday, String thursday, String friday, String satuday, String sunday, String total) {
        this.project = project;
        this.activity = activity;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday= thursday;
        this.friday= friday;
        this.satuday= satuday;
        this.sunday= sunday;
        this.total = total;
    }

    public void infor() {
        System.out.println(this.project);
        System.out.println(this.activity);
        System.out.println(this.monday);
        System.out.println(this.tuesday);
        System.out.println(this.wednesday);
        System.out.println(this.thursday);
        System.out.println(this.friday);
        System.out.println(this.satuday);
        System.out.println(this.sunday);
        System.out.println(this.total);
    }
}
