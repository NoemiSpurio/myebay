<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Cerca acquisti</title>
	
    
</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Cerca tra i tuoi acquisti</h5> 
			    </div>
			    <div class='card-body'>
	
						<form method="post" action="${pageContext.request.contextPath}/acquisto/list" class="row g-3">
						
							<div class="col-md-6">
								<label for="descrizione" class="form-label">Descrizione:</label>
								<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione dell'acquisto" >
							</div>
							
							<div class="col-md-6">
									<label for="prezzo" class="form-label">Prezzo (a partire da):</label>
									<input type="number" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire il prezzo minimo" >
							</div>
							
							<div class="col-md-6">
								<label for="data" class="form-label">Data acquisto:</label>
                        		<input class="form-control" id="data" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="data" >
							</div>
							
							<div class="col-12">	
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
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