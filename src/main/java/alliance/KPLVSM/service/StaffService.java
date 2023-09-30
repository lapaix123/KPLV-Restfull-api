package alliance.KPLVSM.service;

import alliance.KPLVSM.model.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> findAllStaff();
    Staff saveStaff(Staff staff);

    List<Staff>findStaffByTitle(Staff staff);
    Staff findStaffById(String staffId);
    Staff updateStaff(String StaffId, Staff updateStaff );
    void deleteStaff(String staffId);
}
