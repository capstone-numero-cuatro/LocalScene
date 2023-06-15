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

var venuesData; // Global variable to store all venues data
var currentPage = 1;
var venuesPerPage = 4;


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
        card.classList.add('card');

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

        // Append elements to card
        cardBody.appendChild(name);
        cardBody.appendChild(address);
        cardBody.appendChild(description);
        card.appendChild(cardBody);

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
    card.classList.add('card');
    card.onclick = function () {
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
    // getLocation(keyword); // If this is defined and needed, add it here or call it appropriately
});


    document.getElementById('search-form').addEventListener('submit', function (event) {
        event.preventDefault();
        var keyword = document.getElementById('search-input').value;
        fetchVenues(keyword);
    });

    document.getElementById('see-more-button').addEventListener('click', function () {
        currentPage++;
        displayVenues();
    });




document.getElementById('see-more-button').addEventListener('click', function () {
    // Add the logic here to display more venues or handle any desired functionality
});

