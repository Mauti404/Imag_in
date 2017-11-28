window.onload = function() {
	//pass options and add custom controls to a board
	var customBoard = new DrawingBoard.Board('custom-board', {
		controls: [
			'Color',
			{ Size: { type: 'dropdown' } },
			{ DrawingMode: { filler: false } },
			'Navigation',
			'Download'
		],
		size: 0.5,
		webStorage: 'session',
		enlargeYourContainer: true
	});

	//There are multiple ways to add a control to a board after its initialization:
	customBoard.addControl('Download'); //if the DrawingBoard.Control.Download class exists

	//or...
	//var downloadControl = new DrawingBoard.Control.Download(customBoard).addToBoard();

	//or...
	//var downloadControl = new DrawingBoard.Control.Download(customBoard);

	$('.changeImg').on('submit', function(e) {
		var canvas = document.getElementById('canvas');
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
