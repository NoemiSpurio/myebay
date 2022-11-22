<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 	<jsp:include page="../header.jsp" />
	 	 <style>
		    .error_field {
		        color: red; 
		    }
		</style>
	   
	   <title>Modifica password</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			<main class="flex-shrink-0">
			  <div class="container">
			  
					<spring:hasBindErrors  name="insert_utente_attr">
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
				        <h5>Modifica la tua password</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form:form modelAttribute="utente_cambia_pwd" method="post" action="executeChangePwd" novalidate="novalidate" class="row g-3">
					
							
								<div class="col-md-6">
									<label for="vecchiaPassword" class="form-label">Vecchia password<span class="text-danger">*</span></label>
									<spring:bind path="vecchiaPassword">
										<input type="text" name="vecchiaPassword" id="vecchiaPassword" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la vecchia password" required>
									</spring:bind>
									<form:errors  path="vecchiaPassword" cssClass="error_field" />
								</div>
								 
								<div class="col-md-3">
									<label for="password" class="form-label">Password<span class="text-danger">*</span></label>
									<spring:bind path="password">
										<input type="password" class="form-control ${status.error ? 'is-invalid' : ''}" name="password" id="password" placeholder="Inserire Password"  required>
									</spring:bind>
									<form:errors  path="password" cssClass="error_field" />
								</div>
								
								<div class="col-md-3">
									<label for="confermaPassword" class="form-label">Conferma Password<span class="text-danger">*</span></label>
									<spring:bind path="confermaPassword">
										<input type="password" class="form-control ${status.error ? 'is-invalid' : ''}" name="confermaPassword" id="confermaPassword" placeholder="Confermare Password"  required>
									</spring:bind>
									<form:errors  path="confermaPassword" cssClass="error_field" />
								</div>								
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
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