import java.util.ArrayList;
import java.util.List;

public class StudentRepository 
{
private List<Student> students = new ArrayList<>();

public void add(Student s){
    this.students.add(s);
}

public List<Student> findAll(){
    return List.copyOf(this.students);
}

}
