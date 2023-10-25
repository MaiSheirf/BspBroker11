package com.bsp.upgrade.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ESB_SERVICE_EVT")
public class EsbServiceEvt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name= "EVENT_TIME")
    private String eventTIme;

    @Column(name = "EVENT_NAME")
    private String eventName;

    @Column(name = "EVENT_KEY1")
    private String eventKey1;

    @Column(name = "EVENT_KEY2")
    private String eventKey2;

    @Column(name = "EVENT_KEY3")
    private String eventKey3;

    @Column(name = "EVENT_KEY4")
    private String eventKey4;

    @Column(name = "EVENT_KEY5")
    private String eventKey5;

    @Column(name = "EVENT_STATUS")
    private String eventStatus;

    @Column(name = "PAYLOAD_ID")
    private String payloadId;

    @Column(name = "SERVICE_ID")
    private String serviceId;

    public EsbServiceEvt(String eventTIme, String eventName, String eventKey1, String eventKey2,
                         String eventKey3, String eventKey4, String eventKey5, String eventStatus,
                         String payloadId , String serviceId) {
        this.eventTIme = eventTIme;
        this.eventName = eventName;
        this.eventKey1 = eventKey1;
        this.eventKey2 = eventKey2;
        this.eventKey3 = eventKey3;
        this.eventKey4 = eventKey4;
        this.eventKey5 = eventKey5;
        this.eventStatus = eventStatus;
        this.payloadId = payloadId;
        this.serviceId = serviceId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getEventTIme() {
        return eventTIme;
    }

    public void setEventTIme(String eventTIme) {
        this.eventTIme = eventTIme;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventKey1() {
        return eventKey1;
    }

    public void setEventKey1(String eventKey1) {
        this.eventKey1 = eventKey1;
    }

    public String getEventKey2() {
        return eventKey2;
    }

    public void setEventKey2(String eventKey2) {
        this.eventKey2 = eventKey2;
    }

    public String getEventKey3() {
        return eventKey3;
    }

    public void setEventKey3(String eventKey3) {
        this.eventKey3 = eventKey3;
    }

    public String getEventKey4() {
        return eventKey4;
    }

    public void setEventKey4(String eventKey4) {
        this.eventKey4 = eventKey4;
    }

    public String getEventKey5() {
        return eventKey5;
    }

    public void setEventKey5(String eventKey5) {
        this.eventKey5 = eventKey5;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getPayloadId() {
        return payloadId;
    }

    public void setPayloadId(String payloadId) {
        this.payloadId = payloadId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "EsbServiceEvt{" +
                "transactionId='" + transactionId + '\'' +
                ", eventTIme='" + eventTIme + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventKey1='" + eventKey1 + '\'' +
                ", eventKey2='" + eventKey2 + '\'' +
                ", eventKey3='" + eventKey3 + '\'' +
                ", eventKey4='" + eventKey4 + '\'' +
                ", eventKey5='" + eventKey5 + '\'' +
                ", eventStatus='" + eventStatus + '\'' +
                ", payloadId='" + payloadId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                '}';
    }
}
