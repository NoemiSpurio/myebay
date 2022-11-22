<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary" aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
          </li>
          <sec:authorize access="isAuthenticated()">
	          <li class="nav-item dropdown">
	            <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Gestione account</a>
	            <ul class="dropdown-menu" aria-labelledby="dropdown07">
	              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/acquisto/search">Acquisti effettuati</a></li>
	              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/annuncio/mysearch">Gestione annunci</a></li>
	            </ul> 
	          </li>
          </sec:authorize>
           <sec:authorize access="hasRole('ADMIN')">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestione Utenze</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/utente/search">Cerca utenti</a>
		        </div>
		      </li>
		   </sec:authorize>
        </ul>
      </div>
      <sec:authorize access="isAuthenticated()">
      	<p class="navbar-text">Utente: <sec:authentication property="name"/> (${userInfo.nome } ${userInfo.cognome })
      	<div class="nav-item dropdown">
	            <a class="nav-link dropdown-toggle text-light" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Il mio account</a>
	            <ul class="dropdown-menu" aria-labelledby="dropdown07">
	              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a></li>
	              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/account/changePwd">Cambia password</a></li>
	            </ul> 
	    </div>
      </sec:authorize>
      <sec:authorize access="!isAuthenticated()">
	      <a  class="btn btn-primary" href="${pageContext.request.contextPath}/login">Login</a>
      </sec:authorize>
    </div>
  </nav>
  
  
</header>