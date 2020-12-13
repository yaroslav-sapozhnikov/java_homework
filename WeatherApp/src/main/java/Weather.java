public class Weather {

    protected String current_weather;
    protected long temperature;
    protected long feels_like;
    protected int wind_speed;
    protected boolean parse_successful;

    Weather(){}

    Weather(String current_weather, long temperature, long feels_like, int wind_speed){
        this.current_weather = current_weather;
        this.temperature = temperature;
        this.feels_like = feels_like;
        this.wind_speed = wind_speed;
    }

    public void print_weather() {
        if (this.parse_successful == true) {
            System.out.println(" ");
            System.out.println("Current weather: " + this.current_weather);
            System.out.println("Temperature: " + this.temperature);
            System.out.println("Feels Like: " + this.feels_like);
            System.out.println("Wind speed: " + this.wind_speed);
        }
    }

    public boolean is_parse_successful () { return parse_successful; }
}
