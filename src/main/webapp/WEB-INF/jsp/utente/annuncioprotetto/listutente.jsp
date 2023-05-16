<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="it" class="h-100">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei risultati</title>
	
</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
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
			        <h5>Lista dei risultati</h5> 
			    </div>
			    <div class='card-body'>
			    	<a href="${pageContext.request.contextPath}/secured/home" class='btn btn-outline-secondary' >
				            <i class='fa fa-chevron-left'></i> Torna alla homepage
				        </a>
			    
			        <div class='table-responsive'>
			            <table class='table table-striped ' >
			                <thead>
			                    <tr>
			                        <th>Testo Annuncio</th>
			                        <th>Prezzo</th>
			                        <th>Data Inserimento</th>
			                        <th>Azioni</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<c:forEach items="${annuncioUtente_list_attr }" var="annuncioItem">
										<tr>
											<td>${annuncioItem.testoAnnuncio }</td>
											<td>${annuncioItem.prezzo } $</td>
											<td>
												<fmt:parseDate value="${annuncioItem.data}" pattern="yyyy-MM-dd" var="localDateToBeParsed" type="date"/>
												<fmt:formatDate pattern="dd/MM/yyyy" value="${localDateToBeParsed}" />
											</td>
											<td>
												<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/utente/annuncioprotetto/show/${annuncioItem.id }">Visualizza</a>
												<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/utente/annuncioprotetto/show/${annuncioItem.id }">Modifica</a>
												<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/utente/annuncioprotetto/delete/${annuncioItem.id }">Elimina</a>
											</td>
											
											
										</tr>
									</c:forEach>
			                </tbody>
			            </table>
			        </div>
			   
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
	
	
	
	
</body>
</html>