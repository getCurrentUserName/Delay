package com.delay.domain.dto;

import com.delay.components.enums.CommandStatus;

public class ResponseResult {

    public String result;

    public String status = "OK";

    public ResponseResult(String result) {
        this.result = result;
    }

    public ResponseResult() {
    }

    public ResponseResult(CommandStatus result) {
        this.result = result.toString();
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setResult(CommandStatus result) {
        this.result = result.toString();
    }
}
