
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

    function displayVenues(data) {
        var venuesContainer = document.getElementById('venues-container');
        venuesContainer.innerHTML = ''; // Clear previous content

        var venues = data._embedded.venues;
        venues.forEach(function (venue) {
            var venueCard = createVenueCard(venue);
            venuesContainer.appendChild(venueCard);
        });
    }

    function createVenueCard(venue) {
        var card = document.createElement('div');
        card.classList.add('card', 'col-md-6', 'mb-4');

        var cardBody = document.createElement('div');
        cardBody.classList.add('card-body');

        var name = document.createElement('h5');
        name.classList.add('card-title');
        name.textContent = venue.name;

        var address = document.createElement('p');
        address.classList.add('card-text');
        address.textContent = venue.address.line1 + ', '+ venue.city.name + ', ' + venue.state.name + ', ' + venue.postalCode;

        var description = document.createElement('p');
        description.classList.add('card-text');
        description.textContent = venue.description;

        // var image = document.createElement('img');
        // image.classList.add('card-img-top');
        // image.src = venue.images[0].url;
        // image.alt = venue.name;

        cardBody.appendChild(name);
        cardBody.appendChild(address);
        cardBody.appendChild(description);
        // card.appendChild(image);
        card.appendChild(cardBody);

        return card;
    }

    document.getElementById('search-form').addEventListener('submit', function (event) {
        event.preventDefault();
        var keyword = document.getElementById('search-input').value;
        fetchVenues(keyword);
    });