package page;

public class TimesheetTable {
    String project;
    String activity;
    String monday;
    String tuesday;
    String wednesday;
    String thursday;
    String friday;
    String total;

    public TimesheetTable(String project, String activity, String monday, String tuesday, String wednesday, String thursday, String friday, String total) {
        this.project = project;
        this.activity = activity;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday= thursday;
        this.friday= friday;
    }

    public String getProjectName() {
        return this.project;
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
