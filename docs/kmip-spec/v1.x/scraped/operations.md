# KMIP 1.2 — Operations (Sections 4 & 5)

Total: **41** operations

## 4.1 Create

### Request Payload

| Field              | Required | Description                                                                      |
|--------------------|----------|----------------------------------------------------------------------------------|
| Object Type        | Yes      | Determines the type of object to be created.                                     |
| Template-Attribute | Yes      | Specifies desired attributes using to be associated with the new object template |

### Response Payload

| Field                    | Required | Description                                                                      |
|--------------------------|----------|----------------------------------------------------------------------------------|
| Object Type              | Yes      | Type of object created.                                                          |
| Unique Identifier        | Yes      | The Unique Identifier of the newly created object.                               |
| Template-Attribute       | No       | An OPTIONAL list of object attributes with values that were not specified in the |
| Cryptographic Algorithm  | Yes      |                                                                                  |
| Cryptographic Usage Mask | Yes      |                                                                                  |

## 4.2 Create Key Pair

### Request Payload

| Field                          | Required | Description                                                                      |
|--------------------------------|----------|----------------------------------------------------------------------------------|
| Common Template-Attribute      | No       | Specifies desired attributes in templates and/or as individual attributes to be  |
| Private Key Template-Attribute | No       | Specifies templates and/or attributes to be associated with the new object that  |
| Public Key Template-Attribute  | No       | Specifies templates and/or attributes to be associated with the new object that  |

### Response Payload

| Field                           | Required | Description                                                                      |
|---------------------------------|----------|----------------------------------------------------------------------------------|
| Private Key Unique Identifier   | Yes      | The Unique Identifier of the newly created Private Key object.                   |
| Public Key Unique Identifier    | Yes      | The Unique Identifier of the newly created Public Key object.                    |
| Private Key Template-Attribute  | No       | An OPTIONAL list of attributes, for the Private Key Object, with values that wer |
| Public Key Template-Attribute   | No       | An OPTIONAL list of attributes, for the Public Key Object, with values that were |
| Cryptographic Algorithm         | Yes      | Yes                                                                              |
| Cryptographic Length            | No       | Yes                                                                              |
| Cryptographic Usage Mask        | Yes      | No                                                                               |
| Cryptographic Domain Parameters | No       | Yes                                                                              |
| Cryptographic Parameters        | No       | Yes                                                                              |

## 4.3 Register

### Request Payload

| Field                                                                                                 | Required | Description                                                                      |
|-------------------------------------------------------------------------------------------------------|----------|----------------------------------------------------------------------------------|
| Object Type                                                                                           | Yes      | Determines the type of object being registered.                                  |
| Template-Attribute                                                                                    | Yes      | Specifies desired object attributes to be associated with the new object using t |
| Certificate, Symmetric Key, Private Key, Public Key, Split Key, Template Secret Data or Opaque Object | Yes      | The object being registered. The object and attributes MAY be wrapped.           |

### Response Payload

| Field                       | Required                                                                                                                                                                            | Description                                                                      |
|-----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| Unique Identifier           | Yes                                                                                                                                                                                 | The Unique Identifier of the newly registered object.                            |
| Template-Attribute          | No                                                                                                                                                                                  | An OPTIONAL list of object attributes with values that were not specified in the |
| Cryptographic Algorithm     | Yes, MAY be omitted only if this information is encapsulated in the Key Block. Does not apply to Secret Data. If present, then Cryptographic Length below SHALL also be present.    |                                                                                  |
| Cryptographic Length        | Yes, MAY be omitted only if this information is encapsulated in the Key Block. Does not apply to Secret Data. If present, then Cryptographic Algorithm above SHALL also be present. |                                                                                  |
| Certificate Length          | Yes. Only applies to Certificates.                                                                                                                                                  |                                                                                  |
| Cryptographic Usage Mask    | Yes.                                                                                                                                                                                |                                                                                  |
| Digital Signature Algorithm | Yes, MAY be omitted only if this information is encapsulated in the Certificate object. Only applies to Certificates.                                                               |                                                                                  |

## 4.4 Re-key

### Request Payload

| Field              | Required | Description                                                                      |
|--------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier  | No       | Determines the existing Symmetric Key being re-keyed. If omitted, then the ID Pl |
| Offset             | No       | An Interval object indicating the difference between the Initialization Date and |
| Template-Attribute | No       | Specifies desired object attributes using templates and/or individual attributes |

