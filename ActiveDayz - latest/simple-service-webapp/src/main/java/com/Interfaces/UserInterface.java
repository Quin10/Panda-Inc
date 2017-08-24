package com.Interfaces;

import com.Modules.User.*;
import org.json.JSONException;


/**
*
* @author Keaton Pennels u14373018
*/
public interface UserInterface {
	public String getUser(String userName);
    public String authenticate(String authJson);
    public String isAuthenticated(String authToken);
    public String registerAsUser(String jsonUser) throws JSONException;
    public String grantAdminRight(String userName);
    public String deleteUser(String userName);
}
