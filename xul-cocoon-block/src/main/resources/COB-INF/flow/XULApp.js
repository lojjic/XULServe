



function XULApp() {
	this.app = new Packages.net.lojjic.xul.XULApp(cocoon.parameters.file);
}

XULApp.prototype = {

	display : function(pipeline) {

		var redisplaying = false;

		var bookmark = cocoon.createWebContinuation();

		if(redisplaying) {
			var eventType = cocoon.request.getParameter("xul.event.type");
			var eventTarget = cocoon.request.getParameter("xul.event.target");
			if(eventType && eventTarget) {
				this.app.handleEvent(eventType, eventTarget);
			}
		}

		redisplaying = true;

		cocoon.sendPage(pipeline, {xul:this.app}, bookmark);

	}

};