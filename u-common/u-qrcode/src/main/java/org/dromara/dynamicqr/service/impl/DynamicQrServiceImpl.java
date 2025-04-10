package org.dromara.dynamicqr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.dromara.dynamicqr.dto.DynamicQr;
import org.dromara.dynamicqr.mapper.DynamicQrMapper;
import org.dromara.dynamicqr.service.DynamicQrService;
import org.springframework.stereotype.Service;

@Service
public class DynamicQrServiceImpl extends ServiceImpl<DynamicQrMapper, DynamicQr> implements DynamicQrService {
}
