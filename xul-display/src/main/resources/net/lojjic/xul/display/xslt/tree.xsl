<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:xul="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<xsl:template match="xul:tree">
		<table>
			<thead>
				<tr>
					<xsl:for-each select="xul:treecols/xul:treecol">
						<th>
							<xsl:value-of select="@label" />
						</th>
					</xsl:for-each>
				</tr>
			</thead>
			<tbody>
				<xsl:apply-templates />
			</tbody>
		</table>
	</xsl:template>

	<xsl:template match="xul:treechildren">
		<xsl:apply-templates />
	</xsl:template>
	
	<xsl:template match="xul:treeitem">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="xul:treerow">
		<tr>
			<xsl:apply-templates />
		</tr>
	</xsl:template>

	<xsl:template match="xul:treecell">
		<td>
			<xsl:value-of select="@label" />
		</td>
	</xsl:template>


</xsl:stylesheet>