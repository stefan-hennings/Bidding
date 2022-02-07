package com.auctionet.bidding;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/")
@RequiredArgsConstructor
public class Controller {
    private final Service service;

    @GetMapping("/item")
    public ResponseEntity<Item> getItem() {
        return ResponseEntity.ok(service.getItem());
    }

    @PostMapping("/bid")
    public ResponseEntity<Item> placeBid(@RequestParam String bidderName, @RequestParam String currency, @RequestParam double amount) {
        return ResponseEntity.ok(service.placeBid(bidderName, currency, amount));
    }
}
