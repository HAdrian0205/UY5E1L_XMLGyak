<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/UY5E1L_kurzusfelvetel">
        <html>
            <head>
                <style>
                    table {
                        border-collapse: collapse;
                        width: 100%;
                    }
                    th, td {
                        border: 1px solid black;
                        padding: 8px;
                        text-align: left;
                    }
                </style>
            </head>
            <body>
                <h2>Kurzusfelvétel</h2>
                <h3>Hallgató adatai</h3>
                <xsl:for-each select="hallgato">
                    <p><strong>Név:</strong> <xsl:value-of select="hnev"/></p>
                    <p><strong>Születési év:</strong> <xsl:value-of select="szulev"/></p>
                    <p><strong>Szak:</strong> <xsl:value-of select="szak"/></p>
                </xsl:for-each>
                <h3>Kurzusok</h3>
                <table>
                    <tr>
                        <th>Kurzusnév</th>
                        <th>Kredit</th>
                        <th>Hely</th>
                        <th>Időpont</th>
                        <th>Oktató</th>
                    </tr>
                    <xsl:for-each select="kurzusok/kurzus">
                        <tr>
                            <td><xsl:value-of select="kurzusnev"/></td>
                            <td><xsl:value-of select="kredit"/></td>
                            <td><xsl:value-of select="hely"/></td>
                            <td><xsl:value-of select="idopont"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
