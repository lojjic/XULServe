<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:xul="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<xsl:template match="xul:grid">
		<table width="100%">
			<xsl:apply-templates />
		</table>
	</xsl:template>

	<xsl:template match="xul:columns">
		<xsl:variable name="cols" select="xul:column" />
		<xsl:variable name="totalFlex" select="sum($cols/@flex)" />
		<xsl:variable name="flex">
			<xsl:choose>
				<xsl:when test="@flex"><xsl:value-of select="@flex" /></xsl:when>
				<xsl:otherwise>0</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:for-each select="$cols">
			<col width="{100 * number($flex) div $totalFlex}%" />
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="xul:rows">
		<tbody>
			<xsl:apply-templates />
		</tbody>
	</xsl:template>

	<xsl:template match="xul:row">
		<tr>
			<xsl:apply-templates mode="hbox-child" />
		</tr>
	</xsl:template>

</xsl:stylesheet>