<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:xul="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<xsl:template match="xul:grid">
		<table>
			
		</table>
	</xsl:template>

	<xsl:template match="xul:columns">
		<xsl:variable name="cols" select="xul:column" />
		<xsl:variable name="totalFlex" select="sum($cols/@flex)" />
		<xsl:for-each select="$cols">
			<col width="{100 * number(@flex) div $totalFlex}%" />
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="xul:rows">
		<tbody>
			<xsl:apply-templates />
		</tbody>
	</xsl:template>

	<xsl:template match="xul:row">
		<tr>
			<xsl:apply-templates mode="grid-cell" />
		</tr>
	</xsl:template>

	<xsl:template match="xul:*" mode="grid-cell">
		<xsl:apply-templates select="." />
	</xsl:template>

</xsl:stylesheet>