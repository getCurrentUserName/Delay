package com.delay.domain.dto;

/**
 * Created by LucidMinds on 25.05.16.
 * package com.delay.domain.dto;
 */
public class CriteriaDTO {

    private String param;
    private Object value;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public CriteriaDTO(String param, Object value) {
        this.param = param;
        this.value = value;
    }
}
