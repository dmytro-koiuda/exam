package edu.hneu.mjt.koiuda.ticket_1_1.teacher;

import edu.hneu.mjt.koiuda.ticket_1_1.course.Course;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if(!validate(dto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return teacherRepository.save(new Teacher(dto));
    }

    public void updateTeacher(Long teacherId, TeacherDto dto) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(!validate(dto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

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

    public CoursesDurationDto getCoursesDuration(Long teacherId) {
        Teacher teacher = getById(teacherId);
        List<Course> courses = teacher.getCourses();
        var totalDuration = courses.stream()
                .mapToDouble(Course::getDuration)
                .sum();
        return new CoursesDurationDto(totalDuration);
    }

    private boolean validate(TeacherDto dto) {
        Pattern pattern = Pattern.compile("\\+380\\d{9}");
        Matcher matcher = pattern.matcher(dto.phoneNumber());
        return matcher.matches();
    }
}
