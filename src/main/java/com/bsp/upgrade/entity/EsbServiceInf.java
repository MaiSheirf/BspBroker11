package com.bsp.upgrade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ESB_SERVICE_INF")
public class EsbServiceInf {


    public EsbServiceInf(String serviceName) {
        this.serviceName = serviceName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERVICE_ID")
    private String serviceID ;
    @Column(name = "SERVICE_NAME")
    private  String serviceName;

    @Column(name ="ISREADABLE")
    private int isReadable ;

    public int getIsReadable() {
        return isReadable;
    }

    public void setIsReadable(int isReadable) {
        this.isReadable = isReadable;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "EsbServiceInf{" +
                "serviceID='" + serviceID + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
