<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Dettagli acquisto</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Dettagli acquisto</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${show_acquisto_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Descrizione:</dt>
							  <dd class="col-sm-9">${show_acquisto_attr.descrizione}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Prezzo:</dt>
							  <dd class="col-sm-9">${show_acquisto_attr.prezzo}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data acquisto:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_acquisto_attr.data}" /></dd>
					    	</dl>
					    	
					    </div>
					    
					    <div class='card-footer'>
					        <a href="${pageContext.request.contextPath }/acquisto/list" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
					        
					    </div>
					</div>	
			  
			    
			  </div>
			  
			</main>
			
			<jsp:include page="../footer.jsp" />
	  </body>
</html>