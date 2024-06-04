package edu.hneu.mjt.koiuda.ticket_1_1.course;

import edu.hneu.mjt.koiuda.ticket_1_1.teacher.Teacher;
import edu.hneu.mjt.koiuda.ticket_1_1.teacher.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Course create(CourseDto dto) {
        Teacher teacher = teacherRepository.findById(dto.teacherId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher with id " + dto.teacherId() + " not found")
        );
        return courseRepository.save(new Course(dto, teacher));
    }

    public void update(Long courseId, CourseDto dto) {
        Course course = getById(courseId);

        Teacher teacher = teacherRepository.findById(dto.teacherId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher with id " + dto.teacherId() + " not found")
        );

        course.setDepartment(dto.department());
        course.setDuration(dto.duration());
        course.setTitle(dto.title());
        course.setTeacher(teacher);
        course.setDescription(dto.description());

        courseRepository.save(course);
    }

    public void delete(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        courseRepository.delete(course);
    }
}
