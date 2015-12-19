<!DOCTYPE HTML>
<!--
	Spatial by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
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

<!-- 
		<noscript> -->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/skel.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/style-xlarge.css" />

</head>
<body class="landing">
	<header id="header" class="alt">
		<h1><a href="index.jsp">City Recommandation</a></h1>

	</header>

	<!-- Banner -->
	<section id="banner">
		<section id="main" class="wraper">

			<div class="container" style="padding: 50px 250px 50px 250px;">
				<h2>Custormize</h2>

				<div class="table">
					<table style="border-collapse: collapse">
						<tbody>
							<tr id="budget">
								<td><h3 style="color: white">Budget</h3>
								<td style="color: white">Cost
									<div class="btn-group">
										<button id="budget-id" type="button"
											class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											Select <span class="caret"></span>
										</button>
										<ul id="Budget-Options" class="dropdown-menu">
											<li><a>&lt;100</a></li>
											<li><a>&lt;150</a></li>
											<li><a>&lt;200</a></li>
											<li><a>&lt;300</a></li>
										</ul>
									</div>
								</td>
							</tr>
							<script>
								$("#Budget-Options li")
										.click(
												function() {
													document
															.getElementById("budget-id").innerHTML = $(
															this).text();
													 $("#budget_id").val(
															$(this).text()); 
												});
							</script>
							<tr id="weather">
								<td><h3 style="color: white">Weather</h3>
								<td style="color: white">Month
									<div class="btn-group">
										<button id="weather_month_tag" type="button"
											class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											Select <span class="caret"></span>
										</button>
										<ul id="Weather-Month-Options" class="dropdown-menu">
											<li><a>Janurary</a></li>
											<li><a>Feburary</a></li>
											<li><a>March</a></li>
											<li><a>April</a></li>

											<li><a>May</a></li>
											<li><a>June</a></li>
											<li><a>July</a></li>
											<li><a>August</a></li>

											<li><a>September</a></li>
											<li><a>October</a></li>
											<li><a>November</a></li>
											<li><a>December</a></li>
										</ul>
									</div>
								</td>
								<script>
									$("#Weather-Month-Options li")
											.click(
													function() {
														document
																.getElementById("weather_month_tag").innerHTML = $(
																this).text();
														 $("#month_id").val(
																	$(this).text()); 

													});
								</script>
								<td
									style="color: white; text-align: right; margin: 0px 100px 0px 0px;">Temperature
									<div class="btn-group">
										<button id="weather_temp_tag" type="button"
											class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											Select <span class="caret"></span>
										</button>
										<ul id="Weather-Tempreture-Options" class="dropdown-menu">
											<li><a>Hot(80~100)</a></li>
											<li><a>Warm(60~80)</a></li>
											<li><a>Cool(45~60)</a></li>
											<li><a>Cold(&lt;45)</a></li>

										</ul>
									</div>
								</td>
								<script>
									$("#Weather-Tempreture-Options li")
											.click(
													function() {
														document
																.getElementById("weather_temp_tag").innerHTML = $(
																this).text();
														 $("#temp_id").val(
																$(this).text());  
													});
								</script>
							</tr>

							<tr >
								<td><h3 style="color: white">Distance</h3>
								<td nowrap style="color: white">Depart in
									<div class="btn-group">
										<button id="distance_city_tag" type="button"
											class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											Select <span class="caret"></span>
										</button>
										<ul id="Distance-Depart-Options" class="dropdown-menu">
											<li><a>Worcester</a></li>
											<li><a>Boston</a></li>

										</ul>
									</div>
								</td>
								<script>
									$("#Distance-Depart-Options li")
											.click(
													function() {
														document
																.getElementById("distance_city_tag").innerHTML = $(
																this).text();
														 $("#city_id").val(
																$(this).text()); 
													});
								</script>
								<td style="color: white">
									<div class="input-group">
										<input id="radius" type="text" class="form-control"
											placeholder="number of units" aria-describedby="basic-addon2">
										<span class="input-group-addon" id="basic-addon2">X 50
											miles</span>
									</div>

								</td>
								<script>
									$("#radius").on('change',
													function() {
														
														 $("#radius_id").val(
																$(this).val()); 
													});
								</script>

							</tr>

						</tbody>

					</table>
				</div>
				<form method="post" action="customer">
					<ul class="actions">
						<input type="hidden" name="restaurantSelection" value="<%=request.getAttribute("restaurantSelection") %>">
						<input type="hidden" name="pointsOfInterestsSelection" value="<%=request.getAttribute("pointsOfInterestsSelection") %>">
						<input type="hidden" name="hotelsSelection" value="<%=request.getAttribute("hotelsSelection") %>">
						<input id="budget_id" type="hidden" name="budget_id"
							value="select">
						<input id="month_id" type="hidden" name="month_id" value="select">
						<input id="temp_id" type="hidden" name="temp_id" value="select">
						<input id="city_id" type="hidden" name="city_id" value="select">
						<input id="radius_id" type="hidden" name="radius_id" value="select">
						<input type="submit" style="text-align: right" name="Search"
							class="button special">
					</ul>
				</form>

			</div>
		</section>

		<!-- One -->
</body>
</html>