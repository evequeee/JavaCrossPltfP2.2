@param String errorMessage = null

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input, select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .error {
            color: red;
            margin-bottom: 15px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>User Registration</h1>
    
    @if(errorMessage != null)
        <div class="error">${errorMessage}</div>
    @endif
    
    <form action="/api/users/register" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        
        <div class="form-group">
            <label for="role">Role:</label>
            <select id="role" name="role" required>
                <option value="USER">User</option>
                <option value="ADMIN">Admin</option>
            </select>
        </div>
        
        <button type="submit">Register</button>
    </form>
</body>
</html>
