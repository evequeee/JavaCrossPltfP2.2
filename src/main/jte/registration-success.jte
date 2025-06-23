@param com.example.user.User user

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Successful</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
        }
        .user-info {
            background-color: #f5f5f5;
            padding: 15px;
            border-radius: 5px;
            margin-top: 20px;
        }
        .back-link {
            margin-top: 20px;
            display: block;
        }
    </style>
</head>
<body>
    <h1>Registration Successful!</h1>
    
    <p>Your account has been created successfully.</p>
    
    <div class="user-info">
        <h2>User Information</h2>
        <p><strong>ID:</strong> ${user.id}</p>
        <p><strong>Username:</strong> ${user.username}</p>
        <p><strong>Role:</strong> ${user.role}</p>
    </div>
    
    <a href="/register" class="back-link">Register another user</a>
</body>
</html>
