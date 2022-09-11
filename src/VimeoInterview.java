import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  AttendanceRegister
 *   List<Student>
 *   HashMap<Date,List<StudentStatus>>       YMD   1662709306
 *   getAttendance(Date,Status)
 *   getAttendance(Date,Date,Student)
 *   addAttendance(Date,Student,Status)

 *
 *  Student
 *   Name
 *   RollNo
 *
 *
 *  StudentStatus
 *  Student
 *  Status
 *
 *
 *
 *  Enum Status
 *  PRESENT
 *  ABSENT
 *
 * **/


class AttendanceRegister{
    /** will contain only the Students which are present **/
    HashMap<Date,List<Student>> attendanceMap;

    public int getAttendance(Date fromDate, Date toDate, Student student)
    {
        int attendanceCount=0;
        List<Student> studentList;
        while(!fromDate.equals(toDate)){
            studentList = attendanceMap.get(fromDate);

            if(studentList.contains(student)){
                attendanceCount++;
            }

            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            fromDate  = incrementDateByOneDay(format1.format(fromDate));
        }
        return attendanceCount;
    }

    public Date incrementDateByOneDay(String dt){
//        String dt = "2008-01-01";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 1);  // number of days to add
        return new Date();
    }

}

class Student {
    int rollNo;
    String StudentName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return rollNo == student.rollNo && Objects.equals(StudentName, student.StudentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo, StudentName);
    }
}

enum Status{
    PRESENT,
    ABSENT
}

public class VimeoInterview {


    public static void main(String[] args) {

    }
}
