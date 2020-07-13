# Java Thumbprint Library and AWS Lambda Handler

## Introduction
This library was created to support the retrieval of Thumbnails for Certificate Chains, specifically for configuring the AWS EKS OIDC Provider.

As per AWS documentation [found here](https://docs.aws.amazon.com/IAM/latest/UserGuide/id_roles_providers_create_oidc_verify-thumbprint.html), instructions are provided for retrieving the Thumbnail required for the OIDC provider using OpenSSL.  The ideal solution is to retrieve the required Thumbprints using AWS Lambda.  

I first set about creating a Lambda using Python and the pyOpenSSL library.  However, this did not work because pyOpenSSL has dependencies which were not supported as part of the Lambda runtime.  I therefore Wrote a Java Library that is ready for deployment to Lambda to perform the same task.

Rather than simply creating a Lambda function with the requisite code, I have made the Thumbprint code generic and simply call that code from the Lambda Handler.

**Note: **JavaDoc has been included as part of this project for full reference.

# Using the Library
Class com.mcmaster.utility.net.SSL contains function `getThumbprints(url)`.  When supplied with a URL (including the protocol - for example: `https://oidc.eks.eu-west-1.amazonaws.com` ) the function returns the following object (serialised here to JSON):

 ```json
 {
  "thumbprints": [
    {
      "sha1": "711cdb21382ca95773ece1d553c4b1981412e4f8",
      "sha256": "9ef823a2790a92e690ec0a80ae23213e0f2777560ce2ae62a8eeae1b3647ccbd",
      "md5": "f5aa161cfc590f4a64c9ac6ced0d0bf2",
      "issuerDN": "CN=Amazon, OU=Server CA 1B, O=Amazon, C=US",
      "subjectDN": "CN=oidc.eks.eu-west-1.amazonaws.com",
      "validFrom": "2020-00-12 12:00:00",
      "validTo": "2021-00-12 12:00:00",
      "status": "CURRENT",
      "valid": true
    },
    {
      "sha1": "917e732d330f9a12404f73d8bea36948b929dffc",
      "sha256": "f55f9ffcb83c73453261601c7e044db15a0f034b93c05830f28635ef889cf670",
      "md5": "eb268e55d434febda36a979a44654b6d",
      "issuerDN": "CN=Amazon Root CA 1, O=Amazon, C=US",
      "subjectDN": "CN=Amazon, OU=Server CA 1B, O=Amazon, C=US",
      "validFrom": "2015-00-22 12:00:00",
      "validTo": "2025-00-19 12:00:00",
      "status": "CURRENT",
      "valid": true
    },
    {
      "sha1": "06b25927c42a721631c1efd9431e648fa62e1e39",
      "sha256": "87dcd4dc74640a322cd205552506d1be64f12596258096544986b4850bc72706",
      "md5": "e865a22aae524d26869af0448d6fd896",
      "issuerDN": "CN=Starfield Services Root Certificate Authority - G2, O=\"Starfield Technologies, Inc.\", L=Scottsdale, ST=Arizona, C=US",
      "subjectDN": "CN=Amazon Root CA 1, O=Amazon, C=US",
      "validFrom": "2015-00-25 12:00:00",
      "validTo": "2037-00-31 01:00:00",
      "status": "CURRENT",
      "valid": true
    },
    {
      "sha1": "9e99a48a9960b14926bb7f3b02e22da2b0ab7280",
      "sha256": "28689b30e4c306aab53b027b29e36ad6dd1dcf4b953994482ca84bdc1ecac996",
      "md5": "c6150925cfea5941ddc7ff2a0a506692",
      "issuerDN": "OU=Starfield Class 2 Certification Authority, O=\"Starfield Technologies, Inc.\", C=US",
      "subjectDN": "CN=Starfield Services Root Certificate Authority - G2, O=\"Starfield Technologies, Inc.\", L=Scottsdale, ST=Arizona, C=US",
      "validFrom": "2009-00-02 12:00:00",
      "validTo": "2034-39-28 05:39:16",
      "status": "CURRENT",
      "valid": true
    }
  ],
  "succeeded": true,
  "message": "",
  "first": {
    "sha1": "711cdb21382ca95773ece1d553c4b1981412e4f8",
    "sha256": "9ef823a2790a92e690ec0a80ae23213e0f2777560ce2ae62a8eeae1b3647ccbd",
    "md5": "f5aa161cfc590f4a64c9ac6ced0d0bf2",
    "issuerDN": "CN=Amazon, OU=Server CA 1B, O=Amazon, C=US",
    "subjectDN": "CN=oidc.eks.eu-west-1.amazonaws.com",
    "validFrom": "2020-00-12 12:00:00",
    "validTo": "2021-00-12 12:00:00",
    "status": "CURRENT",
    "valid": true
  },
  "last": {
    "sha1": "9e99a48a9960b14926bb7f3b02e22da2b0ab7280",
    "sha256": "28689b30e4c306aab53b027b29e36ad6dd1dcf4b953994482ca84bdc1ecac996",
    "md5": "c6150925cfea5941ddc7ff2a0a506692",
    "issuerDN": "OU=Starfield Class 2 Certification Authority, O=\"Starfield Technologies, Inc.\", C=US",
    "subjectDN": "CN=Starfield Services Root Certificate Authority - G2, O=\"Starfield Technologies, Inc.\", L=Scottsdale, ST=Arizona, C=US",
    "validFrom": "2009-00-02 12:00:00",
    "validTo": "2034-39-28 05:39:16",
    "status": "CURRENT",
    "valid": true
  },
  "count": 4
}
</code>
```

### Thumbprint Object
A **thumbprint** object is always in the following structure:

```json
{
      "sha1": "711cdb21382ca95773ece1d553c4b1981412e4f8",
      "sha256": "9ef823a2790a92e690ec0a80ae23213e0f2777560ce2ae62a8eeae1b3647ccbd",
      "md5": "f5aa161cfc590f4a64c9ac6ced0d0bf2",
      "issuerDN": "CN=Amazon, OU=Server CA 1B, O=Amazon, C=US",
      "subjectDN": "CN=oidc.eks.eu-west-1.amazonaws.com",
      "validFrom": "2020-00-12 12:00:00",
      "validTo": "2021-00-12 12:00:00",
      "status": "CURRENT",
      "valid": true
}
```


| Property | Description | Data Type |
| ---- | --- | --- |
| sha1 | SHA1 Thumbprint for the Certificate | String |
| sha256 | SHA256 Thumbprint for the Certificate | String | 
| md | MD5 Thumbprint for the Certificate | String |
| issuerDN | The DN for the Certificate Issuer | String | 
| subjectDN | The DN for the Certificate Subject | String |
| validFrom | The date the certificate is valid From | java.util.date |
| validto | The date the certificate is valid to | java.util.date |
| status | The status of the certificate.  One of: CURRENT, EXPIRED, NOT_YET_VALID | CONST CERTIFICATE_STATUS |
| valid | True when status == CERTIFICATE_STATUS.CURRENT | Boolean |

###  Thumbprints Class

getThumbprints(url) returns the Thumbprints class, which is described below:
 
| Property | Description | Data Type |
| ---- | --- | --- |
| message | Error message, if relevant | String |
| succeeded | True if the call succeeded, otherwise false | Boolean |
| thumbprints | List of Thumbprints for all certificates found in the chain | List<instanceof Thumbprint> |
| first | The first Thumbprint in the list.  This is the Server Certificate | instanceof Thumbprint |
| last | The last Thumbprint in the list.  This is the Root Certificate | instanceof Thumbprint |
| count | The number of Thumbprints (e.g. Certificates) that have been returned | Integer |

## Using with AWS Lambda

1. This project contains a pre-zipped file that is ready to run with Lambda [GetThumbprint-1.0.0.jar](https://bitbucket.org/StephenMcMaster/getthumbprint/src/master/target/GetThumbprint-1.0.0.jar)  Please see here for the .zip file.  Use this Zip file in your Lambda as the source
2. Set the Lambda Handler name to `com.mcmaster.aws.lambda.thumbprint.GetThumbprintHandler::handleRequest`
3. Make sure your runtime is set to `Java 11`
4. You can execute the Lambda by supplying the following as a Test message:

```json
{
	url: "https://oidc.eks.eu-west-1.amazonaws.com"
}
```

<strong>Note: </strong>If you are running your Lambda in a VPC, you will need to make sure you have outbound internet access.

## Using in your own Project
Import the reference: `com.mcmaster.utility.net.SSL` and call the `getThumbprints(url)` function from the SSL class.

