import androidx.annotation.NonNull;

public class Student {
    private String Name;
    private int SClass;
    private int Class_num;
    public Student(String Name,int SClass,int Class_num){
        this.Name=Name;
        this.SClass=SClass;
        this.Class_num=Class_num;
    }
    public String getName(){
        return Name;
    }
    public int getSClass(){
        return  SClass;
    }
    public int getClass_num(){
        return Class_num;
    }
    public void setName(String Name){
        this.Name=Name;
    }

    public void setSClass(int SClass) {
        this.SClass = SClass;
    }

    public void setClass_num(int Class_num) {
        this.Class_num = Class_num;
    }
}
