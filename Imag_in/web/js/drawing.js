window.onload = function() {
	//pass options and add custom controls to a board

	if (document.getElementById('custom-board') != null) {
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
	}

	if (document.getElementById('writeMessageBoard') != null) {

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
	}

}
