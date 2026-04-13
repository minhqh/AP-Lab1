import java.util.List;
import java.util.Map;

public class MainTest {

    public static void main(String[] args) {
        System.out.println("====== BAT DAU KIEM THU LAB 1 ======\n");

        testExercise1();
        testExercise2();
        testExercise3();
        testExercise4();

        System.out.println("====== HOAN THANH KIEM THU ======");
    }

    private static void testExercise1() {
        System.out.println("--- Test Exercise 1: Transcript Management ---");
        try {
            try {
                new CourseResult("", 3, 8.0);
                System.out.println("[X] LOI: Khong bat duoc ngoai le ID rong!");
            } catch (IllegalArgumentException e) {
                System.out.println("[V] Bat ngoai le ID rong thanh cong.");
            }

            Transcript transcript = new Transcript();
            System.out.println("[V] GPA khi chua co mon hoc: " + transcript.gpa4() + " (Ky vong: 0.0)");

            transcript.addCourseResult(new CourseResult("CO2039", 3, 8.5));
            transcript.addCourseResult(new CourseResult("CO2040", 4, 7.0));
            transcript.addCourseResult(new CourseResult("CO2041", 2, 4.5));
            
            System.out.println("[V] GPA thuc te: " + transcript.gpa4() + " (Ky vong: 2.9)");
            System.out.println("[V] So mon rot: " + transcript.failedCount() + " (Ky vong: 1)");

            try {
                transcript.getResults().add(new CourseResult("HACK", 1, 10.0));
                System.out.println("[X] LOI: Encapsulation bi thung, List bi modify!");
            } catch (UnsupportedOperationException e) {
                System.out.println("[V] Tinh dong goi tot: Khong the hack them mon hoc.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private static void testExercise2() {
        System.out.println("--- Test Exercise 2: Student Classification ---");
        Transcript t1 = new Transcript();
        t1.addCourseResult(new CourseResult("MATH", 3, 9.0));

        RegularStudent rs = new RegularStudent("123", "  Nguyen Van Anh  ", "K22", "CS", 90, t1);
        HonorsStudent hs = new HonorsStudent("456", "Tran Thi Be", "K22", "CS", 85, t1);

        System.out.println("[V] Ten tieng Anh (Regular): " + rs.getEnglishName() + " (Ky vong: Van-Anh Nguyen)");
        System.out.println("[V] Ten tieng Anh (Honors): " + hs.getEnglishName() + " (Ky vong: Thi-Be Tran)");
        System.out.println("[V] ID da trim: '" + rs.getId() + "' (Ky vong: '123')");
        
        System.out.println("[V] RegularStudent discount: " + rs.tuitionDiscountRate() + " (Ky vong: 0.0)");
        System.out.println("[V] HonorsStudent discount: " + hs.tuitionDiscountRate() + " (Ky vong: 0.1)");
        
        System.out.println("[V] Regular duoc nang cap Honors? " + rs.eligibleForHonorsUpgrade() + " (Ky vong: true)");
        System.out.println();
    }

    private static void testExercise3() {
        System.out.println("--- Test Exercise 3: Student Policies ---");
        Transcript t = new Transcript();
        t.addCourseResult(new CourseResult("MATH", 3, 7.5));
        RegularStudent s = new RegularStudent("1", "Nguyen A", "K22", "CS", 80, t);

        ClassificationPolicy stdPolicy = new StandardPolicy();
        ClassificationPolicy strictPolicy = new StrictPolicy();

        System.out.println("[V] Phan loai Standard (GPA 3.0): " + stdPolicy.classify(s) + " (Ky vong: FAIR)");
        System.out.println("[V] Phan loai Strict (GPA 3.0): " + strictPolicy.classify(s) + " (Ky vong: FAIR)");
        System.out.println();
    }

    private static void testExercise4() {
        System.out.println("--- Test Exercise 4: Reporting and Sorting ---");
        StudentRepository repo = new StudentRepository();
        
        Transcript t1 = new Transcript(); t1.addCourseResult(new CourseResult("C1", 3, 9.0));
        Transcript t2 = new Transcript(); t2.addCourseResult(new CourseResult("C2", 3, 9.0));
        Transcript t3 = new Transcript(); t3.addCourseResult(new CourseResult("C3", 3, 8.0));
        
        RegularStudent s1 = new RegularStudent("1", "Le B", "K22", "CS", 80, t1);
        HonorsStudent s2 = new HonorsStudent("2", "Le A", "K22", "CS", 90, t2);
        RegularStudent s3 = new RegularStudent("3", "Le C", "K23", "CS", 90, t3);

        repo.add(s1);
        repo.add(s2);
        repo.add(s3);

        ReportingService service = new ReportingService(repo);

        List<Student> top = service.topKByGpa(2);
        System.out.println("[V] Top 1: " + top.get(0).getId() + " (Ky vong: 2)");
        System.out.println("[V] Top 2: " + top.get(1).getId() + " (Ky vong: 1)");

        CohortStats stats = service.statsByCohort("K22", new StandardPolicy());
        System.out.println("[V] Thong ke K22 - Tong so SV: " + stats.getTotal() + " (Ky vong: 2)");
        System.out.println("[V] Thong ke K22 - So SV Regular: " + stats.getRegularCount() + " (Ky vong: 1)");
        System.out.println("[V] Thong ke K22 - So SV Honors: " + stats.getHonorsCount() + " (Ky vong: 1)");
        System.out.println();
    }
}