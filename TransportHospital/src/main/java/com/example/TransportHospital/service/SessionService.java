package com.example.TransportHospital.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import com.example.TransportHospital.models.UserDetail;

@Service
public class SessionService {

    private static final String USER_SESSION_KEY = "currentUser";

    public void loginUser(HttpSession session, UserDetail user) {
        session.setAttribute(USER_SESSION_KEY, user);
    }

    public UserDetail getCurrentUser(HttpSession session) {
        return (UserDetail) session.getAttribute(USER_SESSION_KEY);
    }

    public void logoutUser(HttpSession session) {
        session.removeAttribute(USER_SESSION_KEY);
        session.invalidate();
    }

    public boolean isUserLoggedIn(HttpSession session) {
        return getCurrentUser(session) != null;
    }

    public boolean isSuperAdmin(HttpSession session) {
        UserDetail user = getCurrentUser(session);
        return user != null && user.getUserrole() == UserDetail.UserRole.SUPERADMIN;
    }
}