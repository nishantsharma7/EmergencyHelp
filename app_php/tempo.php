<?php
define('DB_USER',"1110962");
define('DB_PASS',"nishu459");
define('DATABASE',"1110962");
define('DB_SERVER',"localhost");

$con = mysqli_connect(DB_SERVER,DB_USER,DB_PASS);
mysqli_select_db($con,DATABASE);

$query = "SELECT * FROM `table`";
if($query_run = mysqli_query($con,$query))
{
	$response["request"] = array();
	while($query_row = mysqli_fetch_assoc($query_run))
	{
		$order['name'] = $query_row['name'];
		$order['phone'] = $query_row['phone'];
		$order['address'] = $query_row['address'];
		$order['need'] = $query_row['need'];
		array_push($response["request"],$order);
	}
	$response['success'] = 1;
        $query = "DELETE FROM `table`";
        mysqli_query($con,$query);

}
else
{
	$response['success'] = 0;
	echo 'error_occured';
}
echo json_encode($response);
?>	