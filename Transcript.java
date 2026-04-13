import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Transcript 
{
private List<CourseResult> results;

public Transcript(){
    this.results = new ArrayList<>();
};
public void addCourseResult(CourseResult r){
    if(r != null){
        this.results.add(r);
    }
}

public double gpa4(){
    if(results == null || results.isEmpty()) return 0.0;
    double sum_score = 0;
    int total_credits = 0;
    for(CourseResult course : results){
        double gpa4_courese = 0.0;
        if(course.getScore() >= 8.5) gpa4_courese = 4.0;
        else if (course.getScore() >= 7.0) gpa4_courese = 3.0;
        else if (course.getScore() >= 5.5) gpa4_courese = 2.0;
        else if (course.getScore() >= 4.0) gpa4_courese = 1.0;
        else gpa4_courese = 0.0;
        sum_score += gpa4_courese*course.getCredits();
        total_credits += course.getCredits();
    }
    return Math.round((sum_score/total_credits)*10.0)/10.0;
}

public int failedCount(){
    if(results == null) return 0;
    int count = 0;
    for(CourseResult course : results){
        if(course.getScore() < 5.0){
            count ++;
        }
    }
    return count;
}

public List<CourseResult>  getResults(){
    return Collections.unmodifiableList(this.results);
}

}
