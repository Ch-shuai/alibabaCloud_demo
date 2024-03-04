package com.example.stockServer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/stock")
public class StockServerController {

    @Value("${server.port}")
    String port;

    @GetMapping("/reduct")
    public String reduct() {
        System.out.println("扣减库存");
        return "扣减库存！！！！！" + port;
    }

    public static void main(String[] args) {

        String aivdm = "!AIVDM,1,1,,B,13a;KP0010Mk`U9N>tT8aG?05GI,0*4C";
        String[] parts = aivdm.split(",", -1);
        int fragmentCount = Integer.parseInt(parts[1]);
        int fragmentNumber = Integer.parseInt(parts[2]);
        String payload = parts[5];
        byte[] decoded = Base64.getDecoder().decode(payload);
        int messageType = decoded[0] & 0xFF;
        int repeatIndicator = (decoded[4] >> 1) & 0x03;
        int mmsi = ((decoded[4] & 0x01) << 29) | ((decoded[5] & 0xFF) << 21) | ((decoded[6] & 0xFF) <<
                13) | ((decoded[7] & 0xFF) << 5) | ((decoded[8] & 0xF8) >> 3);
        int navigationStatus = (decoded[8] & 0x07);
        // Parse other fields here as needed
        System.out.println("Message Type: " + messageType);
        System.out.println("Repeat Indicator: " + repeatIndicator);
        System.out.println("MMSI: " + mmsi);
        System.out.println("Navigation Status: " + navigationStatus);
    }
}