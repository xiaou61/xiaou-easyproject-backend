package org.dromara.dynamicqr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.dromara.dynamicqr.dto.DynamicQr;
import org.dromara.dynamicqr.service.DynamicQrService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uapi/admin/qrcode")
public class AdminQrController {

    private final DynamicQrService qrService;

    public AdminQrController(DynamicQrService qrService) {
        this.qrService = qrService;
    }

    @GetMapping
    public List<DynamicQr> list() {
        return qrService.list();
    }

    @PostMapping
    public boolean create(@RequestBody DynamicQr qr) {
        return qrService.save(qr);
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable String id, @RequestBody DynamicQr qr) {
        qr.setId(id);
        return qrService.updateById(qr);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return qrService.removeById(id);
    }
}
