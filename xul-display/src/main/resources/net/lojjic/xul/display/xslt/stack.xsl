<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:xul="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<xsl:template match="xul:stack">
		<div class="stack">
			<xsl:apply-templates mode="stack-item" />
		</div>
	</xsl:template>

	<xsl:template match="xul:*" mode="stack-item">
		<div class="stack-item">
			<xsl:apply-templates select="." />
		</div>
	</xsl:template>

</xsl:stylesheet>