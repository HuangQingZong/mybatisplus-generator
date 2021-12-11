package com.shanjupay.merchant.controller;


import org.springframework.stereotype.Controller;
import com.shanjupay.merchant.service.ICodeHolidayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hqz
 * @since 2021-12-10
 */
@Slf4j
@Controller
@Api(value = "", tags = "", description="")
public class CodeHolidayController {

    @Autowired
    private CodeHolidayService codeHolidayService;
}
