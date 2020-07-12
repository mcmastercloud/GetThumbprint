import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcmaster.aws.oidc.Thumbprints;
import com.mcmaster.utility.net.SSL;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Thumbprints thumbprints = SSL.getTumbprints("https://oidc.eks.eu-west-1.amazonaws.com");
        ObjectMapper oMapper = new ObjectMapper();
        try {
			System.out.println(oMapper.writeValueAsString(thumbprints));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not retrieve Thumbprints");
		}
	}
		
}
