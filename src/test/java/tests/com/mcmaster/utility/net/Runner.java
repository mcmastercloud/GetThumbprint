package tests.com.mcmaster.utility.net;

import com.mcmaster.utility.net.SSL;
import com.mcmaster.utility.net.objects.Thumbprints;

public class Runner {

	public static void main(String[] args) {
		Thumbprints tps = SSL.getTumbprints("https://news.bbc.co.uk", "", 0);
		
		System.out.println(String.format("Number of records returned: '%d'", tps.getCount()));
		System.out.println(String.format("Success: '%s'", tps.getSucceeded()));
		System.out.println(String.format("Error: '%s'", tps.getMessage()));
	}
	
}
