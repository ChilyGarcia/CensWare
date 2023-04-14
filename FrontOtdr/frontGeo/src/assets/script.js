function holaMundo()
{
   
    //Creacion del mapa
    let map = L.map('map').setView([4.639386,-74.082412],5)

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',{
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);
    // - - - - - - - - - - - - - - - - - - 

    //Zoom a la localizacion de interés
    document.getElementById('select-location').addEventListener('change',function(e){
        let coords = e.target.value.split(",");
        map.flyTo(coords,18);
    })
    // - - - - - - - - - - - - - - - - - - 

    //Marcadores para puntos de interes
    let puntoInteresInfo = [];

    var marcador = L.marker([7.8954107550420005, -72.50195553836092]).addTo(map);
    var marcador2 = L.marker([7.921171184587934,-72.48024101219407]).addTo(map);
    var marcador3 = L.marker([7.918014240793359,-72.49329659781102]).addTo(map);
    var marcador4 = L.marker([7.888235561076866,-72.49681042061687]).addTo(map);

    puntoInteresInfo.push(marcador);
    puntoInteresInfo.push(marcador2);
    puntoInteresInfo.push(marcador3);
    puntoInteresInfo.push(marcador4);

    //Sirve para dar mas informacion acerca de la latitud y longitud de un punto
    for(var i =0; i< puntoInteresInfo.length; i++)
    {
    puntoInteresInfo[i].on('click', function(e) {
        alert("Latitud: " + e.latlng.lat + "\nLongitud: " + e.latlng.lng);
    });
  
    }
    //- - - - - - - - - - - - - - - -
}

