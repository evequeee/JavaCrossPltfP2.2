@import ua.edu.chnu.springjpaproject.user.dto.UserRegistrationDto
@import java.util.Map

@param Map<String, String> errors = Map.of()
@param String error = null
@param String logout = null
@param String currentUser = null

@template.layout(
title = "Login",
currentUser = currentUser,
content = @`
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3 class="text-center">Login</h3>
                </div>
                <div class="card-body">
                    <!-- Error messages -->
                    @if(error != null)
                        <div class="alert alert-danger">
                            Invalid username or password.
                        </div>
                    @endif

                    @if(logout != null)
                        <div class="alert alert-success">
                            You have been logged out successfully.
                        </div>
                    @endif

                    <form action="/login" method="post">
                        <!-- CSRF token is added automatically by Spring Security -->

                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" id="username" name="username" class="form-control" required autofocus>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" id="password" name="password" class="form-control" required>
                        </div>

                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="remember-me" name="remember-me">
                            <label class="form-check-label" for="remember-me">Remember me</label>
                        </div>

                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </form>

                    <div class="mt-3 text-center">
                        <p>Don't have an account? <a href="/register">Register</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
`
)
