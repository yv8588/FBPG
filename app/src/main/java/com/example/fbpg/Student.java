package com.example.fbpg;
public class Student {
    private String name,lastName;
    private String Sclass,classNumber;
    private Vaccine vac1,vac2;
    private boolean can;
    public Student(String name,String lastName,String Sclass,String classNumber,Vaccine vac1,Vaccine vac2,boolean can){
        this.name=name;
        this.lastName=lastName;
        this.Sclass=Sclass;
        this.classNumber=classNumber;
        this.vac1=vac1;
        this.vac2=vac2;
        this.can=can;
    }
    public Student(){}
    public String getClassNumber() {
        return classNumber;
    }

    public String getSclass() {
        return Sclass;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSclass(String sclass) {
        Sclass = sclass;
    }

    public Vaccine getVac1() {
        return vac1;
    }

    public Vaccine getVac2() {
        return vac2;
    }

    public void setVac1(Vaccine vac1) {
        this.vac1 = vac1;
    }

    public void setVac2(Vaccine vac2) {
        this.vac2 = vac2;
    }

    public boolean isCan() {
        return can;
    }

    public void setCan(boolean can) {
        this.can = can;
    }
}
