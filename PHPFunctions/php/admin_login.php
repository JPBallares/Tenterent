<?php
include 'access_db.php';

$conn = OpenCon();
session_start();
$username = $_POST['username'];
$password = sha1($_POST['password']);

$username = mysqli_real_escape_string($conn, $username);
$password = mysqli_real_escape_string($conn, $password);

$sql = "SELECT * FROM admin where user_email = '$username'";
$result = $conn->query($sql);
if(isSet($_POST['login'])) {

    if ($result->num_rows > 0) {
        // output data of each row
        while($row = $result->fetch_assoc()) {
            if ($row['password'] == $password){
                $_SESSION['admin_loggedin'] = true;
                $_SESSION['admin_name'] = $row['admin_name'];
                $_SESSION['username'] = $row['username'];
                $_SESSION['password'] = $row['password'];
                header('Location: /home.php');
                exit;
    
            } else {
            
                echo "
                    <script>
                        alert('Wrong password.');
                        window.history.back();
                    </script>
                ";
    
            }
        }
    } else {
        echo "
            <script>
                alert('Account doesn't exist.);
                window.history.back();
            </script>
        ";
    }
    
} else {
    echo "Failed";
}

CloseCon($conn);
 
?>