<?php 

    require_once('data.php');

    $note = $_POST['note'];

    $query = "INSERT INTO notes(note) VALUES ('$note')";
    $sql = mysqli_query($db_connect, $query);

    if ($sql) {
        echo json_encode(array('message' => 'Created!'));        
    } else {
        echo json_encode(array('message' => 'Error'));
    }


?>