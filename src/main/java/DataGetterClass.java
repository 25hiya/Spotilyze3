import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataGetterClass {


    public static JsonNode getData(String queryUrl) throws IOException {
        // Construct the query URL
        String token = "BQDhqtw6K5lv59Gg4EIhX3ZjaS2uYJBnzjnu7ThnoM4Yzmqoh9KFzY3pM_181R2MhOWeHzO9A-JV67BUhmc83LzZVKDWLUPG_t7G-GrEoe8TYfyWzQazGsO1v7oCZXPvTfr49qo0Ewr1fTLw_0bq37KLe9xRsfi2clkZm_At5xlR5DAIYb9A-OUpzI8UYWu38apu04BD8TGl79ALeMBewF0v74yOpIK2HlA";


        //LoginClass.getAccess_token();
        //String queryUrl = SEARCH_URL + userName (for getting user info)
        //"https://api.spotify.com/v1/"
        //https://api.spotify.com/v1/me

        // Set up the connection
        URL url = new URL(queryUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);

        // Handle response
        int status = connection.getResponseCode();
        String message = connection.getResponseMessage();
        if (status != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP Error: " + status + " " + message);
        }

        // Read the JSON response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        // Parse JSON response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResult = mapper.readTree(response.toString());
        return jsonResult;

        // Print the JSON response
        //System.out.println(jsonResult.toPrettyString());
    }

//    public static void main(String[] args) {
//        System.out.println(LoginClass.getAccess_token());
//        try { // Replace with actual token from getToken()
//            String userName = "me"; // Replace with the user's name
//            if (userName.equals("me")) {
//                searchUser(userName);
//            }
//            else {
//                searchUser("users/" + userName);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
