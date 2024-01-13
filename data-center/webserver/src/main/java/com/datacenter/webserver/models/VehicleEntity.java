package com.datacenter.webserver.models;

import com.datacenter.webserver.dto.VehicleDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicule")
public class VehicleEntity extends BaseEntity {
    @Id
    Long id;
    int capacity;
    int weight;
    int speed;
    ProductType product_type;
    int fire_mastery;
    /**
     * @see com.datacenter.webserver.models.FireStationEntity
     */
    Long id_fire_station;

    @Override
    public VehicleDTO toDTO() {
        return new VehicleDTO(this.id, this.capacity, this.weight, this.speed, this.product_type, this.fire_mastery, this.id_fire_station);
    }
}
