import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class OpenWeatherParser extends Weather {

    OpenWeatherParser(String city){
        parse(city);
    }

    public void parse (String city){
        HttpURLConnection connection = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=1f0a000a2de54a0110c5443c52f7819c");
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
                this.current_weather = json.getJSONArray("weather").getJSONObject(0).getString("main");
                this.feels_like = Math.round(json.getJSONObject("main").getDouble("feels_like")) - 273;
                this.temperature = Math.round(json.getJSONObject("main").getDouble("temp")) - 273;
                this.wind_speed = json.getJSONObject("wind").getInt("speed");
                this.parse_successful = true;

            } else {
                System.out.println("Error: No connection" );
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
