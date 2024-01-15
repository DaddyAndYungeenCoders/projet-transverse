package com.simulator.models;

import java.io.Serializable;

public class CoordsEntity implements Serializable{

    private double latitude;
    private double longitude;

    public CoordsEntity() {
    }

    public CoordsEntity(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isCloseTo(CoordsEntity otherCoords, double maxDistance) {
        double distance = calculateDistance(this.latitude, this.longitude, otherCoords.latitude, otherCoords.longitude);
        return distance <= maxDistance;
    }

    // Méthode utilitaire pour calculer la distance entre deux points géographiques
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Utilisez ici une formule appropriée pour calculer la distance entre deux points géographiques.
        // Cette formule dépend de la projection que vous souhaitez utiliser (p. ex. Haversine).
        // Voici un exemple simple utilisant la formule de Haversine :

        final int R = 6371; // Rayon de la Terre en kilomètres

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}

