package com.amazonaws.utility.net;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.DatatypeConverter;

import com.amazonaws.oidc.Thumbprints;

public class GetThumbprint {

	public static Thumbprints getTumbprints(String sURL) {
		try {
            URL URLToCheck = new URL(sURL);
            Thumbprints thumbprints = new Thumbprints();
            
            HttpsURLConnection conn = (HttpsURLConnection) URLToCheck.openConnection();
            conn.connect();
            Certificate[] certs = conn.getServerCertificates();
            for(Certificate cert : certs) {
                  if(cert instanceof X509Certificate) {
                         X509Certificate xcert = (X509Certificate) cert;

                         String sha1 = DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-1").digest(cert.getEncoded())).toLowerCase();
                         String sha256 = DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-256").digest(cert.getEncoded())).toLowerCase();
                         String md5 = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(cert.getEncoded())).toLowerCase();
                         thumbprints.Add(md5,  sha1,  sha256, xcert.getIssuerDN().getName(), xcert.getSubjectDN().getName());
                  }
            }
            return thumbprints;
            // TODO Auto-generated catch block
		     } catch (IOException e) {
		            return new Thumbprints("IO Exception: " + e.getMessage());
		     } catch (CertificateEncodingException e) {
		    	 return new Thumbprints(e.getStackTrace().toString());
		     } catch (NoSuchAlgorithmException e) {
		    	 return new Thumbprints(e.getStackTrace().toString());
		     }
	}
	
}
