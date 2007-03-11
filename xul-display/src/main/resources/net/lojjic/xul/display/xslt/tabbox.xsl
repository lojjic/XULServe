<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:xul="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<xsl:template match="xul:tabbox">
		<div class="tabbox">
			<xsl:apply-templates select="." mode="box">
				<xsl:with-param name="default-orient">vertical</xsl:with-param>
			</xsl:apply-templates>
		</div>
	</xsl:template>

	<xsl:template match="xul:tabs">
		<div class="tabs">
			<xsl:apply-templates />
		</div>
	</xsl:template>

	<xsl:template match="xul:tab">
		<span class="tab">
			<xsl:value-of select="@label" />
		</span>
	</xsl:template>

	<xsl:template match="xul:tabpanels">
		<div class="tabpanels">
			<xsl:apply-templates />
		</div>
	</xsl:template>

	<xsl:template match="xul:tabpanel">
		<xsl:apply-templates select="." mode="box" />
	</xsl:template>

</xsl:stylesheet>