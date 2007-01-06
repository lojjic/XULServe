<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="accesskeyText">
		<xsl:param name="text" />
		<xsl:param name="accesskey" />
		<xsl:choose>
			<xsl:when test="contains($text, $accesskey)">
				<xsl:value-of select="substring-before($text, $accesskey)" />
				<span class="accesskey"><xsl:value-of select="$accesskey" /></span>
				<xsl:value-of select="substring-after($text, $accesskey)" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="concat($text, ' [', $accesskey, ']')" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

</xsl:stylesheet>