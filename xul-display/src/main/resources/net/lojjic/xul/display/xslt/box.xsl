<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:xul="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<xsl:template match="xul:hbox | xul:box[@orient = 'horizontal']">\
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

	<xsl:template match="xul:vbox | xul:box[@orient = 'vertical']">
		<table height="100%" width="100%">
			<tbody>
				<xsl:apply-templates mode="vbox-child" />
			</tbody>
		</table>
	</xsl:template>

	<xsl:template match="xul:*" mode="vbox-child">
		<tr>
			<td>
				<xsl:apply-templates select="." />
			</td>
		</tr>
	</xsl:template>

</xsl:stylesheet>