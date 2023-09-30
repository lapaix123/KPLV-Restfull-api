package alliance.KPLVSM.service;

import alliance.KPLVSM.dao.StaffRepository;
import alliance.KPLVSM.exceptions.InvalidDataException;
import alliance.KPLVSM.exceptions.NotFoundException;
import alliance.KPLVSM.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alliance.KPLVSM.exceptionHandler.GlobalExceptionHandler;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService{
    private final StaffRepository staffRepository;
    

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {

        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> findAllStaff() {

        return staffRepository.findAll();
    }

    @Override
    public Staff saveStaff(Staff staff) {
        if(staff.getStaffId()==null || staff.getStaffId().isEmpty()){
            throw new InvalidDataException("Id can not be empty");
        }
        return staffRepository.save(staff);
    }


    @Override
    public List<Staff> findStaffByTitle(Staff staff) {

        return null;
    }

    @Override
    public Staff findStaffById(String staffId) {
        return staffRepository.findById(staffId)
                .orElseThrow(()-> new NotFoundException("user not found with id :" +staffId ));
    }

    @Override
    public Staff updateStaff(String staffId, Staff updateStaff) {
       Staff existingStaff = staffRepository.findById(staffId)
               .orElseThrow(() -> new NotFoundException("User not found with id:"+ staffId));

       existingStaff.setStaffId(updateStaff.getStaffId());
       existingStaff.setStaffName(updateStaff.getStaffName());
       existingStaff.setTitle(updateStaff.getTitle());

        return staffRepository.save(existingStaff);


    }


    @Override
    public void deleteStaff(String staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new NotFoundException("User not found with id: "+ staffId));
        staffRepository.delete(staff);

    }
}
