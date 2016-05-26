package com.delay.web.api;

import com.delay.domain.dto.ResponseResult;
import com.delay.components.enums.CommandStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthStatus {

    /** Статус авторизации */
    @RequestMapping(value = "status")
    public ResponseResult status(Principal principal) {
        if (principal == null) {
            return new ResponseResult(CommandStatus.ANONYM);
        }
        return new ResponseResult(CommandStatus.OK);
    }

    /** Статус авторизации */
    @RequestMapping(value = "404")
    public ResponseResult accessDenied() {
        return new ResponseResult(CommandStatus.ACCESS_DENIED);
    }
}
