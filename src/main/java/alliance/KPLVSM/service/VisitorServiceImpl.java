package alliance.KPLVSM.service;

import alliance.KPLVSM.dao.VisitorRepository;
import alliance.KPLVSM.exceptions.InvalidDataException;
import alliance.KPLVSM.exceptions.NotFoundException;
import alliance.KPLVSM.model.Category;
import alliance.KPLVSM.model.Gender;
import alliance.KPLVSM.model.Reason;
import alliance.KPLVSM.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class VisitorServiceImpl implements VisitorService{
    Visitor visitor;
    private final VisitorRepository visitorRepository;
        @Autowired
        public VisitorServiceImpl(VisitorRepository visitorRepository){
            this.visitorRepository= visitorRepository;
        }

    @Override
    public List<Visitor> findAllVisitor() {

           return visitorRepository.findAll();
    }

    @Override
    public Visitor saveVisitor(Visitor visitor) {

           if(visitor.getNID()==null|| visitor.getNID().isEmpty()) {
               throw new InvalidDataException("NID Can not be empty");

           }
           setCategoryAutomatically(visitor);
           return visitorRepository.save(visitor);
    }

    @Override
    public List<Visitor> findAllChildVisitors() {
           List<Visitor> allVisitors= visitorRepository.findAll();
           List<Visitor> childVisitors= new ArrayList<>();

           LocalDate currentDate = LocalDate.now();

           for(Visitor visitor: allVisitors){
               LocalDate dob = visitor.getDateOfBirth();
               int age = Period.between(dob, currentDate).getYears();
               if (age<18){
                   childVisitors.add(visitor);
               }
           }
        System.out.println("Total Visitors:"+ allVisitors.size());
        System.out.println("Child Visitors:"+ childVisitors.size());

        return childVisitors;
    }

    @Override
    public List<Visitor> findAllAdultVisitors() {
        List<Visitor> allVisitors= visitorRepository.findAll();
        List<Visitor> adultVisitors= new ArrayList<>();

        LocalDate currentDate = LocalDate.now();

        for(Visitor visitor: allVisitors){
            LocalDate dob = visitor.getDateOfBirth();
            int age = Period.between(dob, currentDate).getYears();
            if (age>18){
                adultVisitors.add(visitor);
            }
        }
        System.out.println("Total Visitors:"+ allVisitors.size());
        System.out.println("Adult Visitors:"+ adultVisitors.size());

        return adultVisitors;
    }

    @Override
    public List<Visitor> findAllStudentVisitors() {
        List<Visitor> allVisitors= visitorRepository.findAll();
        List<Visitor> studentVisitors= new ArrayList<>();

        LocalDate currentDate = LocalDate.now();

        for(Visitor visitor: allVisitors){
            LocalDate dob = visitor.getDateOfBirth();
            int age = Period.between(dob, currentDate).getYears();
            if (age<18 && age>12){
                studentVisitors.add(visitor);
            }
        }
        System.out.println("Total Visitors:"+ allVisitors.size());
        System.out.println("Student Visitors:"+ studentVisitors.size());

        return studentVisitors;

    }

    @Override
    public List<Visitor> findAllFemaleVisitors() {

        return visitorRepository.findByGender(Gender.FEMALE);
    }

    @Override
    public List<Visitor> findAllMaleVisitors() {
        return visitorRepository.findByGender(Gender.MALE);
    }

    @Override
    public List<Visitor> findVisitorForStudying() {
        return visitorRepository.findByReason(Reason.STUDYING);
    }

    @Override
    public List<Visitor> findVisitorForBorrowing_Returning() {
        return visitorRepository.findByReason(Reason.BORROWING_RETURNING_BOOK);
    }

    @Override
    public List<Visitor> findVisitorForMeeting() {
        return visitorRepository.findByReason(Reason.MEETING);
    }

    @Override
    public List<Visitor> findVisitorForEvent() {
        return visitorRepository.findByReason(Reason.EVENT);
    }

    @Override
    public List<Visitor> findVisitorForReading() {
        return visitorRepository.findByReason(Reason.READING);
    }


    @Override
    public Visitor findVisitorById(String NID) {
        return visitorRepository.findById(NID)
                .orElseThrow(()->new NotFoundException("visitor not found with NID:" + NID));
    }

    @Override
    public Visitor updateVisitor(String NID, Visitor updatevisitor) {

        Visitor existingVisitor = visitorRepository.findById(NID)
                .orElseThrow(()-> new NotFoundException("visitor not found with NID:" + NID));

            existingVisitor.setNID(updatevisitor.getNID());
            existingVisitor.setDateOfBirth(updatevisitor.getDateOfBirth());
            existingVisitor.setGender(updatevisitor.getGender());
            existingVisitor.setEmail(updatevisitor.getEmail());
            existingVisitor.setTel(updatevisitor.getTel());
            existingVisitor.setFirstName(updatevisitor.getFirstName());
          //  existingVisitor.setUserName(updatevisitor.getUserName());
            existingVisitor.setLastName(updatevisitor.getLastName());
            existingVisitor.setReason(updatevisitor.getReason());

        return visitorRepository.save(existingVisitor);
    }

    @Override
    public void deleteVisitor(String NID) {
        Visitor visitor = visitorRepository.findById(NID)
                        .orElseThrow(()-> new NotFoundException("Visitor not found with NID: "+ NID));


        visitorRepository.delete(visitor);//.ifPresent(visitor -> visitorRepository.delete(visitor));

    }

    @Override
    public String generateCheckoutCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return visitor.getFirstName().substring(0, Math.min(visitor.getFirstName().length(), 3)) + String.valueOf(code);
    }

    @Override
    public void checkoutVisitor(Visitor visitor) {
        if (visitor.getCheckoutTime()==null) {
            visitor.setCheckoutCode(generateCheckoutCode());
            visitor.setCheckoutTime(LocalDateTime.now());
            visitorRepository.save(visitor);
        } else {
            throw new RuntimeException("Visitor has already checked out.");
        }
    }




    private void setCategoryAutomatically(Visitor visitor){
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(visitor.getDateOfBirth(), currentDate).getYears();
        if(age<12){
            visitor.setCategory(Category.CHILD);
        }else if(age >=12 && age <18){
            visitor.setCategory(Category.STUDENT);
        }else{
            visitor.setCategory(Category.ADULT);
        }
    }
}
