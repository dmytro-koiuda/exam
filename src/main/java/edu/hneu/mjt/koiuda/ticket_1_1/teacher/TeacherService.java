package edu.hneu.mjt.koiuda.ticket_1_1.teacher;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public Teacher getById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Teacher create(TeacherDto dto) {
        return teacherRepository.save(new Teacher(dto));
    }

    public void updateTeacher(Long teacherId, TeacherDto dto) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        teacher.setFullName(dto.fullName());
        teacher.setSpecialization(dto.specialization());
        teacher.setDegree(dto.degree());
        teacher.setDepartment(dto.department());
        teacher.setExperience(dto.experience());
        teacher.setPhoneNumber(dto.phoneNumber());

        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        teacherRepository.delete(teacher);
    }
}
