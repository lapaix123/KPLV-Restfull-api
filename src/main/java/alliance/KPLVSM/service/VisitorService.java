package alliance.KPLVSM.service;

import alliance.KPLVSM.model.Visitor;

import java.util.List;

public interface VisitorService {
    List<Visitor> findAllVisitor();
    Visitor saveVisitor(Visitor Visitor);
   // List<Visitor>findAllAdult(Visitor category);
    List<Visitor> findAllChildVisitors();
    List<Visitor> findAllAdultVisitors();
    List<Visitor> findAllStudentVisitors();
    List<Visitor> findAllFemaleVisitors();
    List<Visitor> findAllMaleVisitors();
    List<Visitor> findVisitorForStudying();
    List<Visitor> findVisitorForBorrowing_Returning();
    List<Visitor> findVisitorForMeeting();
    List<Visitor> findVisitorForEvent();
    List<Visitor> findVisitorForReading();

    Visitor findVisitorById(String NID);
    Visitor updateVisitor(String NID, Visitor visitor);
    void deleteVisitor(String NID);

    String generateCheckoutCode();

    void checkoutVisitor(Visitor visitor);


}

