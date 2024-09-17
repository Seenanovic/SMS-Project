package net.seenanovic.ems.repository;

import net.seenanovic.ems.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

