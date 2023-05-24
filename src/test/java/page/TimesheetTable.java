package page;

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

    public String getProjectName() {
        System.out.println(project);
        return project;
    }

    public String getActivityName() {
        return this.activity;
    }

    public String getMondayTime() {
        return this.monday;
    }

    public String getTuesdayTime() {
        return this.tuesday;
    }

    public String getWednesdayTime() {
        return this.wednesday;
    }

    public String getThursdayTime() {
        return this.thursday;
    }

    public String getFridayTime() {
        return this.friday;
    }

    public String getTotalTime() {
        return this.total;
    }


}
