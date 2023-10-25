package com.bsp.upgrade.esb.payload.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ESB_PAYLOAD_ERR")
public class EsbPaylodErr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYLOAD_ID")
    private String payloadId;

    @Column(name = "PAYLOAD")
    private String payload;

    public EsbPaylodErr(String payload) {
        this.payload = payload;
    }

    public String getPayloadId() {
        return payloadId;
    }

    public void setPayloadId(String payloadId) {
        this.payloadId = payloadId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "EsbPaylodErr{" +
                "payloadId='" + payloadId + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}
