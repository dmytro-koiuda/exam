package edu.hneu.mjt.koiuda.ticket_1_1.course;

public record CourseDto(
        String title,

        String description,

        float duration,

        String department,

        long teacherId
) {
}
