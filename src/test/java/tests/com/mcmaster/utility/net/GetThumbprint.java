package tests.com.mcmaster.utility.net;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcmaster.utility.net.SSL;
import com.mcmaster.utility.net.objects.Thumbprints;
/**
 * JUnit test cases
 * @author Stephen McMaster
 */
public class GetThumbprint {

	private ObjectMapper oMapper = new ObjectMapper();
	/**
	 * Tests that Thumbprints have been retrieved for a known good URL.
	 */
	@Test
	public void testThumbrpintRetrieval() {
		Thumbprints tps = SSL.getTumbprints("https://news.bbc.co.uk");
		try {
			System.out.println(oMapper.writeValueAsString(tps));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		assertTrue(tps.getCount() > 0);
	}
	
	/**
	 * Confirms that there is a graceful return when a bad URL is specified.
	 */
	@Test
	public void testErrorHandling() {
		Thumbprints tps = SSL.getTumbprints("https://silly.old.url.soitus");
		try {
			System.out.println(oMapper.writeValueAsString(tps));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		assertTrue(tps.getCount() == 0);
	}
}
