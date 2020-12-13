import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherStackParser extends Weather {

    WeatherStackParser(String city){
        parse(city);
    }

    public void parse (String city){
        HttpURLConnection connection = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL("http://api.weatherstack.com/current?access_key=226bda107fd0f1e66819c1741de8064d&query=" + city);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.connect();

            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }

                JSONObject json = new JSONObject(sb.toString());
                this.current_weather = json.getJSONObject("current").getJSONArray("weather_descriptions").getString(0);
                this.feels_like = json.getJSONObject("current").getInt("feelslike");
                this.temperature = json.getJSONObject("current").getInt("temperature");
                this.wind_speed = json.getJSONObject("current").getInt("wind_speed");
                this.parse_successful = true;

            } else {
                System.out.println("Error: No connection");
                this.parse_successful = false;
            }

        } catch (Exception e) {
            System.out.println("Error: Wrong city" );
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
