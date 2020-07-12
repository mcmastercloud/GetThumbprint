import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcmaster.utility.net.SSL;
import com.mcmaster.utility.net.objects.Thumbprints;

/**
 * @author Stephen McMaster
 * Public class to test execution, and show the output generated
 */
public class Runner {

	/**
	 * Directly executable function
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
