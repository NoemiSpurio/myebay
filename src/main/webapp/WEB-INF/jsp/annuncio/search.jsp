<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Ricerca annuncio</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Ricerca tra gli annunci</h5> 
				    </div>
				    <div class='card-body'>
		
							<form method="post" action="${pageContext.request.contextPath}/annuncio/list" class="row g-3" >
							
							
								<div class="col-md-6">
									<label for="testoAnnuncio" class="form-label">Testo dell'annuncio</label>
									<input type="text" name="testoAnnuncio" id="testoAnnuncio" class="form-control" placeholder="Inserire il testo dell'annuncio da cercare"  >
								</div>
								
								<div class="col-md-6">
									<label for="prezzo" class="form-label">Prezzo (a partire da)</label>
									<input type="number" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire il prezzo minimo" >
								</div>
								
								<div class="col-12">
									<input type="hidden" id="aperto" name="aperto" value="true">
									
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca</button>
									
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
		
						</form>
  
				   		   
				    </div>
				</div>		
					  
			  </div>
			  
			</main>
			
			<jsp:include page="../footer.jsp" />
	  </body>
</html>