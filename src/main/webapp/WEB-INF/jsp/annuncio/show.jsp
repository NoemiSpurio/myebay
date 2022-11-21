<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<jsp:include page="../header.jsp" />
	<title>Visualizza annuncio</title>
	
</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp" />
	
	<main class="flex-shrink-0">
	  	<div class="container">
			
			<div class='card'>
			    <div class='card-header'>
			        Dettaglio annuncio
			    </div>
			
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Descrizione Annuncio:</dt>
					  <dd class="col-sm-9">${show_annuncio_attr.testoAnnuncio}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Prezzo:</dt>
					  <dd class="col-sm-9">${show_annuncio_attr.prezzo}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Aperto:</dt>
					  <dd class="col-sm-9">${show_annuncio_attr.aperto}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Pubblicazione:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_annuncio_attr.data}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Nome Venditore:</dt>
					  <dd class="col-sm-9">${show_annuncio_attr.utenteInserimento.nome}</dd>
			    	</dl>
			    	
			    	<dl class="row">
			    		<dt class="col-sm-3 text-right">Categorie:</dt>
			    		<c:forEach items="${show_annuncio_attr.categorie}" var="categorieItem">
			    			<dd class="col-sm-9">${categorieItem.codice} </dd>
			    		</c:forEach>
			    	</dl>
			    </div>
			    
			    
			    
			    <div class='card-footer'>
			       <a href="${pageContext.request.contextPath}/annuncio/list" class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i>Back
			       </a>
			       <p>
				       <sec:authorize access="isAuthenticated()">
					      <form action="${pageContext.request.contextPath}/annuncio/compra" method="post">
						    	<input type="hidden" name="idAnnuncio" value="${show_annuncio_attr.id}">
						    	<button type="submit" name="submit" id="submit" class="btn btn-primary">Compra</button>
						   </form>
				       </sec:authorize>
				       <sec:authorize access="!isAuthenticated()">
				       		<a href="${pageContext.request.contextPath}/login" class='btn btn-outline-primary' style='width:90px'>
					            <i class='fa fa-chevron-left'></i>Compra
					       </a>
				       </sec:authorize>
			       </p>
			    </div>
			</div>	
		</div>
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>