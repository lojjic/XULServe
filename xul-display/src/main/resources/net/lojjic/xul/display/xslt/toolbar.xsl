<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:xul="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<xsl:template match="xul:toolbox">
		<div class="toolbox">
			<xsl:apply-templates />
		</div>
	</xsl:template>

	<xsl:template match="xul:toolbar">
		<div class="toolbar">
			<xsl:apply-templates />
		</div>
	</xsl:template>

	<xsl:template match="xul:toolbarbutton">
		<div class="toolbarbutton">
			<xsl:value-of select="label" />
		</div>
	</xsl:template>

</xsl:stylesheet>