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
			       <div>
				       <sec:authorize access="isAuthenticated()">						   
						  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#confirmOperationModal">
					  Compra
					</button>
				       </sec:authorize>
				       
				       <sec:authorize access="!isAuthenticated()">
					       <form action="${pageContext.request.contextPath}/login" method="post">
						    	<input type="hidden" name="idAnnuncio" value="${show_annuncio_attr.id}">
						    	<button type="submit" name="submit" id="submit" class="btn btn-primary">Compra</button>
						   </form>
				       </sec:authorize>
			       </div>
			    </div>
			</div>	
		</div>
		
	</main>
	<jsp:include page="../footer.jsp" />
	
	<div class="modal fade" id="confirmOperationModal" tabindex="-1"  aria-labelledby="confirmOperationModalLabel"
	    aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel">Conferma Aquisto</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                Continuare con l'aquisto?
	            </div>
	            <form method="post" action="${pageContext.request.contextPath}/annuncio/compra" >
		            <div class="modal-footer">
		                <button type="button" class="btn btn-secondary modal" data-bs-dismiss="modal">Chiudi</button>
		            	<input type="hidden" name="idAnnuncio" id="idAnnuncio" value="${show_annuncio_attr.id}">
		            	<input type="hidden" name="idUtente" id="idUtente" value="${userinfo.id}">
		                <input type="submit" value="Continua"  class="btn btn-primary">
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
	<!-- end Modal -->
	<script type="text/javascript">
		
	</script>
	
</body>
</html>