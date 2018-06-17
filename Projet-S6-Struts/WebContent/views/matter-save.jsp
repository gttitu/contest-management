<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/views/include/header.jsp" />
<div class="content">
<div class="container-fluid">
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="content">
                        <form action="insertMatter" method="POST">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Contest</label>
                                        <select class="form-control" name="contest">
                                            <c:forEach items="${contests}" var="c">
                                                <option value="${c.id}">${c.description}</option>
                                             </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <input type="text" class="form-control" name="description" placeholder="Description"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Coefficient</label>
                                        <input type="number" class="form-control" name="coefficient" placeholder="Coefficient" min="0" step="1"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Average</label>
                                        <input type="number" class="form-control" name="average" placeholder="Average" min="0"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Date and time begin</label>
                                       	<input type="date" class="form-control" name="dateBegin"/>
                                       	<input type="time" class="form-control" name="timeBegin"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Date and time end</label>
                                       	<input type="date" class="form-control" name="dateEnd"/>
                                       	<input type="time" class="form-control" name="timeEnd"/>
                                    </div>
                                </div>
                            </div>

                            <input type="submit" class="btn btn-info btn-fill pull-right" value="Validate" name="submit">
                            <div class="clearfix"></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/views/include/footer.jsp" />