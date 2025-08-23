package admin.myapp.com.authservice.constant;

/**
 * Contains application-wide constant values for URL base paths and role names.
 */
public class Constants {

    /** Base URL path for authentication-related endpoints */
    public static final String BASE_URL = "/api/v1/auth";

    /** Base URL path for admin-related endpoints */
    public static final String BASE_URL_ADMIN = "/api/v1/admin";

    /** Base URL path for user-related endpoints */
    public static final String BASE_URL_USER = "/api/v1/user";

    public static final String BASE_URL_VEHICLE = "/api/v1/vehicle";
    public static final String BASE_URL_SERVICES = "/api/v1/services";
    public static final String BASE_URL_RATE = "/api/v1/rate";
    public static final String BASE_URL_BOOKING = "/api/v1/booking";
    public static final String BASE_URL_PAYMENTS = "/api/v1/payments";
    public static final String BASE_URL_PAYMENT_SETTLEMENT = "/api/v1/settlements";
    public static final String BASE_URL_QUEUE = "/api/v1/queue";
    public static final String BASE_URL_EXPENSES = "/api/v1/expenses";

    /** Role name for administrator users */
    public static final String ADMIN = "ADMIN";

    /** Role name for regular users */
    public static final String USER = "USER";
}
