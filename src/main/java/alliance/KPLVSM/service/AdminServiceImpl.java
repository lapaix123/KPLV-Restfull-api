package alliance.KPLVSM.service;

import org.springframework.stereotype.Service;

@Service

public class AdminServiceImpl implements AdminService{
   private static final String ADMIN_USERNAME="admin";
   private static final String ADMIN_PASSWORD="admin";
    @Override
    public boolean authenticateAdmin(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }

    @Override
    public boolean isAdmin(String username) {
        return ADMIN_USERNAME.equals(username);
    }
}
