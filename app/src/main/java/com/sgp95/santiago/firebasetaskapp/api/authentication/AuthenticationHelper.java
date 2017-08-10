package com.sgp95.santiago.firebasetaskapp.api.authentication;

import com.sgp95.santiago.firebasetaskapp.api.RequestListener;

public interface AuthenticationHelper {
    void logTheUserIn(String email, String password, RequestListener requestListener);

    void logTheUserOut();

    boolean checkIfUserLoggedIn();

    String getCurrentUserDBName();
}
