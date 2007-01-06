<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:xul="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<xsl:template match="xul:textbox">
		<input>
			<xsl:attribute name="type">
				<xsl:choose>
					<xsl:when test="@type = 'password'">password</xsl:when>
					<xsl:otherwise>text</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:copy-of select="@maxlength | @id | @value" />
			<xsl:if test="@disabled = 'true'">
				<xsl:attribute name="disabled">disabled</xsl:attribute>
			</xsl:if>
		</input>
	</xsl:template>

	<xsl:template match="xul:textbox[@multiline = 'true']">
		<textarea id="{@id}">
			<xsl:if test="@disabled = 'true'">
				<xsl:attribute name="disabled">disabled</xsl:attribute>
			</xsl:if>
			<xsl:value-of select="@value" />
		</textarea>
	</xsl:template>

	<xsl:template match="xul:checkbox">
		<input type="checkbox" id="{@id}">
			<xsl:if test="@checked = 'true'">
				<xsl:attribute name="checked">checked</xsl:attribute>
			</xsl:if>
			<xsl:if test="@disabled = 'true'">
				<xsl:attribute name="disabled">disabled</xsl:attribute>
			</xsl:if>
		</input>
		<xsl:if test="@label">
			<label for="{@id}"><xsl:value-of select="@label" /></label>
		</xsl:if>
	</xsl:template>

	<xsl:template match="xul:radiogroup">
		<div class="radiogroup">
			<xsl:apply-templates select="." mode="box" />
		</div>
	</xsl:template>

	<xsl:template match="xul:radio">
		<xsl:variable name="radiogroupId" select="generate-id(ancestor::xul:radiogroup[1])" />
		<input type="radio" id="{@id}" name="{concat($radiogroupId, '_', @id)}">
			<xsl:if test="@selected = 'true'">
				<xsl:attribute name="checked">checked</xsl:attribute>
			</xsl:if>
			<xsl:if test="@disabled = 'true'">
				<xsl:attribute name="disabled">disabled</xsl:attribute>
			</xsl:if>
		</input>
		<xsl:if test="@label">
			<label for="{@id}"><xsl:value-of select="@label" /></label>
		</xsl:if>
	</xsl:template>

	<xsl:template match="xul:listbox[xul:listcols]">
		<table>
			<xsl:for-each select="xul:listcols/xul:listcol">
				<col />
			</xsl:for-each>
			<xsl:if test="xul:listhead">
				<thead>
					<tr>
						<xsl:for-each select="xul:listhead/xul:listheader">
							<thead><xsl:value-of select="@label" /></thead>
						</xsl:for-each>
					</tr>
				</thead>
			</xsl:if>
			<tbody>
				<xsl:for-each select="xul:listitem">
					<tr>
						<xsl:for-each select="xul:listcell">
							<td><xsl:value-of select="@label" /></td>
						</xsl:for-each>
					</tr>
				</xsl:for-each>
			</tbody>
		</table>
	</xsl:template>

	<xsl:template match="xul:listbox">
		<select id="{@id}">
			<xsl:attribute name="size">
				<xsl:choose>
					<xsl:when test="@rows"><xsl:value-of select="@rows" /></xsl:when>
					<xsl:otherwise>10</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:for-each select="xul:listitem">
				<option value="{@value}"><xsl:value-of select="@label" /></option>
			</xsl:for-each>
		</select>
	</xsl:template>


	<xsl:template match="xul:menuitem[@editable = 'true']">
		<input type="text" id="{@id}" />
		<div id="{@id}:menupopup" class="menupopup">
			<xsl:for-each select="xul:menupopup/xul:menuitem">
				<div class="menuitem">
					<xsl:value-of select="@label" />
				</div>
			</xsl:for-each>
		</div>
	</xsl:template>

	<xsl:template match="xul:menuitem">
		<select id="{@id}">
			<xsl:for-each select="xul:menupopup/xul:menuitem">
				<option>
					<xsl:attribute name="value">
						<xsl:choose>
							<xsl:when test="@value"><xsl:value-of select="@value" /></xsl:when>
							<xsl:otherwise><xsl:value-of select="@label" /></xsl:otherwise>
						</xsl:choose>
					</xsl:attribute>
					<xsl:if test="@selected = 'true'">
						<xsl:attribute name="selected">selected</xsl:attribute>
					</xsl:if>
					<xsl:value-of select="@label" />
				</option>
			</xsl:for-each>
		</select>
	</xsl:template>

</xsl:stylesheet>