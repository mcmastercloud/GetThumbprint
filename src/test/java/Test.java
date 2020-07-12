import org.junit.Before;

import com.amazonaws.oidc.Thumbprints;
import com.amazonaws.utility.net.GetThumbprint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Thumbprints thumbprints = GetThumbprint.getTumbprints("https://oidc.eks.eu-west-1.amazonaws.com");
        ObjectMapper oMapper = new ObjectMapper();
        try {
			System.out.println(oMapper.writeValueAsString(thumbprints));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not retrieve Thumbprints");
		}
	}
		
}
