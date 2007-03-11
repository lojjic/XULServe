<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:xul="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<xsl:template match="xul:label[@control]">
		<label for="{@control}" class="label">
			<xsl:copy-of select="@accesskey" /> 
			<xsl:value-of select="@value" />
		</label>
	</xsl:template>

	<xsl:template match="xul:label">
		<span class="label">
			<xsl:copy-of select="@accesskey" /> 
			<xsl:value-of select="@value" />
		</span>
	</xsl:template>

	<xsl:template match="xul:description">
		<div class="description">
			<xsl:value-of select="@value" />
			<xsl:apply-templates />
		</div>
	</xsl:template>

</xsl:stylesheet>