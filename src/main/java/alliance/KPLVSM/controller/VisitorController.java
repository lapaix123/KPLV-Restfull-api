package alliance.KPLVSM.controller;

import alliance.KPLVSM.model.Visitor;
import alliance.KPLVSM.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    private final  VisitorService  visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {

        this.visitorService= visitorService;
    }

  @PostMapping("/saveVisitors")
    public ResponseEntity<String> postVisitor(@RequestBody Visitor visitor){
        try {
            Visitor savedVisitor = visitorService.saveVisitor(visitor);
            return ResponseEntity.ok("visitor saved");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/checkout")
    public ResponseEntity<String> checkoutVisitor(@RequestParam Long visitorId){
        Visitor visitor = visitorService.findVisitorById(String.valueOf(visitorId));
        if(visitor != null) {
            visitorService.checkoutVisitor(visitor);
           return ResponseEntity.ok("Visitor checked out successfully ! ");
           // return "checkOut";
        } else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/allVisitors")
    public List<Visitor> getAllVisitors(){
        return visitorService.findAllVisitor();
    }

  /* @GetMapping(value = "/allVisitors")
   public String getAllVisitors(Model model){

        List<Visitor> visitorList = visitorService.findAllVisitor();
        model.addAttribute("visitorList", visitorList);
        return "visitors/allVisitors";
    }*/

    @GetMapping("/childVisitors")
    public List<Visitor> getAllChildVisitors(){
        return visitorService.findAllChildVisitors();
    }
    @GetMapping("/adultVisitors")
    public List<Visitor> getAllAdultVisitors(){
        return visitorService.findAllAdultVisitors();
    }
    @GetMapping("/studentVisitors")
    public List<Visitor> getAllStudentVisitors(){
        return visitorService.findAllStudentVisitors();
    }


    /*@GetMapping("/{NID}")
    public Visitor getVisitorById(@PathVariable String NID){
        return visitorService.findVisitorById(NID);
    } */

    @GetMapping("/{NID}")
    public String showVisitorById(@PathVariable String NID, Model model ){
        Visitor visitor = visitorService.findVisitorById(NID);
        model.addAttribute("visitor", visitor);
        return "visitorDetails";
    }

    @GetMapping("/femaleVisitors")
    public ResponseEntity<List<Visitor>> getAllFemaleVisitors(){
        List<Visitor> femaleVisitor = visitorService.findAllFemaleVisitors();
        return ResponseEntity.ok(femaleVisitor);
    }
    @GetMapping("maleVisitors")
    public ResponseEntity<List<Visitor>> getallMaleVisitors(){
        List<Visitor> maleVisitor = visitorService.findAllMaleVisitors();
        return ResponseEntity.ok(maleVisitor);
    }

    @GetMapping("/reason/studying")
    public ResponseEntity<List<Visitor>> getVisitorForStudying(){
        List<Visitor> studyingVisitor = visitorService.findVisitorForStudying();
        return  ResponseEntity.ok(studyingVisitor);
    }
    @GetMapping("/reason/reading")
    public ResponseEntity<List<Visitor>> getVisitorForReading(){
        List<Visitor> readingVisitor = visitorService.findVisitorForReading();
        return  ResponseEntity.ok(readingVisitor);
    }
    @GetMapping("/reason/borrowing_returning")
    public ResponseEntity<List<Visitor>> getVisitorForBorrowingReturning(){
        List<Visitor> borrowingReturningVisitor = visitorService.findVisitorForBorrowing_Returning();
        return  ResponseEntity.ok(borrowingReturningVisitor);
    }
    @GetMapping("/reason/meeting")
    public ResponseEntity<List<Visitor>> getVisitorForMeeting(){
        List<Visitor> meetingVisitor = visitorService.findVisitorForMeeting();
        return  ResponseEntity.ok(meetingVisitor);
    }
    @GetMapping("/reason/event")
    public ResponseEntity<List<Visitor>> getVisitorForEvent(){
        List<Visitor> eventVisitor = visitorService.findVisitorForEvent();
        return  ResponseEntity.ok(eventVisitor);
    }
    @PutMapping("/{NID}")
    public Visitor updateVisitor(@PathVariable String NID, @RequestBody Visitor updatevisitor){
        return visitorService.updateVisitor(NID,updatevisitor);
    }

    @DeleteMapping("/{NID}")
    public ResponseEntity<String> deleteVisitor(@PathVariable String NID){

        visitorService.deleteVisitor(NID);
        return ResponseEntity.ok("Visitor deleted with id :" + NID);
    }



}
