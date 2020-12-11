import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.net.HttpURLConnection;
import org.json.JSONObject;

public class Main {

    public static void main(String args[]){

        String api_key = "1f0a000a2de54a0110c5443c52f7819c";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите требуемое API");
        System.out.println("OpenWeather - ow");
        System.out.println("WeatherStack - ws");
        String api = scanner.nextLine();
        System.out.println(api);

        if(api.equals("ow")) {
            String city = scanner.nextLine();

            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=1f0a000a2de54a0110c5443c52f7819c";

            String output = getUrlContent(url, "ow");
            JSONObject obj = new JSONObject(output);
            System.out.println(obj);
            System.out.println("Current weather: ");
            System.out.println("Temperature: ");
            //System.out.println("Feels Like: " + output.getJSONObject("main").getDouble("feels_like"));
            System.out.println("Wind speed: ");


        } else if(api.equals("ya")){
            String lat = scanner.nextLine();
            String lon = scanner.nextLine();

            String url = "http://api.weather.yandex.ru/v2/informers?lat=" + lat + "&lon=" + lon;

            String output = getUrlContent(url, "ya");
            System.out.println(output);

        } else if(api.equals("ws")){
            String city = scanner.nextLine();

            String url = "http://api.weatherstack.com/current?access_key=226bda107fd0f1e66819c1741de8064d&query=" + city;

            String output = getUrlContent(url, "ws");
            System.out.println(output);
        }

    }

    private static String getUrlContent(String urlAddress, String api_type) {
        HttpURLConnection connection = null;
        String result = new String();
        try {
            URL url = new URL(urlAddress);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            if (api_type.equals("ya")) {
                connection.setRequestProperty("X-Yandex-API-Key", "3c8dbcb9-b864-4c16-b32e-883762aec300");
            }
            connection.connect();

            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder sb = new StringBuilder();

                String line;

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                result = sb.toString();

            } else {
                System.out.println("fail:" + connection.getResponseCode() + "; " + connection.getResponseMessage());
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return result;
    }


}
