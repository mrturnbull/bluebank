<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

  	<link href="<c:url value="/resources/css/signin.css" />" rel="stylesheet">

    <link rel="apple-touch-icon" href="/favicon.ico">
	<meta name="msapplication-TileImage" content="/favicon.ico">

    <title>Blue Bank</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body style="background-color:#4178be">
<div class="container">
	<h3 class="form-signin-heading" style="color:white">Blue Bank</h3>
	<p style="color:orange;font-size:1em" align="justify">
	Transferência entre contas Blue Bank - Conta Sacado
	<p>	
  	<form:form id="frmInput" name="frmInput" action="${pageContext.request.contextPath}/salvarOrigem" method="POST" modelAttribute="conta" class="form-signin">      
		<form:input path="agencia" id="agencia" maxlength="4" placeholder="Agencia de origem" class="form-control"/>
		<form:input path="numero" id="numero" class="form-control" 	maxlength="5" placeholder="Numero de origem"/></br>
		<form:input path="valorTransferir" id="valorTransferir" class="form-control" maxlength="10" placeholder="Valor a transferir"/></br>
		<input type="submit" class="btn btn-lg btn-success btn-block" value="Prosseguir"/>		
    </form:form></br>
</div> <!-- /container -->
</body>
</html>
