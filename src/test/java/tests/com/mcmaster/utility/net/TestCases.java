package tests.com.mcmaster.utility.net;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.amazonaws.services.lambda.runtime.Context;
import com.mcmaster.aws.lambda.thumbprint.GetThumbprintHandler;
import com.mcmaster.aws.lambda.thumbprint.ThumbprintPayload;
import com.mcmaster.utility.net.objects.Thumbprints;
/**
 * JUnit test cases
 * @author Stephen McMaster
 */
class TestCases {

	/**
	 * Tests that Thumbprints have been retrieved for a known good URL.
	 */
	@Test
	public void testThumbrpintRetrieval() {
		ThumbprintPayload tpp = new ThumbprintPayload();
		tpp.setUrl("https://news.bbc.co.uk");
		GetThumbprintHandler gtth = new GetThumbprintHandler();
		Thumbprints tps = gtth.handleRequest(tpp, this.createContext());

		System.out.println(String.format("Number of records returned: '%d'", tps.getCount()));
		System.out.println(String.format("Success: '%s'", tps.getSucceeded()));
		System.out.println(String.format("Error: '%s'", tps.getMessage()));
		
		assertTrue(tps.getCount() > 0);
	}

	/**
	 * Tests that Thumbprints have been retrieved for a proxy.
	 */
	@Test
	public void testThumbrpintRetrievalWithProxyl() {
		ThumbprintPayload tpp = new ThumbprintPayload();
		tpp.setUrl("https://news.bbc.co.uk");
		tpp.setProxyServer("example.com");
		tpp.setProxyPort(443);
		GetThumbprintHandler gtth = new GetThumbprintHandler();
		Thumbprints tps = gtth.handleRequest(tpp, this.createContext());

		System.out.println(String.format("Number of records returned: '%d'", tps.getCount()));
		System.out.println(String.format("Success: '%s'", tps.getSucceeded()));
		System.out.println(String.format("Error: '%s'", tps.getMessage()));
		
		// Add a valid proxy server above, and then change the below to > 0
		assertTrue(tps.getCount() == 0);
	}
	
	/**
	 * Confirms that there is a graceful return when a bad URL is specified.
	 */
	@Test
	public void testErrorHandling() {
		ThumbprintPayload tpp = new ThumbprintPayload();
		tpp.setUrl("https://silly.wee.url");

		GetThumbprintHandler gtth = new GetThumbprintHandler();
		Thumbprints tps = gtth.handleRequest(tpp, this.createContext());
		
		System.out.println(String.format("Number of records returned: '%d'", tps.getCount()));
		System.out.println(String.format("Success: '%s'", tps.getSucceeded()));
		System.out.println(String.format("Error: '%s'", tps.getMessage()));
		
		assertTrue(tps.getCount() == 0);
	}
	
	private Context createContext() {
		TestContext ctx = new TestContext();
		ctx.setFunctionName("GetThumbprint");
		return ctx;
	}
}
