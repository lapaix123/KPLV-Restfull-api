package alliance.KPLVSM.reports;

import alliance.KPLVSM.model.Staff;
import alliance.KPLVSM.model.Visitor;

import java.util.List;

public class AllReportData {
    private List<Staff> staffList;
    private List<Visitor> visitorList;
    private List<Visitor> ChildVisitor;
    private List<Visitor> adultVisitor;
    private List<Visitor> studentVisitor;
    private List<Visitor> femaleVisitor;
    private List<Visitor> maleVisitor;

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public List<Visitor> getVisitorList() {
        return visitorList;
    }

    public void setVisitorList(List<Visitor> visitorList) {
        this.visitorList = visitorList;
    }

    public List<Visitor> getChildVisitor() {
        return ChildVisitor;
    }

    public void setChildVisitor(List<Visitor> childVisitor) {
        ChildVisitor = childVisitor;
    }

    public List<Visitor> getAdultVisitor() {
        return adultVisitor;
    }

    public void setAdultVisitor(List<Visitor> adultVisitor) {
        this.adultVisitor = adultVisitor;
    }

    public List<Visitor> getStudentVisitor() {
        return studentVisitor;
    }

    public void setStudentVisitor(List<Visitor> studentVisitor) {
        this.studentVisitor = studentVisitor;
    }

    public List<Visitor> getFemaleVisitor() {
        return femaleVisitor;
    }

    public void setFemaleVisitor(List<Visitor> femaleVisitor) {
        this.femaleVisitor = femaleVisitor;
    }

    public List<Visitor> getMaleVisitor() {
        return maleVisitor;
    }

    public void setMaleVisitor(List<Visitor> maleVisitor) {
        this.maleVisitor = maleVisitor;
    }
}
