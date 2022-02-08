package com.auctionet.bidding;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
public class Item {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column(length = 2000)
    private String description;

    @Column
    private String itemCurrencyTag;

    @OneToMany
    private List<Bid> bidList = new ArrayList<>();

    public double getHighestBid() {
        if (bidList.size() == 0) {
            return 0;
        }
        return bidList.get(bidList.size() - 1).getAmount();
    }

}
