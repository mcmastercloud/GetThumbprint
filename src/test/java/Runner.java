import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcmaster.utility.net.SSL;
import com.mcmaster.utility.net.objects.Thumbprints;

/**
 * Public class to test execution, and show the output generated
 * @author Stephen McMaster
 */
public class Runner {

	/**
	 * Directly executable function that should return the Thumbprints for AWS' EKS OIDC Endpoint in Ireland.
	 * @param args Default arguments -- not used.
	 */
	public static void main(String[] args) {
        Thumbprints thumbprints = SSL.getTumbprints("https://oidc.eks.eu-west-1.amazonaws.com");
        ObjectMapper oMapper = new ObjectMapper();
        try {
			System.out.println(oMapper.writeValueAsString(thumbprints));
		} catch (JsonProcessingException e) {
			System.out.println("Could not retrieve Thumbprints");
		}
	}
		
}
