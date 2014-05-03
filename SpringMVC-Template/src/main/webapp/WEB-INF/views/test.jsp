<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Test page</title>
</head>
<body>
<h1>
	${message} 	
</h1>
PropFile1_PropVal1: ${PropFile1_PropVal1}<BR />
PropFile1_PropVal2: ${PropFile1_PropVal2}<BR />
PropFile2_PropVal1: ${PropFile2_PropVal1}<BR />
PropFile2_PropVal2: ${PropFile2_PropVal2}<BR />

injectedInHomeController: ${injectedInHomeControllerModelAttr}<BR />

</body>
</html>
