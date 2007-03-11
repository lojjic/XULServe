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
		<xsl:param name="nestingLevel" select="0" />
		<xsl:apply-templates>
			<xsl:with-param name="nestingLevel" select="$nestingLevel + 1" />
		</xsl:apply-templates>
	</xsl:template>
	
	<xsl:template match="xul:treeitem">
		<xsl:param name="nestingLevel" />
		<xsl:apply-templates>
			<xsl:with-param name="nestingLevel" select="$nestingLevel" />
		</xsl:apply-templates>
	</xsl:template>

	<xsl:template match="xul:treerow">
		<xsl:param name="nestingLevel" />
		<tr>
			<xsl:apply-templates>
				<xsl:with-param name="nestingLevel" select="$nestingLevel" />
			</xsl:apply-templates>
		</tr>
	</xsl:template>

	<xsl:template match="xul:treecell">
		<xsl:param name="nestingLevel" />
		<td style="padding-left:{$nestingLevel}em">
			<xsl:value-of select="@label" />
		</td>
	</xsl:template>

	<xsl:template match="xul:treeseparator">
		<tr>
			<td colspan="{count(ancestor::xul:tree/xul:treecols/xul:treecol)}"><hr /></td>
		</tr>
	</xsl:template>


</xsl:stylesheet>