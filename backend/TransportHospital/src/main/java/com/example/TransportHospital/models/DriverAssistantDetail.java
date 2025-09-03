package com.example.TransportHospital.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "driver_assistant_detail")
public class DriverAssistantDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverassistantid;

    private String driverassistantname;

    @Column(length = 10, nullable = false)
    private String driverassistantcontactnumber;

    public DriverAssistantDetail() {
        super();
    }

    public DriverAssistantDetail(Long driverassistantid, String driverassistantname,
            String driverassistantcontactnumber) {
        super();
        this.driverassistantid = driverassistantid;
        this.driverassistantname = driverassistantname;
        this.driverassistantcontactnumber = driverassistantcontactnumber;
    }

    public Long getDriverassistantid() {
        return driverassistantid;
    }

    public void setDriverassistantid(Long driverassistantid) {
        this.driverassistantid = driverassistantid;
    }

    public String getDriverassistantname() {
        return driverassistantname;
    }

    public void setDriverassistantname(String driverassistantname) {
        this.driverassistantname = driverassistantname;
    }

    public String getDriverassistantcontactnumber() {
        return driverassistantcontactnumber;
    }

    public void setDriverassistantcontactnumber(String driverassistantcontactnumber) {
        this.driverassistantcontactnumber = driverassistantcontactnumber;
    }

}