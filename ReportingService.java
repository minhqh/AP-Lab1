import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportingService 
{
private StudentRepository repo;

public ReportingService(StudentRepository repo){
    if (repo == null) throw new IllegalArgumentException("Repo must not be null");
    this.repo = repo;
}

public Map<Classification, List<Student>> groupByClassification(ClassificationPolicy policy){
    Map<Classification, List<Student>> result = new HashMap<>();

    for(Student s : repo.findAll()){
        result.computeIfAbsent(policy.classify(s), k -> new ArrayList<>()).add(s);
    }

    return result;
}

public List<Student> topKByGpa(int k){
    List<Student> list = new ArrayList<>(repo.findAll());

    list.sort(new Comparator<Student>() {
        @Override
        public int compare(Student a, Student b) {
            if (a.getGpa4() != b.getGpa4()) {
                return Double.compare(b.getGpa4(), a.getGpa4());
            }

            if (a.getDisciplineScore() != b.getDisciplineScore()) {
                return Integer.compare(b.getDisciplineScore(), a.getDisciplineScore());
            }

            if (a.failedCount() != b.failedCount()) {
                return Integer.compare(a.failedCount(), b.failedCount());
            }

            return b.getEnglishName().compareTo(a.getEnglishName());
        }
    });

    return list.subList(0, Math.min(k, list.size()));
} 

public CohortStats statsByCohort(String cohort,ClassificationPolicy policy){
    int total = 0;
    int regularCount = 0;
    int honorsCount = 0;
    Map<Classification, Integer> counts = new HashMap<>();

    for (Student s :repo.findAll()) {
        if (!cohort.equals(s.getCohort())) continue;

        total++;
        counts.put(policy.classify(s), counts.getOrDefault(policy.classify(s), 0) + 1);

        if (s.getClass() == RegularStudent.class) {
            regularCount++;
        } else if (s.getClass() == HonorsStudent.class) {
            honorsCount++;
        }
    }

    return new CohortStats(cohort, total, counts, regularCount, honorsCount);
}

}
