let myToken ="pk.eyJ1Ijoid29kbjIzNDUiLCJhIjoiY2xpajJ1aTA2MDNyejNlcGJ1NTJ5ZTNnYSJ9.U7kLp4bsqObbZ2DHI7b63w";



$('#cityLocation').click(function(e){
    e.preventDefault();
    getLocation($('#cityInput').val());
})

mapboxgl.accessToken =myToken;
const map = new mapboxgl.Map({
    container: 'map', // container ID
    style: 'mapbox://styles/mapbox/streets-v12', // style URL
    center: [-96.8,33.0], // starting position [lng, lat]
    zoom: 10, // starting zoom
});
let myMarker= new mapboxgl.Marker({draggable: true})
    .setLngLat([-96.8,33.0])
    .addTo(map);
console.log(myMarker);

function onDragEnd() {
    let lngLat = myMarker.getLngLat();
    let arrlocation = [lngLat.lng, lngLat.lat];
    console.log(arrlocation);
    getLocation(arrlocation)
}
myMarker.on('dragend', onDragEnd)
function getLocation(searchString){
    geocode(searchString,myToken).then(function(results){
        myMarker.setLngLat(results);
        map.flyTo({center: results, zoom: 9});

    })
}