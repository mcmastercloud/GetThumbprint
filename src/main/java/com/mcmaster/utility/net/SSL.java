package com.mcmaster.utility.net;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.DatatypeConverter;

import com.mcmaster.utility.net.objects.Thumbprint;
import com.mcmaster.utility.net.objects.Thumbprints;

/**
 * Utility class for SSL.  Currently only support retrieving Thumbprints from a Server URL.
 * @author Stephen McMaster
 *
 */
public final class SSL {

	
	/**
	 * Returns a Thumbprints object, with all Thumbprints, while specifically calling out the First and Last Thumbprint in the chain.  The first Thumbprint equates to the Server Certificate, and the last Thumbprint equates to the Root Certificate.<br/>
	 * This function was initially written for to configure the AWS EKS OIDC Provider.  In this case, you would supply the URL (e.g. <code>https://oidc.eks.eu-west-1.amazonaws.com</code>) to this function, and then use the value from Last::sha1 to populate the Thumbrpint for the provider.
	 * @param url The URL for which to retrieve Thumbprints.  Should include protocol (e.g. https://)
	 * @return {@link com.mcmaster.utility.net.objects.Thumbprints}
	 */
	public static Thumbprints getTumbprints(String url) {
		try {
            URL URLToCheck = new URL(url);
            Thumbprints thumbprints = new Thumbprints();
            
            HttpsURLConnection conn = (HttpsURLConnection) URLToCheck.openConnection();
            conn.connect();
            Certificate[] certs = conn.getServerCertificates();
            for(Certificate cert : certs) {
                  if(cert instanceof X509Certificate) {
                         X509Certificate xcert = (X509Certificate) cert;
                         Thumbprint.CERTIFICATE_STATUS status = Thumbprint.CERTIFICATE_STATUS.CURRENT;
                         try {
                        	 xcert.checkValidity();
                         } catch(CertificateExpiredException cee) {
                        	 status = Thumbprint.CERTIFICATE_STATUS.EXPIRED;
                        	 
                         } catch (CertificateNotYetValidException e) {
							// TODO Auto-generated catch block
							status = Thumbprint.CERTIFICATE_STATUS.NOT_YET_VALID;
						 }
                         
                         Date validFrom = xcert.getNotBefore();
                         Date validTo = xcert.getNotAfter();
                         String sha1 = DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-1").digest(cert.getEncoded())).toLowerCase();
                         String sha256 = DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-256").digest(cert.getEncoded())).toLowerCase();
                         String md5 = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(cert.getEncoded())).toLowerCase();
                         thumbprints.Add(md5,  sha1,  sha256, xcert.getIssuerDN().getName(), xcert.getSubjectDN().getName(), status, validFrom, validTo);
                  }
            }
            conn.disconnect();
            return thumbprints;
		     } catch (IOException e) {
		            return new Thumbprints("IO Exception: " + e.getMessage());
		     } catch (CertificateEncodingException e) {
		    	 return new Thumbprints("Certificate Encoding Exception" + e.getStackTrace().toString());
		     } catch (NoSuchAlgorithmException e) {
		    	 return new Thumbprints("NoSuchAlgorithm Exception: " + e.getStackTrace().toString());
		     }
	}
	
}
