



function XULApp(file) {
	this.window = new Packages.net.lojjic.xul.impl.WindowImpl(TODO);
}

XULApp.prototype = {

	display : function(pipeline) {

		var redisplaying = false;

		var bookmark = cocoon.createWebContinuation();

		if(redisplaying) {
			// Update form widget values:
			for(var names = cocoon.request.getParameterNames(); names.hasMoreElements();) {
				var name = names.nextElement();
				if(name.match(/^xul.field./)) {
					var field = this.window.document.getElementById(name.replace(/^xul.field./, ""));
					if(field) {
						field.value = cocoon.request.getParameter(name);
					}
				}
			}

			// Dispatch event:
			var eventType = cocoon.request.getParameter("xul.event.type");
			var eventTarget = cocoon.request.getParameter("xul.event.target");
			if(eventType && eventTarget) {
				var tgt = this.window.document.getElementById(eventTarget);
				if(tgt) {
					var evt = this.window.document.createEvent("UIEvents"); //TODO handle other event families
					evt.initUIEvent(eventType, true, true, this.window, cocoon.request.getParameter("xul.event.detail"));
					tgt.dispatchEvent(evt);
				}
			}
		}

		redisplaying = true;

		cocoon.sendPage(pipeline, {xul:TODO}, bookmark);

	}

};

XULApp.start = function() {
	var file = cocoon.parameters.file;
	var app = new XULApp(file);
	app.display();
};