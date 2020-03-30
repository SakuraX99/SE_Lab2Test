package fudan.se.lab2.controller.request;

public class HoldmeetingRequest {
    private String shortName;
    private String FullName;
    private String Date;
    private String location;
    private String Deadline;
    private String DateForResults;

    public HoldmeetingRequest() {}

    public HoldmeetingRequest(String shortname, String fullName, String date, String location, String deadline, String dateForResults) {
        shortName = shortname;
        FullName = fullName;
        Date = date;
        this.location = location;
        Deadline = deadline;
        DateForResults = dateForResults;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortname) {
        shortName = shortname;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getDateForResults() {
        return DateForResults;
    }

    public void setDateForResults(String dateForResults) {
        DateForResults = dateForResults;
    }

}
