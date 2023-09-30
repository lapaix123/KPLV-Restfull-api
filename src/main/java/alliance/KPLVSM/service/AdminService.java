package alliance.KPLVSM.service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminService {
    boolean authenticateAdmin(String username, String password);
    boolean isAdmin(String username);
}
