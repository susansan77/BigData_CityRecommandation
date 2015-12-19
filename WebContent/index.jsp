

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
		<h1>City Recommandation</h1>

	</header>

	<!-- Banner -->
	<section id="banner">
		<section id="main" class="wraper">



			<h2>Preferences</h2>
			<p>Select your preferences</p>



			<ul class="actions">

				<li style="display: block;">
				<li>
					<h3 style="color: white">Restaurants</h3>
				</li>
				<li>
					<div class="btn-group">
						<button id="restaurant_tag" type="button"
							class="btn btn-default dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							Select <span class="caret"></span>
						</button>
						<ul id="Restaurant-Options" class="dropdown-menu">
							<li><a>Very Important</a></li>
							<li><a>Medium</a></li>
							<li><a>Low Priority</a></li>

						</ul>
					</div>

				</li>
				</li>
				<script>
					$("#Restaurant-Options li")
							.click(
									function() {
										document
												.getElementById("restaurant_tag").innerHTML = $(
												this).text();
										$("#restaurant-id").val($(this).text());
									});
				</script>

				<li>
					<h3 style="color: white">Points of Interests</h3>
				</li>
				<li>
					<div class="btn-group">
						<button id="interests_tag" type="button"
							class="btn btn-default dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							Select <span class="caret"></span>
						</button>
						<ul id="Interests-Options" class="dropdown-menu">
							<li><a>Very Important</a></li>
							<li><a>Medium</a></li>
							<li><a>Low Priority</a></li>

						</ul>
					</div>

				</li>
				<script>
								$("#Interests-Options li").click(function(){ 
							    document.getElementById("interests_tag").innerHTML=$(this).text();
							    $("#pointsOfInterests-id").val($(this).text()); 
								});
						</script>
				<li>
					<h3 style="color: white">Hotels</h3>
				</li>
				<li>
					<div class="btn-group">
						<button id="hotels_tag" type="button"
							class="btn btn-default dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							Select <span class="caret"></span>
						</button>
						<ul id="hotelss-Options" class="dropdown-menu">
							<li><a>Very Important</a></li>
							<li><a>Medium</a></li>
							<li><a>Low Priority</a></li>

						</ul>
					</div>
				</li>
				<script>
								$("#hotelss-Options li").click(function(){ 
							    document.getElementById("hotels_tag").innerHTML=$(this).text();
								$("#hotels-id").val($(this).text()); 
								});
						</script>
			</ul>
			<form method="post" action="index">
				<ul class="actions">
					<input id="restaurant-id" type="hidden" name="restaurant" value="select">
					<input id="pointsOfInterests-id" type="hidden" name="pointsOfInterests" value="select">
					<input id="hotels-id" type="hidden" name="hotels" value="select">
					<input type="submit" name="Get Start"  class="button special big">
				</ul>
			</form>


			</div>
		</section>
	</section>

	<!-- One -->







</body>
</html>