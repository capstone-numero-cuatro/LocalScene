var eventsData; // Global variable to store all events data

function fetchEvents(keyword, segmentId) {
    var baseUrl = 'https://app.ticketmaster.com';
    var endPoint = '/discovery/v2/events';
    var apiKey = 'UdZ8l4XkAoz0AEQZV9sRX2cOqvZL1Atw';
    // var segmentName = 'Concert';
    var url = `${baseUrl}${endPoint}.json?keyword=${keyword}&segmentId=${segmentId}&apikey=${apiKey}`;

    return fetch(url)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            displayEvents(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

function displayEvents(data) {
    eventsData = data._embedded.events; // Store all events data in the global variable
    var eventsContainer = document.getElementById('events-container');
    eventsContainer.innerHTML = ''; // Clear previous content

    var visibleEvents = eventsData.slice(0, 4); // Show only the first 4 events (1 row)
    visibleEvents.forEach(function (event) {
        var eventCard = createEventCard(event);
        eventsContainer.appendChild(eventCard);
    });

    if (eventsData.length > 4) {
        var seeMoreButton = createSeeMoreButton();
        eventsContainer.appendChild(seeMoreButton);
    }
}

{
    function createEventCard(event) {
        var card = document.createElement('div');
        card.classList.add('card');
        card.onclick = function () {
            showModal(event.name);
        };

        var cardBody = document.createElement('div');
        cardBody.classList.add('card-body');

        var name = document.createElement('h1');
        name.classList.add('card-title');
        name.textContent = event.name;

        var date = document.createElement('p');
        date.classList.add('card-text');
        date.textContent = event.dates.start.localDate;

        var venue = document.createElement('p');
        venue.classList.add('card-text');
        venue.textContent = event._embedded.venues[0].name;

        // Add image element
        if (event.images && event.images.length > 0) {
            var image = document.createElement('img');
            image.classList.add('event-image');
            image.src = event.images[0].url;
            image.alt = event.name;
            cardBody.appendChild(image);
        }

        cardBody.appendChild(name);
        cardBody.appendChild(date);
        cardBody.appendChild(venue);
        card.appendChild(cardBody);

        return card;
    }

    function createSeeMoreButton() {
        var button = document.createElement('button');
        button.classList.add('btn', 'btn-primary', 'mt-3');
        button.textContent = 'See More';

        button.addEventListener('click', function () {
            showMoreEvents();
        });

        return button;
    }

    function showMoreEvents() {
        var eventsContainer = document.getElementById('events-container');
        eventsContainer.innerHTML = ''; // Clear previous content

        eventsData.forEach(function (event) {
            var eventCard = createEventCard(event);
            eventsContainer.appendChild(eventCard);
        });
    }

    document.getElementById('search-form').addEventListener('submit', function (event) {
        event.preventDefault();
        var keyword = document.getElementById('search-input').value;
        fetchEvents(keyword, 'KZFzniwnSyZfZ7v7nJ');
        fetchVenues(keyword);
    })
    $('.tab-link').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    function showModal(title) {
        var modal = document.getElementById("modal");
        var modalTitle = document.getElementById("modal-title");
        var modalContent = document.getElementById("modal-content");

        modalTitle.innerHTML = title;
        modalContent.innerHTML = "This is the content of the modal dialog.";

        modal.style.display = "block";
    }

    function hideModal() {
        var modal = document.getElementById("modal");
        modal.style.display = "none";
    }
}