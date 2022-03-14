import java.time.LocalDate;

public class EmployeeInfo {

    private String  empId;
    private String projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public EmployeeInfo(String empId, String projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.empId = empId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getEmpId() {
        return empId;
    }

    public String getProjectId() {
        return projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
