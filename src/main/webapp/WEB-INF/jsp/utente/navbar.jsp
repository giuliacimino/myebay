<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
  <!-- Fixed navbar -->
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary" aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/public/annuncio/search">Ricerca Annunci</a>
          </li>
          <li class="nav-item">
          </li>
          
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Dropdown</a>
            <ul class="dropdown-menu" aria-labelledby="dropdown07">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/utente/annuncioprotetto/annunciutente/${utenteInPagina}">I tuoi annunci</a></li>
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/utente/acquisto/acquistiutente/${utenteInPagina}">I tuoi acquisti</a></li>
           
            </ul> 
          </li>
           <sec:authorize access="hasRole('ADMIN')">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestione Utenze</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/utente/search">Ricerca Utenti</a>
		        </div>
		      </li>
		   </sec:authorize>
        </ul>
      </div>
      
      <sec:authorize access="isAuthenticated()" var="isAutenticato"></sec:authorize>
      <c:choose>
   		<c:when test="${isAutenticato}"><div class="col-md text-end">   
	         <p class="navbar-text">Utente: ${userInfo.username}(${userInfo.nome} ${userInfo.cognome})
    	 <a href="${pageContext.request.contextPath}/executeLogout">Logout</a></p>
    	 </div></c:when>
		   <c:otherwise><div class="col-md text-end">  <h6 class="navbar-text">
		    	 <a href="${pageContext.request.contextPath}/login">Login</a></h6>
		    	 </div>
			</c:otherwise>
		</c:choose>
      
       
		    
	    	
    
    </div>
  </nav>
  
  
</header>