apiclient = (function() {

	return {
		getCinemaByName : function(name, callback) {
			$.get("cinema/" + name, function(data) {
				callback(data);
			});

		}
	}

})();