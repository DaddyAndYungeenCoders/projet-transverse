package com.datacenter.webserver.dto;

import com.datacenter.webserver.models.BaseEntity;

public abstract class BaseDTO {
    public abstract BaseEntity toEntity();
}
