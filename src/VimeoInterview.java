import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * AttendanceRegister
 * List<Student>
 * HashMap<Date,List<StudentStatus>>       YMD   1662709306
 * getAttendance(Date,Status)
 * getAttendance(Date,Date,Student)
 * addAttendance(Date,Student,Status)
 * <p>
 * <p>
 * Student
 * Name
 * RollNo
 * <p>
 * <p>
 * StudentStatus
 * Student
 * Status
 * <p>
 * <p>
 * <p>
 * Enum Status
 * PRESENT
 * ABSENT
 **/


class AttendanceRegister {
    /**
     * will contain only the Students which are present
     **/
    HashMap<Date, Set<Student>> attendanceMap;

    public AttendanceRegister() {
        attendanceMap = new HashMap<>();
    }

    public void markAttendance(Date date, Student student) {
        Set<Student> presentStudents = attendanceMap.getOrDefault(date, new HashSet<>());
        presentStudents.add(student);
        attendanceMap.put(date, presentStudents);
    }

    public int getAttendance(Date fromDate, Date toDate, Student student) {
        int attendanceCount = 0;
        Set<Student> studentList;
        while (!fromDate.equals(toDate)) {
            studentList = attendanceMap.get(fromDate);

            if (studentList.contains(student)) {
                attendanceCount++;
            }

            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String newFromDate = incrementDateByOneDay(format1.format(fromDate));
            try {
//                System.out.println("New from Date "+newFromDate);
                fromDate = format1.parse(newFromDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        studentList = attendanceMap.get(fromDate);

        if (studentList.contains(student)) {
            attendanceCount++;
        }
        return attendanceCount;
    }

    public static String incrementDateByOneDay(String dt) {
//        String dt = "2008-01-01";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 1);  // number of days to add
        dt = sdf.format(c.getTime());
        return dt;
    }

}

class Student {
    int rollNo;
    String studentName;

    public Student(int rollNo, String studentName) {
        this.rollNo = rollNo;
        this.studentName = studentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return rollNo == student.rollNo && Objects.equals(studentName, student.studentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo, studentName);
    }
}

enum Status {
    PRESENT,
    ABSENT
}

public class VimeoInterview {


    public static void main(String[] args) {

        AttendanceRegister attendanceRegister = new AttendanceRegister();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1, date2, date3;

        try {
            date1 = format1.parse("2022-09-11");
            date2 = format1.parse("2022-09-12");
            date3 = format1.parse("2022-09-13");

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        List<Student> studentList = new ArrayList<>(Arrays.asList(
                new Student(1, "Pankaj"),
                new Student(2, "Varkha"),
                new Student(3, "Gaurav")
        ));

        attendanceRegister.markAttendance(date1, studentList.get(0));
//        attendanceRegister.markAttendance(date1, studentList.get(0));
        attendanceRegister.markAttendance(date1, studentList.get(2));

        attendanceRegister.markAttendance(date2, studentList.get(0));
        attendanceRegister.markAttendance(date2, studentList.get(1));


        attendanceRegister.markAttendance(date3, studentList.get(0));
        attendanceRegister.markAttendance(date3, studentList.get(1));
        attendanceRegister.markAttendance(date3, studentList.get(2));

        Student testStudent = studentList.get(0);
        int attendance = attendanceRegister.getAttendance(date1, date3, testStudent);
        System.out.println("Attendance of student " + testStudent.studentName + " is " + attendance);

    }
}
