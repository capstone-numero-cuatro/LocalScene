
// function searchVenues(location) {
//     let options = {
//         keyword: 'music',
//         locale: 'en-us',
//         size: 10,
//         radius: 100 // Set the radius in miles (adjust as needed)
//     };
//

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
            });
    }



// function searchVenues(location) {
//     let options = {
//         keyword: 'music',
//         locale: 'en-us',
//         size: 10,
//         city: location,
//         radius: 100 // Set the radius in miles (adjust as needed)
//     };
// // TMConnect=
// TMConnect.venuesDiscovery.search(options)
//     .then(function(response) {
//         displayVenues(response.embedded.venues);
//     })
//     .catch(function(error) {
//         console.log('Error:', error);
//     });
// }
//
// // Function to display the venues
//     function displayVenues(venues) {
//         let container = $('#venues-container');
//         container.empty();
//
//         $.each(venues, function (index, venue) {
//             let venueCard = $('<div>').addClass('col-md-4');
//             let card = $('<div>').addClass('card venue-card');
//             let cardBody = $('<div>').addClass('card-body');
//             let title = $('<h5>').addClass('card-title').text(venue.name);
//             let address = $('<p>').addClass('card-text').text(venue.address.line1);
//
//             cardBody.append(title, address);
//             card.append(cardBody);
//             venueCard.append(card);
//             container.append(venueCard);
//         });
//     }
//
// // Handle form submission
//     $('#search-form').submit(function (event) {
//         event.preventDefault();
//         let location = $('#search-input').val();
//         searchVenues(location);
//     });
