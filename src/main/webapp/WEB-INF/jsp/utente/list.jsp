<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<jsp:include page="../header.jsp" />
	<title>Lista utenti</title>
	
</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp" />
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
			        <h5>Lista degli utenti</h5> 
			    </div>
			    <div class='card-body'>
			    	<a class="btn btn-primary " href="${pageContext.request.contextPath}/utente/insert">Aggiungi utente</a>
			    	<a href="${pageContext.request.contextPath}/utente/search" class='btn btn-outline-secondary' >
				            <i class='fa fa-chevron-left'></i>Torna alla Ricerca
				        </a>
			    
			        <div class='table-responsive'>
			            <table class='table table-striped ' >
			                <thead>
			                    <tr>
			                        <th>Nome</th>
			                        <th>Cognome</th>
			                        <th>Username</th>
			                        <th>Stato</th>
			                        <th>Azioni</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<c:forEach items="${utente_list_attribute}" var="utenteItem">
									<tr>
										<td>${utenteItem.nome}</td>
										<td>${utenteItem.cognome}</td>
										<td>${utenteItem.username}</td>
										<td>${utenteItem.stato}</td>
										<td>
											<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/utente/show/${utenteItem.id}">Dettagli</a>
											<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/utente/edit/${utenteItem.id}">Modifica</a>
											<a id="resetPwdLink_#_${utenteItem.id}" class="btn  btn-sm btn-outline-warning ml-2 mr-2 link-for-modal2" data-bs-toggle="modal" data-bs-target="#confirmResetPwdModal">Reset password</a>
											<a id="changeStatoLink_#_${utenteItem.id }" class="btn btn-outline-${utenteItem.isAttivo()?'danger':'success'} btn-sm link-for-modal" data-bs-toggle="modal" data-bs-target="#confirmOperationModal"  >${utenteItem.isAttivo()?'Disabilita':'Abilita'}</a>
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
	
	
	<div class="modal fade" id="confirmOperationModal" tabindex="-1"  aria-labelledby="confirmOperationModalLabel"
	    aria-hidden="true">
	    <div class="modal-dialog" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel">Conferma modifica</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                Sei sicuro di voler cambiare lo stato all'utente?
	            </div>
	            <form method="post" action="${pageContext.request.contextPath}/utente/cambiaStato" >
		            <div class="modal-footer">
		            	<input type="hidden" name="idUtenteForChangingStato" id="idUtenteForChangingStato">
		                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
		                <input type="submit" value="Continua"  class="btn btn-primary">
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
	<script type="text/javascript">
		$(".link-for-modal").click(function(){
			var callerId = $(this).attr('id').substring(18);
			$('#idUtenteForChangingStato').val(callerId);
		});
	</script>
	
	<div class="modal fade" id="confirmResetPwdModal" tabindex="-1"  aria-labelledby="confirmResetPwdModalLabel"
	    aria-hidden="true">
	    <div class="modal-dialog" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmResetPwdModalLabel">Conferma reset password</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                Sei sicuro di voler resettare la password all'utente?
	            </div>
	            <form method="post" action="${pageContext.request.contextPath}/utente/resetPwd" >
		            <div class="modal-footer">
		            	<input type="hidden" name="idUtenteForResetPwd" id="idUtenteForResetPwd">
		                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
		                <input type="submit" value="Continua"  class="btn btn-primary">
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
	<script type="text/javascript">
		$(".link-for-modal2").click(function(){
			var callerId = $(this).attr('id').substring(15);
			$('#idUtenteForResetPwd').val(callerId);
		});
	</script>
	
</body>
</html>