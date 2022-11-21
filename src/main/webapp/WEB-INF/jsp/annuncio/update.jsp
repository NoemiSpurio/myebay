<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	 <style>
		    .error_field {
		        color: red; 
		    }
		</style>
	   
	   <title>Modifica annuncio</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			<main class="flex-shrink-0">
			  <div class="container">
			  
					<spring:hasBindErrors  name="update_annuncio_attr">
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Modifica annuncio</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form:form modelAttribute="update_annuncio_attr" method="post" action="${pageContext.request.contextPath }/annuncio/executeUpdate" novalidate="novalidate" class="row g-3">
								<input type="hidden" name="id" value="${update_annuncio_attr.id }">
							
								<div class="col-md-6">
									<label for="testoAnnuncio" class="form-label">Testo annuncio:<span class="text-danger">*</span></label>
									<spring:bind path="testoAnnuncio">
										<input type="text" name="testoAnnuncio" id="testoAnnuncio" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il testo dell'annuncio" value="${update_annuncio_attr.testoAnnuncio }" required>
									</spring:bind>
									<form:errors  path="testoAnnuncio" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="prezzo" class="form-label">Prezzo:<span class="text-danger">*</span></label>
									<spring:bind path="prezzo">
										<input type="number" name="prezzo" id="prezzo" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il prezzo" value="${update_annuncio_attr.prezzo }" required>
									</spring:bind>
									<form:errors  path="prezzo" cssClass="error_field" />
								</div>
								
								<div class="col-md-6 form-check" id="categorieDivId">
									<p style="margin-left: 30px;"><b>Categorie:</b></p>
									<form:checkboxes itemValue="id" itemLabel="codice"  element="div class='form-check'" items="${categorie_totali_attr}" path="categorieIds" />
								</div>
								<script>
									$(document).ready(function(){
										
										$("#ruoliDivId :input").each(function () {
											$(this).addClass('form-check-input'); 
										});
										$("#ruoliDivId label").each(function () {
											$(this).addClass('form-check-label'); 
										});
										
									});
								</script>
								
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma modifica</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
		
						</form:form>
  
				 		   
				    </div>
				</div>		
					  
			  </div>
			  
			</main>
			
			<jsp:include page="../footer.jsp" />
	  </body>
</html>