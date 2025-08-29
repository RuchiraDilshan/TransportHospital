package com.example.TransportHospital.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "user_detail")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String useremail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userrole = UserRole.ADMIN;

    @Column(nullable = false)
    private String userpassword;

    @Transient
    private String userconfirmpassword;

    @Column(nullable = false)
    private boolean isactive = true;

    private LocalDateTime createdat;

    private LocalDateTime lastlogin;

    public enum UserRole {
        SUPERADMIN,
        ADMIN
    }

    public UserDetail() {

    }

    public UserDetail(Long userid, String username, String useremail, String userpassword,
            UserRole userrole, Boolean isActive) {
        super();
        this.userid = userid;
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
        this.userrole = userrole;
        this.isactive = isActive;
        this.createdat = LocalDateTime.now();
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;

    }

    public String getUserconfirmpassword() {
        return userconfirmpassword;
    }

    public void setUserconfirmpassword(String userconfirmpassword) {
        this.userconfirmpassword = userconfirmpassword;
    }

    public UserRole getUserrole() {
        return userrole;
    }

    public void setUserrole(UserRole userrole) {
        this.userrole = userrole;
    }

    public Boolean getIsActive() {
        return isactive;
    }

    public void setIsActive(Boolean isactive) {
        this.isactive = isactive;
    }

    public LocalDateTime getCreatedAt() {
        return createdat;
    }

    public void setCreatedAt(LocalDateTime createdat) {
        this.createdat = createdat;
    }

    public LocalDateTime getLastLogin() {
        return lastlogin;
    }

    public void setLastLogin(LocalDateTime lastlogin) {
        this.lastlogin = lastlogin;
    }

}
