package edu.hneu.mjt.koiuda.ticket_1_1.teacher;

public record TeacherDto (
        String fullName,
        String specialization,
        String degree,
        String department,
        float experience,
        String phoneNumber ) {
}
