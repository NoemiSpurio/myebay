<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca utente</title>
	
    
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
			        <h5>Ricerca utenti</h5> 
			    </div>
			    <div class='card-body'>
	
						<form method="post" action="${pageContext.request.contextPath}/utente/list" class="row g-3">
						
							<div class="col-md-6">
								<label for="nome" class="form-label">Nome</label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" >
							</div>
							
							<div class="col-md-6">
								<label for="cognome" class="form-label">Cognome</label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" >
							</div>
							
							<div class="col-md-6">
								<label for="username" class="form-label">Username</label>
								<input type="text" class="form-control" name="username" id="username" placeholder="Inserire username" >
							</div>
							<div class="col-md-6">
								<label for="dateCreated" class="form-label">Data di Creazione</label>
                        		<input class="form-control" id="dateCreated" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dateCreated" >
							</div>
							
							<div class="col-md-3">
								<label for="stato" class="form-label">Stato</label>
								    <select class="form-select " id="stato" name="stato" >
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="ATTIVO" >Attivo</option>
								    	<option value="CREATO">Creato</option>
								      	<option value="DISABILITATO" >Disabilitato</option>
							    	</select>
							</div>
							
							<div class="col-12">	
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								<a class="btn btn-outline-primary ml-2" href="${pageContext.request.contextPath}/utente/insert">Inserisci nuovo utente</a>
							</div>
	
							
						</form>	   
			    </div>
			</div>	
	
		</div>
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>