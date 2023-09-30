package alliance.KPLVSM.dao;

import alliance.KPLVSM.model.Gender;
import alliance.KPLVSM.model.Reason;
import alliance.KPLVSM.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor,String>{
    List<Visitor> findByGender(Gender gender);
    List<Visitor> findByReason(Reason reason);
}
