<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<html>
	<head>
		<meta charset="utf-8"></meta>
		<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></link>
	</head>
	<body>
		<h2>A Movie Collection</h2>
		<table class="table table-bordered">
			<xsl:for-each select="collection/movie">
			<xsl:choose>
				<xsl:when test="genre='Comedie'">
					<tr class="success">
						<td><xsl:value-of select="title"/></td>
						<td><xsl:value-of select="actors"/></td>
	       	              <td><xsl:value-of select="genre"/></td>
	                      <td><xsl:value-of select="dateofplaying"/></td>
						  <td><xsl:value-of select="duration"/></td>
					</tr>
				</xsl:when>
				<xsl:when test="genre='Crimă'">
					<tr class="active">
						<td><xsl:value-of select="title"/></td>
						<td><xsl:value-of select="actors"/></td>
	       	              <td><xsl:value-of select="genre"/></td>
	                      <td><xsl:value-of select="dateofplaying"/></td>
						  <td><xsl:value-of select="duration"/></td>
					</tr>
				</xsl:when>
				<xsl:when test="genre='Acțiune'">
					<tr class="info">
						<td><xsl:value-of select="title"/></td>
						<td><xsl:value-of select="actors"/></td>
	       	              <td><xsl:value-of select="genre"/></td>
	                      <td><xsl:value-of select="dateofplaying"/></td>
						  <td><xsl:value-of select="duration"/></td>
					</tr>
				</xsl:when>
				<xsl:when test="genre='Aventură'">
					<tr class="warning">
						<td><xsl:value-of select="title"/></td>
						<td><xsl:value-of select="actors"/></td>
	       	              <td><xsl:value-of select="genre"/></td>
	                      <td><xsl:value-of select="dateofplaying"/></td>
						  <td><xsl:value-of select="duration"/></td>
					</tr>
				</xsl:when>
				<xsl:when test="genre='Horror'">
					<tr class="danger">
						<td><xsl:value-of select="title"/></td>
						<td><xsl:value-of select="actors"/></td>
	       	              <td><xsl:value-of select="genre"/></td>
	                      <td><xsl:value-of select="dateofplaying"/></td>
						  <td><xsl:value-of select="duration"/></td>
					</tr>
				</xsl:when>
				
			</xsl:choose>
			</xsl:for-each>
		</table>
	</body>
</html>

</xsl:template>
</xsl:stylesheet>
