<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/views/include/header.jsp" />
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="content table-responsive table-full-width">
                        <table class="table table-hover table-striped">
                            <thead>
                                <th>ID</th>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>Gender</th>
                                <th>Age</th>
                            </thead>
                            <tbody>
                                <c:forEach items="${candidates}" var="c">
                                    <tr>
                                        <td>${ c.candidate }</td>
                                        <td>${ c.firstname }</td>
                                        <td>${ c.lastname }</td>
                                        <td>
                                        	<c:if test="${ c.gender == 0 }">Female</c:if>
                                        	<c:if test="${ c.gender == 1 }">Male</c:if>
                                       	</td>
                                        <td>${ c.age }</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="footer card-footer">
                        <a href="saveCandidate.action">Save candidate</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/views/include/footer.jsp" />