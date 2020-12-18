# movieSearch
Simple app that allows you to search for movies and display them in recycler view as a list of cards with image, title and overview. 
Showcases usage of:
- <b>Retrofit</b> for api retrieval and simplification of network interface, 
- <b>Gson</b> for consuming and parsing of json from api into model layer classes,
- <b>Picasso</b> for image loading and caching for recycler view. 

## Features/UseCases: 
- Show a toast message if input field left empty and search button is triggered
- Show a progress bar while values from api are being retrieved
- Show an empty screen if invalid search term is entered and there are no search results
- Show a list of movies of successful search

### Dependencies added:
- Retrofit
- Gson
- Picasso

---
Please add your api key into local.properties before running (apiKey=<yourApiKey>)
---
