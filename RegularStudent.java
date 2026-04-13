public class RegularStudent extends Student
{
public RegularStudent(String id,String fullName, String cohort, String major,int disciplineScore,Transcript transcript){
    super(id, fullName, cohort, major, disciplineScore, transcript);
}

@Override
public double tuitionDiscountRate(){
    return 0.0;
}

public boolean eligibleForHonorsUpgrade(){
    if(this.getGpa4() >= 3.4 &&
       this.getDisciplineScore() >= 85 && 
       this.failedCount() == 0) {return true;}

    return false;
}

}
