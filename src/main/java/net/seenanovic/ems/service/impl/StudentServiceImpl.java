package net.seenanovic.ems.service.impl;

import lombok.AllArgsConstructor;
import net.seenanovic.ems.dto.StudentDto;
import net.seenanovic.ems.entity.Student;
import net.seenanovic.ems.exception.ResourceNotFoundExcep;
import net.seenanovic.ems.mapper.StudentMapper;
import net.seenanovic.ems.repository.StudentRepository;
import net.seenanovic.ems.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {

        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundExcep("No student found with this id: " + studentId));
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student) -> StudentMapper.mapToStudentDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudent) {

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundExcep("Student not found with this ID: "+ studentId)
        );

        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());

        Student updatedStudenObj = studentRepository.save(student);

        return StudentMapper.mapToStudentDto(updatedStudenObj);
    }

    @Override
    public void deleteStudent(Long studentId) {

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundExcep("Student not found with this ID: "+ studentId)
        );
        studentRepository.deleteById(studentId);
    }
}
