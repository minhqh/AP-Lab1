import java.util.Map;

public final class CohortStats
{
private final String cohort;
private final int total;
private final Map<Classification,Integer> counts;
private final int regularCount;
private final int honorsCount;

public  CohortStats(String cohort, int total, Map<Classification,Integer> counts,int regularCount, int honorsCount){
    if (cohort == null || cohort.isEmpty()) {
        throw new IllegalArgumentException("cohort must not be null or empty");
    }
    if (total < 0) {
        throw new IllegalArgumentException("total must be >= 0");
    }
    if (counts == null) {
        throw new IllegalArgumentException("counts must not be null");
    }
    if (regularCount < 0 || honorsCount < 0) {
        throw new IllegalArgumentException("counts must be >= 0");
    }
    
    this.cohort = cohort;
    this.total = total;
    this.counts = Map.copyOf(counts);
    this.regularCount = regularCount;
    this.honorsCount = honorsCount;    
}

public String getCohort(){return this.cohort;}
public int getTotal(){return this.total;}
public  Map<Classification,Integer> getCounts(){return this.counts;}
public int getRegularCount() {return this.regularCount;}
public int getHonorsCount() {return this.honorsCount;}

}
