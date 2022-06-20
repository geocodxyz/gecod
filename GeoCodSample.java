import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeoCodSample
{
    final static String MY_KEY_API = "YOUR_KEY_API"; //you can get at https://geocod.xyz/register.html

    // docs at https://geocod.xyz/geocoding-api.html
    private String getCoords(String postaladdress, String postalcode, String cityname, String statename, String countryname)
    {
        try
        {
            String fullURL = "https://geocod.xyz/api/public/getCoords?apikey=" + MY_KEY_API
                    + "&postaladdress=" + postaladdress
                    + "&zipcode=" + postalcode
                    + "&city=" + cityname
                    + "&state=" + statename
                    + "&country=" + countryname
                    ;

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(fullURL, String.class);

            ObjectMapper mapper = new ObjectMapper();

            JsonNode object = mapper.readValue(response, JsonNode.class);
            if(object==null) return "";

            double lat = Double.parseDouble(object.get("lat").asText());
            double lon = Double.parseDouble(object.get("lon").asText());

            return lat + "," + lon;
        }
        catch(Exception e)
        {
            return "";
        }
    }

}
