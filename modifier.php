 <?php
require_once('connect.php');

mysql_select_db($database_localhost,$con);

$date=$_GET['date'];

$id=$_GET['id'];
mysql_query("UPDATE reservationh SET date='$date' WHERE id='$id' ");

echo "OK";
?>