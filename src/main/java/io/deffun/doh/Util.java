package io.deffun.doh;

import java.net.URI;
import java.net.URISyntaxException;

public final class Util {
    /**
     * Converts "postgresql://username:pswd@host:port/dbname"
     * to "jdbc:postgresql://host:port/dbname?user=username&password=pswd".
     *
     * @return JDBC URL
     */
    public static URI convertDbUrlToJdbcUrl(URI uri) {
        String userInfo = uri.getUserInfo();
        String query = userInfo != null ? userInfoToQuery(userInfo) : "";
        try {
            return new URI("jdbc:%s".formatted(uri.getScheme()), null, uri.getHost(), uri.getPort(), uri.getPath(), query, null);
        } catch (URISyntaxException e) {
            throw new ClientInvocationException(e);
        }
    }

    private static String userInfoToQuery(String userInfo) {
        String[] parts = userInfo.split(":");
        if (parts.length > 2) {
            throw new IllegalArgumentException("Cannot parse userInfo '%s'.".formatted(userInfo));
        }
        if (parts.length > 1) {
            return "user=%s&password=%s".formatted(parts[0], parts[1]);
        } else {
            return "user=%s".formatted(parts[0]);
        }
    }
}
