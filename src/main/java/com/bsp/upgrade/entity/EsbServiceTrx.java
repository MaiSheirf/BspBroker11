package com.bsp.upgrade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ESB_SERVICE_TRX")
public class EsbServiceTrx {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private String trancactionId ;

    @Column(name = "GLOBAL_TRANSACTION_ID")
    private String globalTransactionId;
    @Column(name = "START_TIME")
    private String startTime ;

    public EsbServiceTrx(String globalTransactionId, String startTime) {
        this.globalTransactionId = globalTransactionId;
        this.startTime = startTime;
    }

    public String getTrancactionId() {
        return trancactionId;
    }

    public void setTrancactionId(String trancactionId) {
        this.trancactionId = trancactionId;
    }

    public String getGlobalTransactionId() {
        return globalTransactionId;
    }

    public void setGlobalTransactionId(String globalTransactionId) {
        this.globalTransactionId = globalTransactionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "EsbServiceTrx{" +
                "trancactionId='" + trancactionId + '\'' +
                ", globalTransactionId='" + globalTransactionId + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
