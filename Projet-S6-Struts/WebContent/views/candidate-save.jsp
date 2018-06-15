<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="/views/include/header.jsp" />
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="content">
                        <form action="insertCandidate.action" method="post">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Center</label>
                                        <select class="form-control" name="candidate.center">
                                            <s:iterator value="centers">
                                                <option value="<s:property value="id"/>"><s:property value="description"/> ( <s:property value="location"/> )</option>
                                            </s:iterator>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Firstname</label>
                                        <input type="text" class="form-control" name="detail.firstname" placeholder="Firstname"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Lastname</label>
                                        <input type="text" class="form-control" name="detail.lastname" placeholder="Lastname"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Age</label>
                                        <input type="number" class="form-control" name="detail.age" placeholder="Age" min="0" step="1"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Gender</label>
                                        <select class="form-control" name="detail.gender">
                                            <option value="1">Male</option>
                                            <option value="1">Female</option>
                                        </select>
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