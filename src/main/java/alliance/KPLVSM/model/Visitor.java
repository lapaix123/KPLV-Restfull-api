package alliance.KPLVSM.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Table
public class Visitor {
    @Id
    @Column
    private String NID;
   /* @Column
    private String userName; */
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private LocalDate dateOfBirth;
    @Column
    private String Tel;
    @Column
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column
    @Enumerated(EnumType.STRING)
    private Reason reason;

    @Column
    private LocalDateTime enteredTime;

    private LocalDateTime checkoutTime;

    private String checkoutCode;

    public Visitor(){
        this.enteredTime= LocalDateTime.now();
    }


    public void addAtribute(String allVisitors, List<Visitor> allVisitors1) {
    }
}