<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, queries.*"%>
<!DOCTYPE HTML>
<!--
	Spatial by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
<script>
	var tab = "restaurant";
</script>
<head>
<title>City Recommandation</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
<script src="js/jquery.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/skel-layers.min.js"></script>
<script src="js/init.js"></script>
<!-- <link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="css/skel.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/style-xlarge.css" />


</head>
<body>

	<!-- Header -->
	<header id="header">
		<h1>City Recommandation</h1>
		<nav id="nav">
			<ul id="nav">
				<li id="res"><a onclick="changeTab('restaurant');">Restaurant</a></li>
				<li id="poi"><a onclick="changeTab('interests');">Points of
						Interests</a></li>
				<li id="hot"><a onclick="changeTab('hotels');">Hotels</a></li>
			</ul>
		</nav>
	</header>
	<%
		List<Business> filteredrestaurants = (List<Business>) request.getAttribute("filteredResult");
		
	%>
	<!-- Main -->

	<div class="container">
		<div id="filter-div" style="padding: 50px 50px 20px 50px">
			<table>
				<tr>
					<form id="filter_form" method="post" action="filteredrestaurant">
						<input type="hidden" name="cityName"
							value="<%=request.getAttribute("cityName")%>" />
					<td><input style="align: left;" type="text" name="filter"
						value="" placeholder="type your filter word, like 'Chinese' " /></td>
					<td><input type="submit" name="submit" value="Filter" /></td>
					</form>
				</tr>
			</table>
		</div>
		<div id="restautant-div" style="padding: 0px 50px 20px 50px">
			<table>
				<%
					for (int i = 0; i < filteredrestaurants.size(); i++) {
						Business business = filteredrestaurants.get(i);
						String name = business.getName();
						String rating = business.getRating();
						String phone = business.getPhone();
						String url = business.getUrl();
						String category = business.getCateroty();
						String review = business.getReview();
				%>
				<tr>
					<td>Name <%=name%> <br>Rating <%=rating%> <br>Phone
						<%=phone%> <br>URL <a href="<%=url%>"><%=url%></a> <br>Category
						<%=category%> <br>Review <%=review%>
					</td>
				</tr>
				<%
					}
				%>


			</table>
		</div>

	
	</div>


</body>
<script>
	function changeTab(tabName) {
		if (tabName == "restaurant") {
			$("#filter-div").show();
			$("#restautant-div").show();
			$("#interests-div").hide();
			$("#hotels-div").hide();
		} else if (tabName == "interests") {

			$("#filter-div").hide();
			$("#restautant-div").hide();
			$("#interests-div").show();
			$("#hotels-div").hide();

		} else {
			$("#filter-div").hide();
			$("#restautant-div").hide();
			$("#interests-div").hide();
			$("#hotels-div").show();
		}
	}

</script>

