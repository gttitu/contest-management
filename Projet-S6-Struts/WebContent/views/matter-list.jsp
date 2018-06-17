<%@taglib uri="/struts-tags" prefix="s" %>
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
								<th>Contest</th>
								<th>Description</th>
								<th>Coefficient</th>
								<th>Average</th>
								<th>Date and time begin</th>
								<th>Date and time end</th>
							 </thead>
							 <tbody>
								<s:iterator value="matters">
									<tr>
										<td><s:property value="id"/></td>
										<td><s:property value="contests.get(contest).getDescription()"/></td>
										<td><s:property value="description"/></td>
										<td><s:property value="coefficient"/></td>
										<td><s:property value="average"/></td>
										<td><s:property value="datetimeBegin"/></td>
										<td><s:property value="datetimeEnd"/></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
			                        
                    </div>
                    
                    <div class="footer card-footer">
                        <a href="insertMatter">Save matter</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/views/include/footer.jsp" />