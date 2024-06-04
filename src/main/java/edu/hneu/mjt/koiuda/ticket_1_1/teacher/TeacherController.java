package edu.hneu.mjt.koiuda.ticket_1_1.teacher;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAll();
    }


    @GetMapping("/teachers/{id}")
    public Teacher getTeacherById(@PathVariable(value = "id") Long teacherId) {
        return teacherService.getById(teacherId);
    }

    @PostMapping("/teachers")
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher createTeacher(@RequestBody TeacherDto dto) {
        return teacherService.create(dto);
    }

    @PutMapping("/teachers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTeacher(@PathVariable(value = "id") Long teacherId, @RequestBody TeacherDto dto) {
        teacherService.updateTeacher(teacherId, dto);
    }

    @DeleteMapping("/teachers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacher(@PathVariable(value = "id") Long teacherId) {
        teacherService.deleteTeacher(teacherId);
    }
}
