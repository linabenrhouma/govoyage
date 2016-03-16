<?php   
      require_once('connect.php'); 
	  
	 /*;
	  $garde=$_GET['garde_phr'];
	  
	  $lettre=$_GET['pays'];*/ 	  
      mysql_select_db($database_localhost,$con);  
	  
	 
	  $jour="SELECT `client`FROM `reservationh` WHERE `date` = '$date'";
	  $jour_exec=mysql_query($jour);


	  
	  
	  
     /* $query_search = "SELECT * FROM pharmacie"; 
      $query_exec = mysql_query($query_search) or die(mysql_error());  
     
	if( $query_search=!null ){ 
//if( $jour!= null || $garde!=null || $domi!=null || $let!=null){  
      $xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
      $root_element = "pharmacies"; 
      $xml .= "<$root_element>";
	while($result_array = mysql_fetch_assoc($query_exec))
     {
      $xml .= "<pharmacie>";
 
      foreach($result_array as $key => $value)
      {
         //$key holds the table column name
         $xml .= "<$key>";
 
         //embed the SQL data in a CDATA element to avoid XML entity issues
         $xml .= "<![CDATA[$value]]>"; 
 
         //and close the element
         $xml .= "</$key>";
      }
 
      $xml.="</pharmacie>";
   }
//close the root element
$xml .= "</$root_element>";
 
//send the xml header to the browser
header ("Content-Type:text/xml"); 
 
//output the XML data
echo $xml;
 }  
 ?>  