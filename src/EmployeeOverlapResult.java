public class EmployeeOverlapResult {

    private String id1;
    private String id2;
    private String project;
    private long overlappingDays = 0;

    public EmployeeOverlapResult(String id1, String id2, String project, long overlappingDays) {
        this.id1 = id1;
        this.id2 = id2;
        this.project = project;
        this.overlappingDays = overlappingDays;
    }

    public EmployeeOverlapResult() {

    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setOverlappingDays(long overlappingDays) {
        this.overlappingDays = overlappingDays;
    }

    @Override
    public String toString() {
        return "EmployeeOverlapResult{" +
                "id1='" + id1 + '\'' +
                ", id2='" + id2 + '\'' +
                ", project='" + project + '\'' +
                ", overlappingDays=" + overlappingDays +
                '}';
    }

    public long getOverlappingDays() {
        return overlappingDays;
    }
}
