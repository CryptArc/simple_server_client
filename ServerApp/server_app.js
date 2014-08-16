//////////////////////////////////////////////////////////// 
// Simple Node.js HTTP server receives req to route /"quote"
// and returns a random quote in JSON format.
////////////////////////////////////////////////////////////

var express = require('./express');
var server = express(); 
var fs  = require("fs"); //file stream for reading the quote file
var util = require('util'); //define util

//some constants
//quote_file - file containing multiple quotes
var quote_file='./Quotes.TXT';

var port = process.env.OPENSHIFT_NODEJS_PORT || 8080
var ip_address = process.env.OPENSHIFT_NODEJS_IP || '192.168.1.19'

//constant array of quotes.
//will be used in case no file available.
var quotes = [
  "Nothing is impossible, the word itself says 'I'm possible'!",
  "You may not realize it when it hserverens, but a kick in the teeth may be the best thing in the world for you",
  "Even the greatest was once a beginner. Don't be afraid to take that first step.",
  "You are afraid to die, and you're afraid to live. What a way to exist."
];

//read quote file async into quote_arr.
//upon error set quote_arr to constant quotes arr.
var quote_arr;
fs.readFile(quote_file, function(err, f){

	util.log('Reading quotes file: ' + quote_file);

	if(err) 
	{
		util.log('Error: ' + err + ' received when trying to read file: '  +  quote_file);
		quote_arr = quotes;
	}
		
    else
    { 
      util.log('Successfully read file ' + quote_file)
      quote_arr = f.toString().split('\r\n');
    }
});

//get request on '/quote' port and return a random
//quote from quote_arr
server.get('/quote', function(req, res) {
  var client = (req.headers['x-forwarded-for'] || '').split(',')[0] || req.connection.remoteAddress;
	util.log('Got request from ' + client);

 	var rand_index = Math.floor(Math.random() * quote_arr.length); 
  var quote = quote_arr[rand_index];
	var json_quote = JSON.stringify({ 
    quote : quote, 
  });

  res.end(json_quote);

  util.log('sent response: ' + json_quote);

});

//listen on http://localhost:3000
var server = server.listen(port, ip_address,function() {
    util.log('Server listening on host: ' + ip_address + ' ,port: ' +  port);
});




