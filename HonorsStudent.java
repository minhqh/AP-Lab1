public class HonorsStudent extends Student
{
public HonorsStudent(String id,String fullName, String cohort, String major,int disciplineScore,Transcript transcript){
    super(id, fullName, cohort, major, disciplineScore, transcript);
}

@Override
public double tuitionDiscountRate(){
    if(this.getGpa4() >= 3.2 && this.getDisciplineScore() >= 80){
        return 0.1;
    }

    return 0.0;
}

public boolean maintainsHonorsStatus(){
    if(this.getGpa4() >= 3.2 &&
       this.getDisciplineScore() >= 75 && 
       this.failedCount() == 0) {return true;}

    return false;
}
}
