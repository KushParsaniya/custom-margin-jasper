package com.vasyerp.barcodewithmargin.controller;

import com.vasyerp.barcodewithmargin.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BarcodeController {

    private final BarcodeService barcodeService;

    @GetMapping("/generate")
    public String generateReport(@RequestParam(defaultValue = "0") int left, @RequestParam(defaultValue = "0") int top) throws Exception {
        return barcodeService.generateReport(left, top);
    }
}
