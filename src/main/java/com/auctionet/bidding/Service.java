package com.auctionet.bidding;


import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {
    private final Repository repository;
    public static final String API_KEY = "2ac80b5f5e236b8b64fb";

    //Since the simple version only contains one item...
    public Item getItem() {
        List<Item> all = repository.findAll();
        return all.get(0);
    }

    public Item placeBid(String bidderName, String currencyTag, double amount) {
        CurrencyConverter converter = new CurrencyConverter(
                new ConfigBuilder()
                        .openExchangeRatesApiKey(API_KEY)
                        .build()
        );

        Item item = getItem();
        double exchangeRate = converter.rate(currencyTag, item.getItemCurrencyTag());
        double bidValue = amount * exchangeRate;
        Bid newBid = new Bid()
                .setBidderName(bidderName)
                .setAmount(bidValue);

        item.getBidList().add(newBid);
        return repository.save(item);
    }
}
