package com.auctionet.bidding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
public class Bid {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private double amount;

    @Column
    private String bidderName;
}
