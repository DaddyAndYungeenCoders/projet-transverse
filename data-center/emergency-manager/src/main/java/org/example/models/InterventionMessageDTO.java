package org.example.models;

public class InterventionMessageDTO {
    private Coords coords;
    private int stamina;
    private int fire_mastery_total;
    private Long fire_station_id;
    private Long team_id;
    private Long sensor_id;

    public InterventionMessageDTO(Coords coords, int stamina, int fire_mastery_total, Long fire_station_id, Long team_id, Long sensor_id) {
        this.coords = coords;
        this.stamina = stamina;
        this.fire_mastery_total = fire_mastery_total;
        this.fire_station_id = fire_station_id;
        this.team_id = team_id;
        this.sensor_id = sensor_id;
    }

    public Long getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(Long sensor_id) {
        this.sensor_id = sensor_id;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getFire_mastery_total() {
        return fire_mastery_total;
    }

    public void setFire_mastery_total(int fire_mastery_total) {
        this.fire_mastery_total = fire_mastery_total;
    }

    public Long getFire_station_id() {
        return fire_station_id;
    }

    public void setFire_station_id(Long fire_station_id) {
        this.fire_station_id = fire_station_id;
    }

    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }
}
