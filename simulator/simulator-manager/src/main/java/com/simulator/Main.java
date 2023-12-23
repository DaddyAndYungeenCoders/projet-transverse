package com.simulator;

import com.simulator.repository.FireEventRepository;
import com.simulator.repository.FireStationRepository;
import com.simulator.repository.SensorRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world22!");
        SensorRepository sensorRepository = new SensorRepository();
        System.out.println(SensorRepository.getSensorById(1L).getCoordsEntity().getLatitude());
        FireEventRepository fireEventRepository = new FireEventRepository();
        System.out.println(fireEventRepository.getFireEventById(2L).getCoords().getLatitude());
        System.out.println(fireEventRepository.getFireEventById(2L).getIntervention().get(0).getDuration());
        System.out.println(fireEventRepository.getFireEventById(2L).getIntervention().get(0).getTeam().getFire_station().getAddress());

        System.out.println(SensorRepository.getSensorById(1L).getDetects().getFirst().getFireEventEntity().getIntervention().getFirst().getTeam().getFire_station().getAddress());

        FireStationRepository fireStationRepository = new FireStationRepository();
        System.out.println("test ulitme");
        System.out.println(fireStationRepository.getFireStationById(1L).getTeams().get(0).getIntervention().get(0).getFire_event().getDetecs().getSensorEntity().getCoordsEntity().getLatitude());
    }
}