// console.log('POINTS:' + latlngs[0].lat + ' ' + latlngs[0].lng);
// const positionTab: turf.Position[] = [];

// latlngs.forEach((point) => {
//   positionTab.push([point.lat, point.lng]);
// });

// const turfPolygon = turf.polygon([
//   latlngs.map((point) => [point.lng, point.lat]),
// ]);
// const bbox = turf.bbox(turfPolygon);

// // Calcul de la latitude moyenne pour ajuster l'espacement de la longitude
// const latMin = bbox[1];
// const latMax = bbox[3];
// const latMean = (latMin + latMax) / 2;

// // Calcul de l'espacement en longitude
// const kmPerDegree = 111.32; // Approximation à l'équateur
// const lngSpacing =
//   0.5 / (kmPerDegree * Math.cos((latMean * Math.PI) / 180));

// // Créer manuellement la grille de points
// let pointsDansPolygone = [];
// for (let lat = latMin; lat <= latMax; lat += 0.5 / kmPerDegree) {
//   for (let lng = bbox[0]; lng <= bbox[2]; lng += lngSpacing) {
//     let point = turf.point([lng, lat]);
//     if (turf.booleanPointInPolygon(point, turfPolygon)) {
//       pointsDansPolygone.push(point.geometry.coordinates);
//     }
//   }
// }
// const sensorDTOs: SensorDTO[] = pointsDansPolygone.map((coords) => {
//   return {
//     coords: { latitude: coords[1], longitude: coords[0] },
//     intensity: 0,
//   };
// });
// this.sensorsCreationService.create(sensorDTOs);
