//package com.capstone.accountteam.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Message {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Long mentorId;
//    private Long employeeId;
//    private String content;
//    private LocalDateTime sentDate;
//    @ManyToOne
//    @JoinColumn(name = "mentor_id", nullable = false)
//    private Mentor mentor;
//}
