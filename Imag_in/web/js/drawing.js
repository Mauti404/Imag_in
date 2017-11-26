window.onload = function() {
	//pass options and add custom controls to a board
	var customBoard = new DrawingBoard.Board('custom-board', {
		background: "#ff7ffe",
		color: "#ff0",
		size: 30,
		controls: [
			{ Size: { type: "range" } },
			{ Navigation: { back: false, forward: false } },
			'DrawingMode'
		],
		webStorage: 'local'
	});

	//There are multiple ways to add a control to a board after its initialization:
	customBoard.addControl('Download'); //if the DrawingBoard.Control.Download class exists

	//or...
	//var downloadControl = new DrawingBoard.Control.Download(customBoard).addToBoard();

	//or...
	//var downloadControl = new DrawingBoard.Control.Download(customBoard);
}

