package com.repaso_prueba.entities;

public class Enrollment {

    private Long id;
    private Long student_id;
    private Long course_id;
    private int grade;
    private String created_at;
    private String updated_at;
    private Student student;
    private Course course;

    public Enrollment() {
    }

    public Enrollment(Long student_id, Long course_id, int grade) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Enrollment{" + "id=" + id + ", student_id=" + student_id + ", course_id=" + course_id + ", grade=" + grade + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }

}
