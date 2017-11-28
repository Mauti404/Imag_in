window.onload = function() {
	//pass options and add custom controls to a board
	var customBoard = new DrawingBoard.Board('custom-board', {
		controls: [
			'Color',
			{ Size: { type: 'dropdown' } },
			{ DrawingMode: { filler: false } },
			'Navigation'
		],
		size: 0.5,
		webStorage: 'session',
		enlargeYourContainer: true
	});

	customBoard.addControl('Download');

	$('.changeImg').on('submit', function(e) {
	   //get drawingboard content
	  var img = customBoard.getImg();
	  
	  //we keep drawingboard content only if it's not the 'blank canvas'
	  var imgInput = (customBoard.blankCanvas == img) ? '' : img;
	  
	  //put the drawingboard content in the form field to send it to the server
	  $(this).find('input[name=hidden_data]').val(imgInput);

	  //we can also assume that everything goes well server-side
	  //and directly clear webstorage here so that the drawing isn't shown again after form submission
	  //but the best would be to do when the server answers that everything went well
	  customBoard.clearWebStorage();
	});

	var messageBoard = new DrawingBoard.Board('writeMessageBoard', {
		controls: [
			'Color',
			{ Size: { type: 'dropdown' } },
			{ DrawingMode: { filler: false } },
			'Navigation'
		],
		size: 0.5,
		webStorage: 'session',
		enlargeYourContainer: true
	});

	messageBoard.addControl('Download');

	$('.writeMessage').on('submit', function(e) {
	   //get drawingboard content
	  var img = messageBoard.getImg();
	  
	  //we keep drawingboard content only if it's not the 'blank canvas'
	  var imgInput = (messageBoard.blankCanvas == img) ? '' : img;
	  
	  //put the drawingboard content in the form field to send it to the server
	  $(this).find('input[name=hidden_data]').val(imgInput);

	  //we can also assume that everything goes well server-side
	  //and directly clear webstorage here so that the drawing isn't shown again after form submission
	  //but the best would be to do when the server answers that everything went well
	  messageBoard.clearWebStorage();
	});
	

	/*
	var button = document.getElementById("formTest");

	if (button == null) {
		console.log("erreur");
	}

	button.onclick = function() {
		var canvas = document.getElementById('canvas-drawing-id');
		var dataURL = canvas.toDataURL("image/png");
		console.log(dataURL);
		document.getElementById('hidden_data_canvas').value = dataURL;
		document.getElementById("changeImg").submit();
	}
	*/


}
