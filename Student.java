public abstract class Student 
{
protected String id;
protected String fullName;
protected String cohort;
protected String major;
protected int disciplineScore;
protected Transcript transcript;

protected Student(String id,String fullName, String cohort, String major,int disciplineScore,Transcript transcript){
    if(disciplineScore < 0 || disciplineScore > 100){
        throw new IllegalArgumentException("disciplineScore in range  [0, 100]");
    }

    if(transcript == null){
        throw new NullPointerException("Transcipt must not null");
    }

    this.id = id.trim();
    this.fullName = fullName.trim();
    this.cohort = cohort.trim();
    this.major = major.trim();
    this.disciplineScore = disciplineScore;
    this.transcript = transcript;
};
    
public String getId(){ return this.id;}
public String getFullName(){ return this.fullName;}
public String getCohort(){ return this.cohort;}
public String getMajor(){ return this.major;}
public int    getDisciplineScore(){ return this.disciplineScore;}
protected Transcript getTranscript(){return this.transcript;}

public void setCohort(String cohort){this.cohort = cohort.trim();}
public void setMajor(String major){this.major = major.trim();}
public void setDisciplineScore(int disciplineScore){this.disciplineScore = disciplineScore;}

public String getEnglishName(){
    String[] parts = this.fullName.split("\\s+");
    String familyName = parts[0];

    StringBuilder givenAndMiddle = new StringBuilder();
    for (int i = 1; i < parts.length; i++) {
        givenAndMiddle.append(parts[i]);
        if (i < parts.length - 1) {
            givenAndMiddle.append("-");
        }
    }

    return givenAndMiddle + " " + familyName;
}

public abstract double tuitionDiscountRate();

public double getGpa4(){
    return this.transcript.gpa4();
}

public int failedCount(){
    return this.transcript.failedCount();
}


}
