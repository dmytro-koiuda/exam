package edu.hneu.mjt.koiuda.ticket_1_1.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.hneu.mjt.koiuda.ticket_1_1.teacher.Teacher;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Teacher teacher;

    private String title;

    private String description;

    private float duration;

    private String department;

    public Course (CourseDto dto, Teacher teacher) {
        this.title = dto.title();
        this.description = dto.description();
        this.duration = dto.duration();
        this.department = dto.department();
        this.teacher = teacher;
    }
}
