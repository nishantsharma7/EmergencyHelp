<?php
$DB_USER="1110962";
$DB_PASSWORD="nishu459";
$DB_DATABASE="1110962";
$DB_SERVER="localhost";
$response=array();

if (isset($_POST['name']) && isset($_POST['mobile']) && isset($_POST['address']) && isset($_POST['need'])) {
	$name = $_POST['name'];
	$mobile = $_POST['mobile'];
	$address = $_POST['address'];
        $need = $_POST['need'];
	$conn=mysqli_connect($DB_SERVER,$DB_USER,$DB_PASSWORD,$DB_DATABASE);
	$result=mysqli_query($conn,"INSERT INTO `table`(`name`, `phone`, `address`,`need`) VALUES ('$name','$mobile','$address','$need')");
		
	if($result){
	$response["success"]=TRUE;
	$response["message"]="User Successfully added";	
	echo json_encode($response);
	}
	else{
	$response["success"]=FALSE;
	$response["message"]="OOPS!!";	
	echo json_encode($response);
	}
}
else{
	$response["success"]=FALSE;
	$response["message"]="Required field is missing";	
	echo json_encode($response);
}

?>	
					