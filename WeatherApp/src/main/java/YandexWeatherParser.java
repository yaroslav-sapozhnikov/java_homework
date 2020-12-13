import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class YandexWeatherParser extends Weather {

    YandexWeatherParser(String lat, String lon){
        parse(lat, lon);
    }

    YandexWeatherParser(){}

    public void parse (String lat, String lon){
        HttpURLConnection connection = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL("http://api.weather.yandex.ru/v2/informers?lat=" + lat + "&lon=" + lon);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setRequestProperty("X-Yandex-API-Key", "3c8dbcb9-b864-4c16-b32e-883762aec300");
            connection.connect();

            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }

                JSONObject json = new JSONObject(sb.toString());
                this.current_weather = json.getJSONObject("fact").getString("condition");
                this.feels_like = json.getJSONObject("fact").getInt("feels_like");
                this.temperature = json.getJSONObject("fact").getInt("temp");
                this.wind_speed = json.getJSONObject("fact").getInt("wind_speed");
                this.parse_successful = true;

            } else {
                System.out.println("fail:" + connection.getResponseCode() + "; " + connection.getResponseMessage());
                this.parse_successful = false;
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }

}
