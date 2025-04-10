public class StudentAttendance {
    private String name;
    private String roll;
    private String date;
    private String status;

    // Constructors
    public StudentAttendance() {}

    public StudentAttendance(String name, String roll, String date, String status) {
        this.name = name;
        this.roll = roll;
        this.date = date;
        this.status = status;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRoll() { return roll; }
    public void setRoll(String roll) { this.roll = roll; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
