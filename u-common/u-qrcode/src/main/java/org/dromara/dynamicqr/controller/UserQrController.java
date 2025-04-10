package org.dromara.dynamicqr.controller;


import org.dromara.dynamicqr.dto.DynamicQr;
import org.dromara.dynamicqr.service.DynamicQrService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uapi/user/qrcode")
public class UserQrController {

    private final DynamicQrService qrService;

    public UserQrController(DynamicQrService qrService) {
        this.qrService = qrService;
    }

    @GetMapping("/{id}")
    public DynamicQr getQrCode(@PathVariable String id) {
        return qrService.getById(id);
    }
}
