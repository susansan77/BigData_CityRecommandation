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
		<h1 ><a href="index.jsp">City Recommandation</a></h1>
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
		List<Business> restaurants = (List<Business>) request.getAttribute("city_restaurant");
		List<Business> interests = (List<Business>) request.getAttribute("city_interest");
		List<Business> hotels = (List<Business>) request.getAttribute("city_hotels");
	%>
	<!-- Main -->

	<div class="container">
		<div id="filter-div" style="padding: 50px 50px 20px 50px ;">
			<table>
				<tr>
					<form id="filter_form" >
						<input type="hidden" id="cityName"
							value="<%=request.getAttribute("city_name")%>" />
						<td><input style="align: left;" type="text" id="filter"
							value="" placeholder="type your filter word, like'Chinese' " /></td>
						<td><input type="submit" name="submit" value="Filter" /></td>
					</form>
				</tr>
			</table>
		</div>
		<div id="filtered-result" style="padding: 0px 50px 20px 50px; display:none">

		</div>
		<div id="restautant-div" style="padding: 0px 50px 20px 50px">
			<table>
				<%
					for (int i = 0; i < restaurants.size(); i++) {
						Business business = restaurants.get(i);
						String name = business.getName();
						String rating = business.getRating();
						String phone = business.getPhone();
						String url = business.getUrl();
						String category = business.getCateroty();
						String review = business.getReview();
				%>
				<tr>
					<td>Name: <%=name%> <br>Rating: <%=rating%> <br>Phone:
						<%=phone%> <br>URL: <a href="<%=url%>"><%=url%></a> <br>Category:
						<%=category%> <br>Review: <%=review%>
					</td>
				</tr>
				<%
					}
				%>


			</table>
		</div>

		<div id="interests-div"
			style="padding: 0px 50px 20px 50px; display: none">
			<table>
				<%
					for (int i = 0; i < interests.size(); i++) {
						Business business = interests.get(i);
						String name = business.getName();
						String rating = business.getRating();
						String phone = business.getPhone();
						String url = business.getUrl();
						//String category = business.getCateroty();
						String review = business.getReview();
				%>
				<tr>
					<td>Name: <%=name%> <br>Rating: <%=rating%> <br>Phone:
						<%=phone%> <br>URL: <a href="<%=url%>"><%=url%></a> <br>Review:
						<%=review%>
					</td>
				</tr>
				<%
					}
				%>


			</table>
		</div>
		<div id="hotels-div"
			style="padding: 0px 50px 20px 50px; display: none">
			<table>
				<%
					for (int i = 0; i < hotels.size(); i++) {
						Business business = hotels.get(i);
						String name = business.getName();
						String rating = business.getRating();
						String phone = business.getPhone();
						String url = business.getUrl();
						//String category = business.getCateroty();
						String review = business.getReview();
				%>
				<tr>
					<td>Name: <%=name%> <br>Rating: <%=rating%> <br>Phone:
						<%=phone%> <br>URL: <a href="<%=url%>"><%=url%></a> <br>Review:
						<%=review%>
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
			$("#filtered-result").hide();
		} else if (tabName == "interests") {

			$("#filter-div").hide();
			$("#restautant-div").hide();
			$("#interests-div").show();
			$("#hotels-div").hide();
			$("#filtered-result").hide();

		} else {
			$("#filter-div").hide();
			$("#restautant-div").hide();
			$("#interests-div").hide();
			$("#hotels-div").show();
			$("#filtered-result").hide();
		}
	}
</script>
<script>
	$(document).ready(function() {
		$('#filter_form').submit(function(e) {
			e.preventDefault();
			var cityName = $("#cityName").val();
			var filter = $("#filter").val();
			$.get('details', {
                // Parameter to be sent to server side
                cityName : cityName,
                filter : filter
        }, function(jsonResponse) {
	        	$("#filter-div").show();
	        	$("#filtered-result").show();
				$("#restautant-div").hide();
				$("#interests-div").hide();
				$("#hotels-div").hide();
                $("#filtered-result").html("");
                $("#filtered-result").append("<table>");
                for (var i = 0; i < jsonResponse.length; i++) {
                	if (i%2 == 0) {
                		$("#filtered-result").append("<tr style='background-color:#f0f0f5'><td>Name: " + jsonResponse[i].name +  
								"<br>Rating: " + jsonResponse[i].rating + 
								"<br>Phone: " + jsonResponse[i].phone + 
								"<br>URL: <a href='" + jsonResponse[i].url + "'>" + 
								 jsonResponse[i].url + 
								 "</a> <br>Category: " + jsonResponse[i].category + 
								 "<br>Review: " + jsonResponse[i].review + 
								"</td></tr>");
                	} else {
                		$("#filtered-result").append("<tr><td>Name: " + jsonResponse[i].name +  
								"<br>Rating: " + jsonResponse[i].rating + 
								"<br>Phone: " + jsonResponse[i].phone + 
								"<br>URL: <a href='" + jsonResponse[i].url + "'>" + 
								 jsonResponse[i].url + 
								 "</a> <br>Category: " + jsonResponse[i].category + 
								 "<br>Review: " + jsonResponse[i].review + 
								"</td></tr>");
                	}
                	
                }
                $("#filtered-result").append("</table>");
        });
		});
	});

</script>

</html>