### Response Payload

| Field              | Required | Description                                                                      |
|--------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier  | Yes      | The Unique Identifier of the newly-created replacement Symmetric Key.            |
| Template-Attribute | No       | An OPTIONAL list of object attributes with values that were not specified in the |

## 4.5 Re-key Key Pair

### Request Payload

| Field                          | Required | Description                                                                      |
|--------------------------------|----------|----------------------------------------------------------------------------------|
| Private Key Unique Identifier  | No       | Determines the existing Asymmetric key pair to be re-keyed. If omitted, then the |
| Offset                         | No       | An Interval object indicating the difference between the Initialization date and |
| Common Template-Attribute      | No       | Specifies desired attributes in templates and/or as individual attributes that a |
| Private Key Template-Attribute | No       | Specifies templates and/or attributes that apply to the Private Key Object. Orde |
| Public Key Template-Attribute  | No       | Specifies templates and/or attributes that apply to the Public Key Object. Order |

### Response Payload

| Field                          | Required | Description                                                                      |
|--------------------------------|----------|----------------------------------------------------------------------------------|
| Private Key Unique Identifier  | Yes      | The Unique Identifier of the newly created replacement Private Key object.       |
| Public Key Unique Identifier   | Yes      | The Unique Identifier of the newly created replacement Public Key object.        |
| Private Key Template-Attribute | No       | An OPTIONAL list of attributes, for the Private Key Object, with values that wer |
| Public Key Template-Attribute  | No       | An OPTIONAL list of attributes, for the Public Key Object, with values that were |

## 4.6 Derive Key

### Request Payload

| Field                            | Required             | Description                                                                      |
|----------------------------------|----------------------|----------------------------------------------------------------------------------|
| Object Type                      | Yes                  | Determines the type of object to be created.                                     |
| Unique Identifier                | Yes. MAY be repeated | Determines the object or objects to be used to derive a new key. Note that the c |
| Derivation Method                | Yes                  | An Enumeration object specifying the method to be used to derive the new key.    |
| Derivation Parameters, see below | Yes                  | A Structure object containing the parameters needed by the specified derivation  |
| Template-Attribute               | Yes                  | Specifies desired attributes to be associated with the new object using template |

### Response Payload

