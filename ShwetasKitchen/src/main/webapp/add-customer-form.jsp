<!DOCTYPE html>
<html>

<head>
	<title>Add Customer</title>

	<link type="text/css" rel="stylesheet" href="style/style.css">
	<link type="text/css" rel="stylesheet" href="style/add-customer-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Customer Data</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add Customer</h3>
		
		<form action="FoodControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName" /></td>
					</tr>

					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" /></td>
					</tr>

					<tr>
						<td><label>Address:</label></td>
						<td><input type="text" name="address" /></td>
					</tr>
					<tr>
						<td><label>State:</label></td>
						<td><input type="text" name="state" /></td>
					</tr>
					<tr>
						<td><label>Postal code:</label></td>
						<td><input type="text" name="postalCode" /></td>
					</tr>
					<tr>
						<td><label>Comment:</label></td>
						<td><input type="text" name="comment" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="CustomerControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











