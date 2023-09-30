package alliance.KPLVSM.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name="staff")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Staff {
   @Id
   @Column
   private String staffId;
   @Column
    private String staffName;
   @Column
    private String Title;
}
