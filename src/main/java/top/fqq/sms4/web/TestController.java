package top.fqq.sms4.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: fqq
 * @Date: 2019/8/15 10:30
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {


    @PostMapping("/test")
    public String test(@RequestBody String test){
        log.info(test);
        return "1234";
    }
}
