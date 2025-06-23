package ua.edu.chnu.springjpaproject.user;

public enum UserRole {
    USER("Користувач"),
    ADMIN("Адміністратор");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}