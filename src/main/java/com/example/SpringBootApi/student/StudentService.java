package com.example.SpringBootApi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //GET
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    //POST
    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email has already taken!");
        }
        studentRepository.save(student);
    }

    //DELETE
    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " doesn't exists!");
        }
        studentRepository.deleteById(studentId);
    }

    //PUT
    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("Student with id " + studentId + " does not exists!");

                });
        if (name != null && name.length() > 0  && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if (studentOptional.isPresent()){
                throw new IllegalStateException("Email has already taken!");
            }
            student.setEmail(email);
        }
    }

}
