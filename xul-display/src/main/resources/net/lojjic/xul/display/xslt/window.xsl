<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:xul="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<xsl:template match="xul:window">
		<html>
			<head>
				<title><xsl:value-of select="@title" /></title>
				<style type="text/css">
					tr {vertical-align:top;}
					.label {white-space:nowrap;}
					input.textbox, textarea.textbox {width:100%;}
					select.menulist, select.listbox {width:100%;}
					.tab {border:1px solid; -moz-border-radius:4px 4px 0 0; cursor:default;}
				</style>
			</head>
			<body>
				<xsl:apply-templates />
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>