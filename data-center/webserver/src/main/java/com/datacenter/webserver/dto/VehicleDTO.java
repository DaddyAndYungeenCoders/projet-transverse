package com.datacenter.webserver.dto;

import com.datacenter.webserver.models.VehicleEntity;
import com.datacenter.webserver.models.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO extends BaseDTO {
    Long id;
    int capacity;
    int weight;
    int speed;
    ProductType product_type;
    int fire_mastery;
    Long id_fire_station;

    @Override
    public VehicleEntity toEntity() {
        return new VehicleEntity(this.id, this.capacity, this.weight, this.speed, this.product_type, this.fire_mastery, this.id_fire_station);
    }
}
