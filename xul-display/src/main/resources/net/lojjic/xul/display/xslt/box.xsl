<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:xul="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<xsl:template match="xul:*" mode="box">
		<xsl:param name="default-orient">horizontal</xsl:param>
		<xsl:variable name="orient">
			<xsl:choose>
				<xsl:when test="@orient"><xsl:value-of select="@orient" /></xsl:when>
				<xsl:otherwise><xsl:value-of select="$default-orient" /></xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:choose>
			<xsl:when test="$orient = 'horizontal'">
				<xsl:apply-templates select="." mode="hbox" />
			</xsl:when>
			<xsl:when test="$orient = 'vertical'">
				<xsl:apply-templates select="." mode="vbox" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:message terminate="no">Unrecognized @orient: <xsl:value-of select="$orient" /></xsl:message>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="xul:*" mode="hbox">
		<table width="100%" height="100%">
			<tbody>
				<tr>
					<xsl:apply-templates mode="hbox-child" />
				</tr>
			</tbody>
		</table>
	</xsl:template>

	<xsl:template match="xul:*" mode="hbox-child">
		<td>
			<xsl:apply-templates select="." />
		</td>
	</xsl:template>

	<xsl:template match="xul:*" mode="vbox">
		<table height="100%" width="100%">
			<tbody>
				<xsl:apply-templates mode="vbox-child" />
			</tbody>
		</table>
	</xsl:template>

	<xsl:template match="xul:*" mode="vbox-child">
		<tr>
			<td>
				<xsl:if test="@flex">
					<xsl:attribute name="height">
						<xsl:value-of select="concat(number(@flex) * 100, '%')" />
					</xsl:attribute>
				</xsl:if>
				<xsl:apply-templates select="." />
			</td>
		</tr>
	</xsl:template>



	<xsl:template match="xul:hbox | xul:box[@orient = 'horizontal']">
		<xsl:apply-templates select="." mode="hbox" />
	</xsl:template>

	<xsl:template match="xul:vbox | xul:box[@orient = 'vertical']">
		<xsl:apply-templates select="." mode="vbox" />
	</xsl:template>



</xsl:stylesheet>