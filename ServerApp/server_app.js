//////////////////////////////////////////////////////////// 
// Simple Node.js HTTP server receives req to route /"quote"
// and returns a random quote in JSON format.
////////////////////////////////////////////////////////////

var express = require('./node_modules/express');
var app = express(); 
var fs  = require("fs"); //file stream for reading the quote file

//some constants
//quote_file - file containing multiple quotes
var quote_file='./Quotes.TXT';

var listen_port = 3000; 

//constant array of quotes.
//will be used in case no file available.
var quotes = [
  "Nothing is impossible, the word itself says 'I'm possible'!",
  "You may not realize it when it happens, but a kick in the teeth may be the best thing in the world for you",
  "Even the greatest was once a beginner. Don't be afraid to take that first step.",
  "You are afraid to die, and you're afraid to live. What a way to exist."
];

//read quote file async into quote_arr.
//upon error set quote_arr to constant quotes arr.
var quote_arr;
fs.readFile(quote_file, function(err, f){
	console.log("Reading quotes file %s ...", quote_file);
	if(err) 
	{
		console.log("Error received when trying to read file %s, using hard coded...", err);
		quote_arr = quotes;
	}
		
    else  	
    	quote_arr = f.toString().split('\r\n');
});

//get request on '/quote' port and return a random
//quote from quote array
app.get('/quote', function(req, res) {
	console.log('Got request ...')
 	var rand_index = Math.floor(Math.random() * quote_arr.length);
  	var quote = quote_arr[rand_index];

	var json = JSON.stringify({ 
    quote : quote, 
  });

  	res.end(json);
});

//listen on http://localhost:3000
var server = app.listen(listen_port, function() {
    console.log('Server listening on port %d ...', listen_port);
});




