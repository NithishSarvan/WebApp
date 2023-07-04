
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">
<title>Update Todo Page</title>


</head>
<style>
.btn-warning {
	background-color: red;
}

.btn-1 {
	background-color: green !important;
	color: #ffffff !important;
}

.summa {
    display: flex;
    padding-top: 10px;
    gap: 20px;
}
</style>
<body>
	<div class="container">
		<div>Welcome ${name}</div>
		<hr>
		<h1>Update Todos</h1>

		<div class="container">
			<div class="row">
				<div class="col-md-5 mb-3">
				<form method="post">
				<label for="exampleFormControlInput1" class="form-label">
						Description</label> <input type="text" class="form-control" required>

					<div class="summa">
						<div class="form-check">
							<input class="form-check-input" type="radio"
								name="flexRadioDefault" > <label
								class="form-check-label" > True </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio"
								name="flexRadioDefault" > 
								<label
								class="form-check-label" > False </label>
						</div>
					</div>
					
					<button type="submit" class="btn btn-primary mt-5">Save</button>
				</form>
				</div>
			</div>
		</div>

	</div>
	<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>

</body>
</html>