| Field                    | Required    | Description                                                                      |
|--------------------------|-------------|----------------------------------------------------------------------------------|
| Unique Identifier        | Yes         | The Unique Identifier of the newly derived key or Secret Data object.            |
| Template-Attribute       | No          | An OPTIONAL list of object attributes with values that were not specified in the |
| Derivation Parameters    | Structure   | Yes.                                                                             |
| Cryptographic Parameters | Structure   | Yes, except for HMAC derivation keys.                                            |
| Initialization Vector    | Byte String | No, depends on PRF and mode of operation: empty IV is assumed if not provided.   |
| Derivation Data          | Byte String | Yes, unless the Unique Identifier of a Secret Data object is provided.           |
| Derivation Parameters    | Structure   | Yes.                                                                             |
| Cryptographic Parameters | Structure   | No, depends on the PRF.                                                          |
| Initialization Vector    | Byte String | No, depends on the PRF (if different than those defined in [PKCS#5] ) and mode o |
| Derivation Data          | Byte String | Yes, unless the Unique Identifier of a Secret Data object is provided.           |
| Salt                     | Byte String | Yes.                                                                             |
| Iteration Count          | Integer     | Yes.                                                                             |

## 4.7 Certify

### Request Payload

| Field                    | Required | Description                                                                      |
|--------------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier        | No       | The Unique Identifier of the Public Key being certified. If omitted, then the ID |
| Certificate Request Type | No       | An Enumeration object specifying the type of certificate request. It is REQUIRED |
| Certificate Request      | No       | A Byte String object with the certificate request.                               |
| Template-Attribute       | No       | Specifies desired object attributes using templates and/or individual attributes |

### Response Payload

| Field              | Required | Description                                                                      |
|--------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier  | Yes      | The Unique Identifier of the generated Certificate object.                       |
| Template-Attribute | No       | An OPTIONAL list of object attributes with values that were not specified in the |

## 4.8 Re-certify

### Request Payload

| Field                    | Required | Description                                                                      |
|--------------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier        | No       | The Unique Identifier of the Certificate being renewed. If omitted, then the ID  |
| Certificate Request Type | No       | An Enumeration object specifying the type of certificate request. It is REQUIRED |
| Certificate Request      | No       | A Byte String object with the certificate request.                               |
| Offset                   | No       | An Interval object indicating the difference between the Initial Date of the new |
| Template-Attribute       | No       | Specifies desired object attributes using templates and/or individual attributes |

### Response Payload

| Field              | Required | Description                                                                      |
|--------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier  | Yes      | The Unique Identifier of the new certificate.                                    |
| Template-Attribute | No       | An OPTIONAL list of object attributes with values that were not specified in the |

## 4.9 Locate

### Request Payload

| Field               | Required            | Description                                                                      |
|---------------------|---------------------|----------------------------------------------------------------------------------|
| Maximum Items       | No                  | An Integer object that indicates the maximum number of object identifiers the se |
| Storage Status Mask | No                  | An Integer object (used as a bit mask) that indicates whether only on-line objec |
| Object Group Member | No                  | An Enumeration object that indicates the object group member type.               |
| Attribute           | No, MAY be repeated | Specifies an attribute and its value(s) that are REQUIRED to match those in a ca |

### Response Payload

| Field             | Required            | Description                                   |
|-------------------|---------------------|-----------------------------------------------|
| Unique Identifier | No, MAY be repeated | The Unique Identifier of the located objects. |

## 4.10 Check

### Request Payload

| Field                    | Required | Description                                                                      |
|--------------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier        | No       | Determines the object being checked. If omitted, then the ID Placeholder value i |
| Usage Limits Count       | No       | Specifies the number of Usage Limits Units to be protected to be checked against |
| Cryptographic Usage Mask | No       | Specifies the Cryptographic Usage for which the client intends to use the object |
| Lease Time               | No       | Specifies a Lease Time value that the Client is asking the server to validate ag |

### Response Payload

| Field                    | Required               | Description                                                                      |
|--------------------------|------------------------|----------------------------------------------------------------------------------|
| Unique Identifier        | Yes, unless a failure, | The Unique Identifier of the object.                                             |
| Usage Limits Count       | No                     | Returned by the Server if the Usage Limits value specified in the Request Payloa |
| Cryptographic Usage Mask | No                     | Returned by the Server if the Cryptographic Usage Mask specified in the Request  |
| Lease Time               | No                     | Returned by the Server if the Lease Time value in the Request Payload is larger  |

## 4.11 Get

### Request Payload

| Field                      | Required | Description                                                                      |
|----------------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier          | No       | Determines the object being requested. If omitted, then the ID Placeholder value |
| Key Format Type            | No       | Determines the key format type to be returned.                                   |
| Key Compression Type       | No       | Determines the compression method for elliptic curve public keys.                |
| Key Wrapping Specification | No       | Specifies keys and other information for wrapping the returned object. This fiel |

### Response Payload

| Field                                                                                                   | Required | Description                              |
|---------------------------------------------------------------------------------------------------------|----------|------------------------------------------|
| Object Type                                                                                             | Yes      | Type of object.                          |
| Unique Identifier                                                                                       | Yes      | The Unique Identifier of the object.     |
| Certificate, Symmetric Key, Private Key, Public Key, Split Key, Template, Secret Data, or Opaque Object | Yes      | The cryptographic object being returned. |

## 4.12 Get Attributes

### Request Payload

| Field             | Required            | Description                                                                      |
|-------------------|---------------------|----------------------------------------------------------------------------------|
| Unique Identifier | No                  | Determines the object whose attributes are being requested. If omitted, then the |
| Attribute Name    | No, MAY be repeated | Specifies the name of an attribute associated with the object.                   |

### Response Payload

| Field             | Required            | Description                                         |
|-------------------|---------------------|-----------------------------------------------------|
| Unique Identifier | Yes                 | The Unique Identifier of the object.                |
| Attribute         | No, MAY be repeated | The requested attribute associated with the object. |

## 4.13 Get Attribute List

### Request Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | No       | Determines the object whose attribute names are being requested. If omitted, the |

### Response Payload

| Field             | Required             | Description                                                       |
|-------------------|----------------------|-------------------------------------------------------------------|
| Unique Identifier | Yes                  | The Unique Identifier of the object.                              |
| Attribute Name    | Yes, MAY be repeated | The names of the available attributes associated with the object. |

## 4.14 Add Attribute

### Request Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | No       | The Unique Identifier of the object. If omitted, then the ID Placeholder value i |
| Attribute         | Yes      | Specifies the attribute to be added as an attribute for the object.              |

### Response Payload

| Field             | Required | Description                                     |
|-------------------|----------|-------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object.            |
| Attribute         | Yes      | The added attribute associated with the object. |

## 4.15 Modify Attribute

### Request Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | No       | The Unique Identifier of the object. If omitted, then the ID Placeholder value i |
| Attribute         | Yes      | Specifies the attribute associated with the object to be modified.               |

### Response Payload

| Field             | Required | Description                                                           |
|-------------------|----------|-----------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object.                                  |
| Attribute         | Yes      | The modified attribute associated with the object with the new value. |

## 4.16 Delete Attribute

### Request Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | No       | Determines the object whose attributes are being deleted. If omitted, then the I |
| Attribute Name    | Yes      | Specifies the name of the attribute associated with the object to be deleted.    |
| Attribute Index   | No       | Specifies the Index of the Attribute.                                            |

### Response Payload

| Field             | Required | Description                                       |
|-------------------|----------|---------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object.              |
| Attribute         | Yes      | The deleted attribute associated with the object. |

## 4.17 Obtain Lease

### Request Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | No       | Determines the object for which the lease is being obtained. If omitted, then th |

### Response Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object.                                             |
| Lease Time        | Yes      | An interval (in seconds) that specifies the amount of time that the object MAY b |
| Last Change Date  | Yes      | The date and time indicating when the latest change was made to the contents or  |

## 4.18 Get Usage Allocation

### Request Payload

| Field                                                    | Required | Description                                                                      |
|----------------------------------------------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier                                        | No       | Determines the object whose usage allocation is being requested. If omitted, the |
| Usage Limits Count, see Usage Limits Count field in 3.21 | Yes      | The number of Usage Limits Units to be protected.                                |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 4.19 Activate

### Request Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | No       | Determines the object being activated. If omitted, then the ID Placeholder value |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 4.20 Revoke

### Request Payload

| Field                      | Required | Description                                                                      |
|----------------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier          | No       | Determines the object being revoked. If omitted, then the ID Placeholder value i |
| Revocation Reason          | Yes      | Specifies the reason for revocation.                                             |
| Compromise Occurrence Date | No       | SHALL be specified if the Revocation Reason is 'key compromise'.                 |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 4.21 Destroy

### Request Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | No       | Determines the object being destroyed. If omitted, then the ID Placeholder value |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 4.22 Archive

### Request Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | No       | Determines the object being archived. If omitted, then the ID Placeholder value  |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 4.23 Recover

### Request Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | No       | Determines the object being recovered. If omitted, then the ID Placeholder value |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 4.24 Validate

### Request Payload

| Field             | Required            | Description                                                                      |
|-------------------|---------------------|----------------------------------------------------------------------------------|
| Certificate       | No, MAY be repeated | One or more Certificates.                                                        |
| Unique Identifier | No, MAY be repeated | One or more Unique Identifiers of Certificate Objects.                           |
| Validity Date     | No                  | A Date-Time object indicating when the certificate chain needs to be valid. If o |

### Response Payload

| Field              | Required | Description                                                                      |
|--------------------|----------|----------------------------------------------------------------------------------|
| Validity Indicator | Yes      | An Enumeration object indicating whether the certificate chain is valid, invalid |

## 4.25 Query

### Request Payload

| Field          | Required             | Description                               |
|----------------|----------------------|-------------------------------------------|
| Query Function | Yes, MAY be Repeated | Determines the information being queried. |

### Response Payload

| Field                 | Required            | Description                                                                      |
|-----------------------|---------------------|----------------------------------------------------------------------------------|
| Operation             | No, MAY be repeated | Specifies an Operation that is supported by the server.                          |
| Object Type           | No, MAY be repeated | Specifies a Managed Object Type that is supported by the server.                 |
| Vendor Identification | No                  | SHALL be returned if Query Server Information is requested. The Vendor Identific |
| Server Information    | No                  | Contains vendor-specific information possibly be of interest to the client.      |
| Application Namespace | No, MAY be repeated | Specifies an Application Namespace supported by the server.                      |
| Extension Information | No, MAY be repeated | SHALL be returned if Query Extension List or Query Extension Map is requested an |
| Attestation Type      | No, MAY be repeated | Specifies an Attestation Type that is supported by the server.                   |

## 4.26 Discover Versions

### Request Payload

| Field            | Required            | Description                                                                      |
|------------------|---------------------|----------------------------------------------------------------------------------|
| Protocol Version | No, MAY be Repeated | The list of protocol versions supported by the client ordered in highest prefere |

### Response Payload

| Field            | Required            | Description                                                                      |
|------------------|---------------------|----------------------------------------------------------------------------------|
| Protocol Version | No, MAY be repeated | The list of protocol versions supported by the server ordered in highest prefere |

## 4.27 Cancel

### Request Payload

| Field                          | Required | Description                           |
|--------------------------------|----------|---------------------------------------|
| Asynchronous Correlation Value | Yes      | Specifies the request being canceled. |

### Response Payload

| Field                          | Required | Description                                            |
|--------------------------------|----------|--------------------------------------------------------|
| Asynchronous Correlation Value | Yes      | Specified in the request.                              |
| Cancellation Result            | Yes      | Enumeration indicating the result of the cancellation. |

## 4.28 Poll

### Request Payload

| Field                          | Required | Description                         |
|--------------------------------|----------|-------------------------------------|
| Asynchronous Correlation Value | Yes      | Specifies the request being polled. |

### Response Payload

_No fields (empty or not scraped)_

## 4.29 Encrypt

### Request Payload

| Field                    | Required | Description                                                                      |
|--------------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier        | No       | The Unique Identifier of the Managed Cryptographic Object that is the key to use |
| Cryptographic Parameters | No       | The Cryptographic Parameters (Block Cipher Mode, Padding Method, RandomIV) corre |
| Data                     | Yes      | The data to be encrypted (as a Byte String).                                     |
| IV/Counter/Nonce         | No       | The initialization vector, counter or nonce to be used (where appropriate).      |

### Response Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the Managed Cryptographic Object that was the key used  |
| Data              | Yes      | The encrypted data (as a Byte String).                                           |
| IV/Counter/Nonce  | No       | The value used if the Cryptographic Parameters specified Random IV and the IV/Co |

## 4.30 Decrypt

### Request Payload

| Field                    | Required | Description                                                                      |
|--------------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier        | No       | The Unique Identifier of the Managed Cryptographic Object that is the key to use |
| Cryptographic Parameters | No       | The Cryptographic Parameters (Block Cipher Mode, Padding Method) corresponding t |
| Data                     | Yes      | The data to be decrypted (as a Byte String).                                     |
| IV/Counter/Nonce         | No       | The initialization vector, counter or nonce to be used (where appropriate).      |

### Response Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the Managed Cryptographic Object that is the key used f |
| Data              | Yes      | The decrypted data (as a Byte String).                                           |

## 4.31 Sign

### Request Payload

| Field                    | Required | Description                                                                      |
|--------------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier        | No       | The Unique Identifier of the Managed Cryptographic Object that is the key to use |
| Cryptographic Parameters | No       | The Cryptographic Parameters (Digital Signature Algorithm or Cryptographic Algor |
| Data                     | Yes      | The data to be signed (as a Byte String).                                        |

### Response Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the Managed Cryptographic Object that is the key used f |
| Signature Data    | Yes      | The signed data (as a Byte String).                                              |

## 4.32 Signature Verify

### Request Payload

| Field                    | Required | Description                                                                      |
|--------------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier        | No       | The Unique Identifier of the Managed Cryptographic Object that is the key to use |
| Cryptographic Parameters | No       | The Cryptographic Parameters (Digital Signature Algorithm or Cryptographic Algor |
| Data                     | No       | The data that was signed (as a Byte String).                                     |
| Signature Data           | Yes      | The signature to be verified (as a Byte String).                                 |

### Response Payload

| Field              | Required | Description                                                                      |
|--------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier  | Yes      | The Unique Identifier of the Managed Cryptographic Object that is the key used f |
| Validity Indicator | Yes      | An Enumeration object indicating whether the signature is valid, invalid, or unk |
| Data               | No       | The OPTIONAL recovered data (as a Byte String) for those signature algorithms wh |

## 4.33 MAC

### Request Payload

| Field                    | Required | Description                                                                      |
|--------------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier        | No       | The Unique Identifier of the Managed Cryptographic Object that is the key to use |
| Cryptographic Parameters | No       | The Cryptographic Parameters (Cryptographic Algorithm) corresponding to the part |
| Data                     | Yes      | The data to be MACed (as a Byte String).                                         |

### Response Payload

| Field             | Required | Description                                                                      |
|-------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the Managed Cryptographic Object that is the key used f |
| MAC Data          | Yes      | The data MACed (as a Byte String).                                               |

## 4.34 MAC Verify

### Request Payload

| Field                    | Required | Description                                                                      |
|--------------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier        | No       | The Unique Identifier of the Managed Cryptographic Object that is the key to use |
| Cryptographic Parameters | No       | The Cryptographic Parameters (Cryptographic Algorithm) corresponding to the part |
| Data                     | No       | The data that was MACed (as a Byte String).                                      |
| MAC Data                 | Yes      | The data to be MAC verified (as a Byte String).                                  |

### Response Payload

| Field              | Required | Description                                                                      |
|--------------------|----------|----------------------------------------------------------------------------------|
| Unique Identifier  | Yes      | The Unique Identifier of the Managed Cryptographic Object that is the key used f |
| Validity Indicator | Yes      | An Enumeration object indicating whether the MAC is valid, invalid, or unknown.  |

## 4.35 RNG Retrieve

### Request Payload

| Field       | Required | Description                                                             |
|-------------|----------|-------------------------------------------------------------------------|
| Data Length | Yes      | The amount of random number generator output to be returned (in bytes). |

### Response Payload

| Field | Required | Description                         |
|-------|----------|-------------------------------------|
| Data  | Yes      | The random number generator output. |

## 4.36 RNG Seed

### Request Payload

| Field | Required | Description                                                       |
|-------|----------|-------------------------------------------------------------------|
| Data  | Yes      | The data to be provided as a seed to the random number generator. |

### Response Payload

| Field       | Required | Description                              |
|-------------|----------|------------------------------------------|
| Data Length | Yes      | The amount of seed data used (in bytes). |

## 4.37 H ash

### Request Payload

| Field                    | Required | Description                                                                      |
|--------------------------|----------|----------------------------------------------------------------------------------|
| Cryptographic Parameters | Yes      | The Cryptographic Parameters (Hashing Algorithm) corresponding to the particular |
| Data                     | Yes      | The data to be hashed (as a Byte String).                                        |

### Response Payload

| Field | Required | Description                         |
|-------|----------|-------------------------------------|
| Data  | Yes      | The hashed data (as a Byte String). |

## 4.38 Create Split Key

### Request Payload

| Field               | Required | Description                                                                      |
|---------------------|----------|----------------------------------------------------------------------------------|
| Object Type         | Yes      | Determines the type of object to be created.                                     |
| Unique Identifier   | No       | The Unique Identifier of the key to be split (if applicable).                    |
| Split Key Parts     | Yes      | The total number of parts.                                                       |
| Split Key Threshold | Yes      | The minimum number of parts needed to reconstruct the entire key.                |
| Split Key Method    | Yes      |                                                                                  |
| Prime Field Size    | No       |                                                                                  |
| Template-Attribute  | Yes      | Specifies desired object attributes using templates and/or individual attributes |

### Response Payload

| Field              | Required             | Description                                                                      |
|--------------------|----------------------|----------------------------------------------------------------------------------|
| Object Type        | Yes                  | Type of object created.                                                          |
| Unique Identifier  | Yes, MAY be repeated | The list of Unique Identifiers of the newly created objects.                     |
| Template-Attribute | No                   | An OPTIONAL list of object attributes with values that were not specified in the |

## 4.39 Join Split Key

### Request Payload

| Field              | Required             | Description                                                                      |
|--------------------|----------------------|----------------------------------------------------------------------------------|
| Object Type        | Yes                  | Determines the type of object to be created.                                     |
| Unique Identifier  | Yes, MAY be repeated | Determines the Split Keys to be combined to form the object returned by the serv |
| Secret Data Type   | No                   | Determines which Secret Data type the Split Keys form.                           |
| Template-Attribute | No                   | Specifies desired object attributes using templates and/or individual attributes |

### Response Payload

| Field              | Required | Description                                                                      |
|--------------------|----------|----------------------------------------------------------------------------------|
| Object Type        | Yes      | Type of object created.                                                          |
| Unique Identifier  | Yes      | The Unique Identifier of the object obtained by combining the Split Keys.        |
| Template-Attribute | No       | An OPTIONAL list of object attributes with values that were not specified in the |

## 5.1 Notify

### Request Payload

_No fields (empty or not scraped)_

### Response Payload

_No fields (empty or not scraped)_

## 5.2 Put

### Request Payload

_No fields (empty or not scraped)_

### Response Payload

_No fields (empty or not scraped)_

