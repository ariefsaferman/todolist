<?php 

    define("HOST", "localhost");
    define('USER', 'root');
    define('PASS', '');
    define('DB', 'todolist');
    $db_connect = mysqli_connect(HOST, USER, PASS, DB) or die("Unable connect to MySQL");
    header('Content-Type: application/json');



    $query = "SELECT * FROM notes ORDER BY id DESC";
    $sql = mysqli_query($db_connect, $query);

    if ($sql) {
        $result = array();
        while ($row = mysqli_fetch_array($sql)) {
            array_push($result, array(
                'id' => $row['id'],
                'note' => $row['note']
            ));
        }
        echo json_encode(array('notes' => $result));        
    }

    
    

?>