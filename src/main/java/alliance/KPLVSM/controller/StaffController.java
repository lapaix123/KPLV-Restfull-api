package alliance.KPLVSM.controller;

import alliance.KPLVSM.exceptions.NotFoundException;
import alliance.KPLVSM.model.Staff;
import alliance.KPLVSM.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {
    private final StaffService staffService;

   @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }
    @GetMapping
    public List<Staff> getAllStaffs(){

       return staffService.findAllStaff();
    }

    @GetMapping("/{staffId}")
    public Staff getStaffById(@PathVariable String staffId){

       return staffService.findStaffById(staffId);
    }
    @PostMapping
    public Staff createStaff(@RequestBody Staff staff){
       return staffService.saveStaff(staff);
    }
    @PutMapping("/{staffId}")
    public Staff updateStaff(@PathVariable String staffId, @RequestBody Staff updateStaff){
       return staffService.updateStaff(staffId,updateStaff);
    }
    @DeleteMapping("/{staffId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String staffId){
       staffService.deleteStaff(staffId);
       return ResponseEntity.noContent().build();
    }

}
