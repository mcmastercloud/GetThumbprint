package tests.com.mcmaster.utility.net;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mcmaster.aws.oidc.Thumbprints;
import com.mcmaster.utility.net.SSL;
public class GetThumbprint {

	@Test
	public void testThumbrpintRetrieval() {
		Thumbprints tps = SSL.getTumbprints("https://news.bbc.co.uk");
		assertTrue(tps.getCount() > 0);
	}
	
	@Test
	public void testErrorHandling() {
		Thumbprints tps = SSL.getTumbprints("https://silly.old.url.soitus");
		assertTrue(tps.getCount() == 0);
	}
}
