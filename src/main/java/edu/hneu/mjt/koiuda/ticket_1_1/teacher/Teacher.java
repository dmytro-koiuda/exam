package edu.hneu.mjt.koiuda.ticket_1_1.teacher;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;

    private String specialization;

    private String degree;

    private String department;

    private float experience;

    private String phoneNumber;

    public Teacher(TeacherDto dto) {
        this.fullName = dto.fullName();
        this.specialization = dto.specialization();
        this.degree = dto.degree();
        this.department = dto.department();
        this.experience = dto.experience();
        this.phoneNumber = dto.phoneNumber();
    }
}
