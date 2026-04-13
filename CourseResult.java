public class CourseResult implements GradedItem
{
private  String courseId;
private  int credits;
private  double score;

public CourseResult(String courseId,int credits,double score){
    if(courseId == null || courseId.trim().isEmpty() ){
        throw new IllegalArgumentException("courseID must non-empty");
    }

    if(credits <= 0){
        throw new IllegalArgumentException("credits must be greater than 0");
    }

    if(score < 0 || score > 10){
        throw new IllegalArgumentException("score must be in the range [0.0, 10.0]");
    }
    this.courseId = courseId;
    this.credits = credits;
    this.score = score;
}

public String getCourseId(){
    return this.courseId;
}

public int getCredits(){
    return this.credits;
}

public double getScore(){
    return this.score;
}
};   
