package com.shanjupay.merchant.service.impl;

import com.shanjupay.merchant.entity.CodeHoliday;
import com.shanjupay.merchant.mapper.CodeHolidayMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanjupay.merchant.service.ICodeHolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hqz
 * @since 2021-12-10
 */
@Slf4j
@Service
@Transactional
public class CodeHolidayServiceImpl extends ServiceImpl<CodeHolidayMapper, CodeHoliday> implements ICodeHolidayService {

}
