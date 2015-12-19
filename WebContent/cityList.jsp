<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="queries.*, java.util.*"
%>
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
      			<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

			<!-- Latest compiled JavaScript -->
			<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
      <noscript>
         <link rel="stylesheet" href="css/skel.css" />
         <link rel="stylesheet" href="css/style.css" />
         <link rel="stylesheet" href="css/style-xlarge.css" />
      </noscript>
   </head>
   <body>
      <!-- Header -->
      <header id="header">
         <h1 ><a href="index.jsp">City Recommandation</a></h1>
      </header>
      <!-- Main -->
      <section id="main" class="wrapper">
         <div class="container">
            <header class="major special">
               <h2>Cities Recommandation</h2>
               <p>lists the result of your pernalized city recommandation</p>
            </header>
            <section>
               <div class="table-wrapper" style="padding: 0px 150px 20px 150px;">
                  <table>
                     <thead>
                        <tr>
                           <th>City Name</th>
                           <th>Score</th>
                           <th style="text-align: center;"></th>
                        </tr>
                     </thead>
                     <tbody>
                     <%
                     int i=0;
                     String d="details";
                     String c="city";
                     CityList clist=(CityList) request.getAttribute("cityList");
                     List<CityWithScore> cityList=clist.getCityList();
                     for(CityWithScore city: cityList){
                    	 String cname=city.getCity();
                    	 String score=city.getScore();%>
                    	 <tr>
                    	 <form type="submit" method="post" action="cityList">
                    	  
                    	 	<td ><%=cname%></td>
                    	 	<td style="text-align: center;"><%=score%></td>
                    	 	<%-- <td id=<%=d+i %>style="text-align: center;">
                              <a  href="#" class="button small">Details...</a>				
                           </td> --%>
                           <td>
                           	<input type="hidden" name="city_name" value="<%=cname%>"/>
                          	<input type="submit" name="city_details" value="Details" />
                          	</td>
                    	 </form>                	
                     </tr>
                     <% }%>
                     <script>
                     	
                     </script>
<!--                         <tr>
                           <td>Boston</td>
                           <td>98.88</td>
                           <td style="text-align: center;">
                              <a  href="#" class="button small">Details...</a>				
                           </td>
                        </tr>
                        <tr>
                           <td>Seattle</td>
                           <td>97.88</td>
                           <td style="text-align: center;">
                              <a  href="#" class="button small">Details...</a>				
                           </td>
                        </tr>
                        <tr>
                           <td>Los Angelas</td>
                           <td>94.88</td>
                           <td style="text-align: center;">
                              <a  href="#" class="button small">Details...</a>				
                           </td>
                        </tr>
                        <tr>
                           <td>New York</td>
                           <td>93.88</td>
                           <td style="text-align: center;">
                              <a  href="#" class="button small">Details...</a>				
                           </td>
                        </tr>
                        <tr>
                           <td>Washington D.C.</td>
                           <td>92.88</td>
                           <td style="text-align: center;">
                              <a  href="#" class="button small">Details...</a>				
                           </td>
                        </tr> -->
                     </tbody>
                  </table>
               </div>
            </section>
         </div>
        <!--  <form id="city-form" method="post" action="index">
				<ul class="actions">
					<input id="city_details" type="hidden" name="city_details" value="">					
				</ul>
		</form> -->
      </section>
   </body>
</html>