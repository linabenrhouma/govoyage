 <?php
require_once('connect.php');

@mysql_select_db($database_localhost,$con);

$hotel=$_GET['hotel'];
$client=$_GET['client'];
$date=$_GET['date'];
$nbre_jours=$_GET['nbre_jours'];
$Total=$_GET['Total'];
$nombrep=$_GET['nombrep'];
@mysql_query("INSERT INTO reservationh VALUES ('','$hotel','$client','$date','$nbre_jours','$Total','$nombrep') ");
echo "OK";
?>