<? php 

    
    define("HOST", "localhost");
    define('USER', 'root');
    define('PASS', '');
    define('DB', 'todolist');
    

    $db_connect = mysqli_connect(HOST, USER, PASS, DB) or die("Unable connect to MySQL");
    header('Content-Type: application/json');
?>