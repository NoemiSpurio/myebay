<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 	<jsp:include page="../header.jsp" />
	   	<title>Annunci</title>
	 </head>
	 
	<body class="d-flex flex-column h-100">
	 
		<jsp:include page="../navbar.jsp"></jsp:include>
	
		<main class="flex-shrink-0">
		  <div class="container">
		  
		  		<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
				  ${successMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
				  ${errorMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
		  
		  
		  
		  		<div class='card'>
				    <div class='card-header'>
				        <h5>Elenco dei miei annunci</h5> 
				    </div>
				    <div class='card-body'>
				    	<sec:authorize access="isAuthenticated()">
				    		<a class="btn btn-primary " href="${pageContext.request.contextPath}/annuncio/insert">Aggiungi annuncio</a>
				    	</sec:authorize>
				    	
				        <div class='table-responsive'>
				            <table class='table table-striped ' >
				                <thead>
				                    <tr>
			                         	<th>Testo</th>
				                        <th>Prezzo</th>
				                        <th>Aperto</th>
				                        <th>Azioni</th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<c:forEach items="${annunci_list_attribute}" var="annuncioItem">
										<tr>
											<td>${annuncioItem.testoAnnuncio}</td>
											<td>${annuncioItem.prezzo}</td>
											<td>${annuncioItem.aperto}</td>
											<td>
												<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/annuncio/show/${annuncioItem.id}">Dettagli</a>
												<c:if test="${annuncioItem.aperto}">
													<a class="btn  btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/annuncio/delete/${annuncioItem.id}">Rimuovi</a>
													<a class="btn  btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/annuncio/update/${annuncioItem.id}">Modifica</a>
												</c:if>
											</td>
										</tr>
									</c:forEach>
				                </tbody>
				            </table>
				        </div>
				   		   
			    </div>
			</div>	
		  </div>
		  
		</main>
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>