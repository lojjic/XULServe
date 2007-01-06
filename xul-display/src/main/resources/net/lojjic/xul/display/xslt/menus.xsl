<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:xul="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<xsl:template match="xul:menubar">
		<div class="menubar">
			<xsl:apply-templates />
		</div>
	</xsl:template>

	<xsl:template match="xul:menu">
		<div class="menu">
			<div class="menulabel"><xsl:value-of select="@label" /></div>
			<xsl:apply-templates />
		</div>
	</xsl:template>

	<xsl:template match="xul:menupopup">
		<div class="menupopup">
			<xsl:apply-templates />
		</div>
	</xsl:template>

	<xsl:template match="xul:menuseparator">
		<hr />
	</xsl:template>

	<xsl:template match="xul:menuitem">
		<div class="menuitem"></div>
	</xsl:template>

</xsl:stylesheet>