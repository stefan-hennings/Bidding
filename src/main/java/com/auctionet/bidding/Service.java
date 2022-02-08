package com.auctionet.bidding;


import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {
    private final Item item = new Item()
        .setName("Banana")
        .setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
                "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
                "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
        .setItemCurrencyTag("SEK");
    public static final String API_KEY = "ee60bdbca066448a9ddad5213a9bea44";

    public Item getItem() {
        return item;
    }

    public Item placeBid(String bidderName, String currencyTag, double amount) {
        CurrencyConverter converter = new CurrencyConverter(
                new ConfigBuilder()
                        .openExchangeRatesApiKey(API_KEY)
                        .build()
        );

        double exchangeRate = converter.rate(currencyTag, item.getItemCurrencyTag());
        double bidValue = (amount * exchangeRate);
        if (bidValue <= item.getHighestBid()) {
            throw new IllegalArgumentException("Bid too low!");
        }
        Bid newBid = new Bid()
                .setBidderName(bidderName)
                .setAmount(bidValue);

        item.getBidList().add(newBid);
        return item;
    }
}
