"use strict";

function geocode(search, token) {
    var baseUrl = 'https://api.mapbox.com';
    var endPoint = '/geocoding/v5/mapbox.places/';
    return fetch(baseUrl + endPoint + encodeURIComponent(search) + '.json' + "?" + 'access_token=' + token)
        .then(function(res) {
            return res.json();
        }).then(function(data) {
            return data.features[0].center;
        });
}

function reverseGeocode(coordinates, token) {
    var baseUrl = 'https://api.mapbox.com';
    var endPoint = '/geocoding/v5/mapbox.places/';
    return fetch(baseUrl + endPoint + coordinates.lng + "," + coordinates.lat + '.json' + "?" + 'access_token=' + token)
        .then(function(res) {
            return res.json();
        })
        .then(function(data) {
            return data.features[0].place_name;
        });
}

function fetchVenues(keyword) {
    var baseUrl = 'https://app.ticketmaster.com';
    var endPoint = '/discovery/v2/venues';
    var apiKey = 'UdZ8l4XkAoz0AEQZV9sRX2cOqvZL1Atw';
    var url = `${baseUrl}${endPoint}.json?keyword=${keyword}&apikey=${apiKey}`;

    return fetch(url)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log(data);
            displayVenues(data);
        })
        .catch(function (error) {
            console.error('Error:', error);
        });
}

var venuesData;
var currentPage = 1;
var venuesPerPage = 4;

function displayVenues(data) {
    venuesData = data._embedded.venues;
    var venuesContainer = document.getElementById('venues-container');
    venuesContainer.innerHTML = '';

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
    var card = document.createElement('div');
    card.classList.add('card');
    card.onclick = function () {
        showModal(venue.name);
    };

    var cardBody = document.createElement('div');
    cardBody.classList.add('card-body');

    var name = document.createElement('h5');
    name.classList.add('card-title');
    name.textContent = venue.name;

    var address = document.createElement('p');
    address.classList.add('card-text');
    address.textContent = venue.address.line1 + ', ' + venue.city.name + ', ' + venue.state.name + ', ' + venue.postalCode;

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
    // getLocation(keyword); // If this is defined and needed, add it here or call it appropriately
});

document.getElementById('see-more-button').addEventListener('click', function () {
    // Add the logic here to display more venues or handle any desired functionality
});
