"use strict";

/***
 * geocode is a method to search for coordinates based on a physical address and return
 * @param {string} search is the address to search for the geocoded coordinates
 * @param {string} token is your API token for MapBox
 * @returns {Promise} a promise containing the latitude and longitude as a two element array
 *
 * EXAMPLE:
 *
 *  geocode("San Antonio", API_TOKEN_HERE).then(function(results) {
 *      // do something with results
 *  })
 *
 */
function geocode(search, token) {
    var baseUrl = 'https://api.mapbox.com';
    var endPoint = '/geocoding/v5/mapbox.places/';
    return fetch(baseUrl + endPoint + encodeURIComponent(search) + '.json' + "?" + 'access_token=' + token)
        .then(function(res) {
            return res.json();
            // to get all the data from the request, comment out the following three lines...
        }).then(function(data) {
            return data.features[0].center;
        });
}
/***
 * reverseGeocode is a method to search for a physical address based on inputted coordinates
 * @param {object} coordinates is an object with properties "lat" and "lng" for latitude and longitude
 * @param {string} token is your API token for MapBox
 * @returns {Promise} a promise containing the string of the closest matching location to the coordinates provided
 *
 * EXAMPLE:
 *
 *  reverseGeocode({lat: 32.77, lng: -96.79}, API_TOKEN_HERE).then(function(results) {
 *      // do something with results
 *  })
 *
 */
function reverseGeocode(coordinates, token) {
    var baseUrl = 'https://api.mapbox.com';
    var endPoint = '/geocoding/v5/mapbox.places/';
    return fetch(baseUrl + endPoint + coordinates.lng + "," + coordinates.lat + '.json' + "?" + 'access_token=' + token)
        .then(function(res) {
            return res.json();
        })
        // to get all the data from the request, comment out the following three lines...
        .then(function(data) {
            return data.features[0].place_name;
        });
}

// ticketmaster
function fetchVenues(keyword) {
    var baseUrl = 'https://app.ticketmaster.com';
    var endPoint = '/discovery/v2/venues';
    var apiKey = 'UdZ8l4XkAoz0AEQZV9sRX2cOqvZL1Atw';
    var url = `${baseUrl}${endPoint}.json?keyword=${keyword}&apikey=${apiKey}`;

    return fetch(url)
        .then(function (response) {
            return response.json();
        }).then(function (data) {
            console.log(data);
            displayVenues(data);
        });
}

var venuesData; // Global variable to store all venues data
var currentPage = 1;
var venuesPerPage = 4;

function displayVenues(data) {
    venuesData = data._embedded.venues; // Store all venues data in the global variable
    var venuesContainer = document.getElementById('venues-container');
    venuesContainer.innerHTML = ''; // Clear previous content

    var startIndex = (currentPage - 1) * venuesPerPage;
    var endIndex = startIndex + venuesPerPage;
    var visibleVenues = venuesData.slice(startIndex, endIndex);

    visibleVenues.forEach(function (venue) {
        var venueCard = createVenueCard(venue);
        venuesContainer.appendChild(venueCard);
    });

    if (venuesData.length > endIndex) {
        document.getElementById('see-more-container').style.display = 'block';
    } else {
        document.getElementById('see-more-container').style.display = 'none';
    }
}

function createVenueCard(venue) {
    // Create card element
    var card = document.createElement('div');
    card.classList.add('card');card.onclick = function () {
        showModal(venue.name);
    };

    // Create card body
    var cardBody = document.createElement('div');
    cardBody.classList.add('card-body');

    // Create card title
    var name = document.createElement('h5');
    name.classList.add('card-title');
    name.textContent = venue.name;

    // Create card address
    var address = document.createElement('p');
    address.classList.add('card-text');
    address.textContent = venue.address.line1 + ', ' + venue.city.name + ', ' + venue.state.name + ', ' + venue.postalCode;

    // Create card description
    var description = document.createElement('p');
    description.classList.add('card-text');
    description.textContent = venue.description;

    if (venue.images && venue.images.length > 0) {
        var image = document.createElement('img');
        image.classList.add('venue-image');
        image.src = venue.images[0].url;
        image.alt = venue.name;
        cardBody.appendChild(image);
    }
    // Append elements to card
    cardBody.appendChild(name);
    cardBody.appendChild(address);
    cardBody.appendChild(description);
    card.appendChild(cardBody);

    return card;
}

document.getElementById('search-form').addEventListener('submit', function (event) {
    event.preventDefault();
    var keyword = document.getElementById('search-input').value;
    fetchVenues(keyword);
    getLocation(keyword);
});

document.getElementById('see-more-button').addEventListener('click', function () {
})

