package com.zr.shirodemo.controller;

import com.zr.shirodemo.domain.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Description:
 *
 * @author zhangr
 * 2020/1/9 14:10
 */
@Api("video_update")
@RestController
public class PermitController {
    @ApiOperation(value = "/video/update")
    @GetMapping("/video/update")
    public JsonData<Object> needLogin() {
        return new JsonData<>(0, null, "videoUpdate");
    }

}
