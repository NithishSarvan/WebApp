
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>


<div class="container">

	<h1>Enter Your Todo Details</h1>

	<form:form method="post" modelAttribute="todo">

			<fieldset class="mb-3">
				<form:label path="course">Course</form:label>
				<form:input type="text" path="course" required="required" />
				<form:errors path="course" cssClass="texr-warning" />
			</fieldset>
	
			<fieldset class="mb-3">
				<form:label path="date">Date</form:label>
				<form:input type="text" path="date" required="required" />
				<form:errors path="date" cssClass="texr-warning" />
			</fieldset>
	
			<form:input type="hidden" path="id" />
			<form:input type="hidden" path="done" />
	
			<input type="submit" class="btn btn-success" />

	</form:form>

</div>

<%@ include file="common/footer.jspf"%>

		 <script type="text/javascript">
			$('#date').datepicker({
				format : 'yyyy-mm-dd'
			});
		</script>
		
		
		
		
		