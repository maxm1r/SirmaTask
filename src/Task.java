import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Task {

    public static void main(String[] args) {

        HashMap<String,List<EmployeeInfo>> map = parseInput();
        EmployeeOverlapResult maxOverlap = new EmployeeOverlapResult();
        for (String s : map.keySet()) {
            List<EmployeeInfo> value = map.get(s);

            if (value.size() >1){
                EmployeeOverlapResult result = getMaxWorkTogether(value);
                if (result.getOverlappingDays() > maxOverlap.getOverlappingDays()){
                    maxOverlap = result;
                }
            }
        }
       if (maxOverlap.getOverlappingDays() == 0){
           System.out.println("There are no employees that worked together on the same project in the input data");
       }
       else{
           System.out.println(maxOverlap);
       }

    }

    static EmployeeOverlapResult getMaxWorkTogether(List<EmployeeInfo> list) {
        EmployeeOverlapResult overlapResult = new EmployeeOverlapResult();
        for (int i = 0; i < list.size() - 1; i++) {
            EmployeeInfo employeeA = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                EmployeeInfo employeeB = list.get(j);

                if (!(employeeA.getEmpId().equals(employeeB.getEmpId()))) {

                    if (!(employeeA.getDateTo().isBefore(employeeB.getDateFrom()) || employeeB.getDateTo().isBefore(employeeA.getDateFrom()))) {
                        LocalDate laterStart = Collections.max(Arrays.asList(employeeA.getDateFrom(), employeeB.getDateFrom()));
                        LocalDate earlierEnd = Collections.min(Arrays.asList(employeeA.getDateTo(), employeeB.getDateTo()));
                        long numberOfOverlappingDays = ChronoUnit.DAYS.between(laterStart, earlierEnd) +1; // plus 1 because the days are inclusive

                        if (numberOfOverlappingDays > overlapResult.getOverlappingDays()) {
                            overlapResult.setOverlappingDays(numberOfOverlappingDays);
                            overlapResult.setId1(employeeA.getEmpId());
                            overlapResult.setId2(employeeB.getEmpId());
                            overlapResult.setProject(employeeA.getProjectId());
                        }
                    }
                }
            }
        }
        return overlapResult;
    }

    static LocalDate parseDate(String value){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate dateTime = LocalDate.parse(value, formatter);
        return dateTime;
    }

    static HashMap<String,List<EmployeeInfo>> parseInput(){
        String path = "taskFile.csv";
        String line = "";
        HashMap<String,List<EmployeeInfo>> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null){
                String[] values = line.split(",");

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
                dateFormat.setLenient(false);
                LocalDate dateFrom = parseDate(values[2]);
                LocalDate dateTo;
                if (values[3].equalsIgnoreCase("null")){
                    dateTo = LocalDate.now();
                }
                else{
                    dateTo=  parseDate(values[3]);
                }

                if (!(map.containsKey(values[1]))){
                    map.put(values[1],new ArrayList<>());
                }
                map.get(values[1]).add(new EmployeeInfo(values[0],values[1],dateFrom,dateTo));
                System.out.println(dateFrom);
                System.out.println(dateTo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        }
        catch (IOException e ){
            System.out.println("An IOException appeared!");
        }
        return map;
    }
}
