package com.datacenter.webserver.models;

import com.datacenter.webserver.dto.BaseDTO;

public abstract class BaseEntity {
    public abstract BaseDTO toDTO();
}
