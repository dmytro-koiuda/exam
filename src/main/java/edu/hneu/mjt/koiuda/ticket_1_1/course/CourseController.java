package edu.hneu.mjt.koiuda.ticket_1_1.course;

import edu.hneu.mjt.koiuda.ticket_1_1.teacher.Teacher;
import edu.hneu.mjt.koiuda.ticket_1_1.teacher.TeacherDto;
import edu.hneu.mjt.koiuda.ticket_1_1.teacher.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAll();
    }


    @GetMapping("/courses/{id}")
    public Course getTeacherById(@PathVariable(value = "id") Long courseId) {
        return courseService.getById(courseId);
    }

    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public Course createTeacher(@RequestBody CourseDto dto) {
        return courseService.create(dto);
    }

    @PutMapping("/courses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTeacher(@PathVariable(value = "id") Long courseId, @RequestBody CourseDto dto) {
        courseService.update(courseId, dto);
    }

    @DeleteMapping("/courses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacher(@PathVariable(value = "id") Long courseId) {
        courseService.delete(courseId);
    }
}
