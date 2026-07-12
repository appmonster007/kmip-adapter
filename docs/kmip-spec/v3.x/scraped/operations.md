# KMIP 3.0 — Operations

Total: **67** operations

## 6.1.1 Activate

### Request Payload

| Field             | Required | Description                            |
|-------------------|----------|----------------------------------------|
| Unique Identifier | Yes      | Determines the object being activated. |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 6.1.2 Add Attribute

### Request Payload

| Field             | Required | Description                                        |
|-------------------|----------|----------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object.               |
| New Attribute     | Yes      | Specifies the attribute to be added to the object. |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 6.1.3 Adjust Attribute

### Request Payload

| Field               | Required | Description                          |
|---------------------|----------|--------------------------------------|
| Unique Identifier   | Yes      | The Unique Identifier of the object. |
| Attribute Reference | Yes      | The attribute to be adjusted.        |
| Adjustment Type     | Yes      | The adjustment to be made.           |
| Adjustment Value    | No       | The value for the adjustment         |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 6.1.4 Archive

### Request Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 6.1.5 Cancel

### Request Payload

| Field                          | Required | Description                           |
|--------------------------------|----------|---------------------------------------|
| Asynchronous Correlation Value | Yes      | Specifies the request being canceled. |

### Response Payload

| Field                          | Required | Description                                            |
|--------------------------------|----------|--------------------------------------------------------|
| Asynchronous Correlation Value | Yes      | Specified in the request.                              |
| Cancellation Result            | Yes      | Enumeration indicating the result of the cancellation. |

## 6.1.6 Certify

### Request Payload

| Field                     | Required | Description                                                                                                              |
|---------------------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier         | No       | The Unique Identifier of the Public Key or the Certificate Request being certified.                                      |
| Certificate Request Type  | No       | An Enumeration object specifying the type of certificate request. It is REQUIRED if the Certificate Request Value is pre |
| Certificate Request Value | No       | A Byte String object with the certificate request.                                                                       |
| Attributes                | No       | Specifies desired object attributes.                                                                                     |
| Protection Storage Masks  | No       | Specifies all permissible Protection Storage Mask selections for the new object                                          |

### Response Payload

| Field             | Required | Description                                                |
|-------------------|----------|------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the generated Certificate object. |

## 6.1.7 Check

### Request Payload

| Field                    | Required | Description                                                                                          |
|--------------------------|----------|------------------------------------------------------------------------------------------------------|
| Unique Identifier        | Yes      | The Unique Identifier of the object.                                                                 |
| Usage Limits Count       | No       | Specifies the number of Usage Limits Units to be protected to be checked against server policy.      |
| Cryptographic Usage Mask | No       | Specifies the Cryptographic Usage for which the client intends to use the object.                    |
| Lease Time               | No       | Specifies a Lease Time value that the Client is asking the server to validate against server policy. |

### Response Payload

| Field                    | Required               | Description                                                                                                              |
|--------------------------|------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier        | Yes, unless a failure, | The Unique Identifier of the object.                                                                                     |
| Usage Limits Count       | No                     | Returned by the Server if the Usage Limits value specified in the Request Payload is larger than the value that the serv |
| Cryptographic Usage Mask | No                     | Returned by the Server if the Cryptographic Usage Mask specified in the Request Payload is rejected by the server for po |
| Lease Time               | No                     | Returned by the Server if the Lease Time value in the Request Payload is larger than a valid Lease Time that the server  |

## 6.1.8 Create

### Request Payload

| Field                    | Required | Description                                                                     |
|--------------------------|----------|---------------------------------------------------------------------------------|
| Object Type              | Yes      | Determines the type of object to be created.                                    |
| Attributes               | Yes      | Specifies desired attributes to be associated with the new object.              |
| Protection Storage Masks | No       | Specifies all permissible Protection Storage Mask selections for the new object |

### Response Payload

| Field             | Required | Description                                        |
|-------------------|----------|----------------------------------------------------|
| Object Type       | Yes      | Type of object created.                            |
| Unique Identifier | Yes      | The Unique Identifier of the newly created object. |

## 6.1.9 Create Credential

### Request Payload

| Field                                                                                                   | Required | Description                                                        |
|---------------------------------------------------------------------------------------------------------|----------|--------------------------------------------------------------------|
| Credential Type                                                                                         | Yes      | Determines the type of credential object to be created.            |
| Attributes                                                                                              | Yes      | Specifies desired attributes to be associated with the new object. |
| Password Credential or Device Credential or Hashed Password Credential or OTP Credential or Certificate |          |                                                                    |

### Response Payload

| Field             | Required | Description                                        |
|-------------------|----------|----------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the newly created object. |

## 6.1.10 Create Group

### Request Payload

| Field      | Required | Description                                                        |
|------------|----------|--------------------------------------------------------------------|
| Attributes | Yes      | Specifies desired attributes to be associated with the new object. |

### Response Payload

| Field             | Required | Description                                        |
|-------------------|----------|----------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the newly created object. |

## 6.1.11 Create Key Pair

### Request Payload

| Field                            | Required | Description                                                                                                              |
|----------------------------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| Common Attributes                | No       | Specifies desired attributes to be associated with the new object that apply to both the Private and Public Key Objects. |
| Private Key Attributes           | No       | Specifies the attributes to be associated with the new object that apply to the Private Key Object.                      |
| Public Key Attributes            | No       | Specifies the attributes to be associated with the new object that apply to the Public Key Object.                       |
| Common Protection Storage Masks  | No       | Specifies all Protection Storage Mask selections that are permissible for the new Private Key and Public Key objects.    |
| Private Protection Storage Masks | No       | Specifies all Protection Storage Mask selections that are permissible for the new Private Key object.                    |
| Public Protection Storage Masks  | No       | Specifies all Protection Storage Mask selections that are permissible for the new Public Key object.                     |

### Response Payload

| Field                         | Required | Description                                                    |
|-------------------------------|----------|----------------------------------------------------------------|
| Private Key Unique Identifier | Yes      | The Unique Identifier of the newly created Private Key object. |
| Public Key Unique Identifier  | Yes      | The Unique Identifier of the newly created Public Key object.  |

## 6.1.12 Create Split Key

### Request Payload

| Field                    | Required | Description                                                                     |
|--------------------------|----------|---------------------------------------------------------------------------------|
| Object Type              | Yes      | Determines the type of object to be created.                                    |
| Unique Identifier        | No       | The Unique Identifier of the key to be split (if applicable).                   |
| Split Key Parts          | Yes      | The total number of parts.                                                      |
| Split Key Threshold      | Yes      | The minimum number of parts needed to reconstruct the entire key.               |
| Split Key Method         | Yes      |                                                                                 |
| Prime Field Size         | No       |                                                                                 |
| Attributes               | Yes      | Specifies desired object attributes.                                            |
| Protection Storage Masks | No       | Specifies all permissible Protection Storage Mask selections for the new object |

### Response Payload

| Field             | Required             | Description                                                  |
|-------------------|----------------------|--------------------------------------------------------------|
| Unique Identifier | Yes, MAY be repeated | The list of Unique Identifiers of the newly created objects. |

## 6.1.13 Create User

### Request Payload

| Field      | Required | Description                                                        |
|------------|----------|--------------------------------------------------------------------|
| Attributes | Yes      | Specifies desired attributes to be associated with the new object. |

### Response Payload

| Field             | Required | Description                                        |
|-------------------|----------|----------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the newly created object. |

## 6.1.14 Deactivate

### Request Payload

| Field               | Required | Description                                            |
|---------------------|----------|--------------------------------------------------------|
| Unique Identifier   | Yes      | The Unique Identifier of the object..                  |
| Deactivation Reason | No       | Specifies the reason for deactivation.                 |
| Deactivation Date   | No       | Specifies the date on which the deactivation occurred. |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 6.1.15 Decrypt

### Request Payload

| Field                                    | Required                                | Description                                                                                                              |
|------------------------------------------|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier                        | Yes                                     | The Unique Identifier of the Managed Cryptographic Object that is the key to use for the decryption operation.           |
| Cryptographic Parameters                 | No                                      | The Cryptographic Parameters (Block Cipher Mode, Padding Method) corresponding to the particular decryption method reque |
| Data                                     | Yes for single-part. No for multi-part. | The data to be decrypted.                                                                                                |
| IV/Counter/Nonce                         | No                                      | The initialization vector, counter or nonce to be used (where appropriate).                                              |
| Correlation Value                        | No                                      | Specifies the existing stream or by-parts cryptographic operation (as returned from a previous call to this operation).  |
| Init Indicator                           | No                                      | Initial operation as Boolean                                                                                             |
| Final Indicator                          | No                                      | Final operation as Boolean                                                                                               |
| Authenticated Encryption Additional Data | No                                      | Additional data to be authenticated via the Authenticated Encryption Tag. If supplied in multi-part decryption, this dat |
| Authenticated Encryption Tag             | No                                      | Specifies the tag that will be needed to authenticate the decrypted data and the additional authenticated data. If suppl |

### Response Payload

| Field             | Required | Description                                                                                                              |
|-------------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the Managed Cryptographic Object that is the key used for the decryption operation.             |
| Data              | No.      | The decrypted data (as a Byte String).                                                                                   |
| Correlation Value | No       | Specifies the stream or by-parts value to be provided in subsequent calls to this operation for performing cryptographic |

## 6.1.16 Delegated Login

### Request Payload

| Field         | Required | Description                                                                                                              |
|---------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| Lease Time    | No       | The lease time Interval or Date Time for the ticket.                                                                     |
| Request Count | No       | The integer count of the number of requests that can be made with the ticket                                             |
| Usage Limits  | No       | The usage limits for operations performed.                                                                               |
| Rights        | Yes      | List of Rights granted to the ticket holder which may only perform operations allowed by at least one of the contained R |

### Response Payload

| Field  | Required | Description                 |
|--------|----------|-----------------------------|
| Ticket | Yes      | The Ticket that is returned |

## 6.1.17 Delete Attribute

### Request Payload

| Field               | Required | Description                                                                         |
|---------------------|----------|-------------------------------------------------------------------------------------|
| Unique Identifier   | Yes      | Determines the object whose attributes are being deleted.                           |
| Current Attribute   | No       | Specifies the attribute associated with the object to be deleted.                   |
| Attribute Reference | No       | Specifies the reference for the attribute associated with the object to be deleted. |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 6.1.18 Derive Key

### Request Payload

| Field                 | Required             | Description                                                                                                              |
|-----------------------|----------------------|--------------------------------------------------------------------------------------------------------------------------|
| Object Type           | Yes                  | Determines the type of object to be created.                                                                             |
| Unique Identifier     | Yes. MAY be repeated | Determines the object or objects to be used to derive a new key.                                                         |
| Derivation Method     | Yes                  | An Enumeration object specifying the method to be used to derive the new key.                                            |
| Derivation Parameters | Yes                  | A Structure object containing the parameters needed by the specified derivation method.                                  |
| Attributes            | Yes                  | Specifies desired attributes to be associated with the new object; the length and algorithm SHALL always be specified fo |

### Response Payload

| Field             | Required | Description                                                           |
|-------------------|----------|-----------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the newly derived key or Secret Data object. |

## 6.1.19 Destroy

### Request Payload

| Field             | Required | Description                            |
|-------------------|----------|----------------------------------------|
| Unique Identifier | Yes      | Determines the object being destroyed. |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 6.1.20 Discover Versions

### Request Payload

| Field            | Required            | Description                                                                                      |
|------------------|---------------------|--------------------------------------------------------------------------------------------------|
| Protocol Version | No, MAY be Repeated | The list of protocol versions supported by the client ordered in decreasing order of preference. |

### Response Payload

| Field            | Required            | Description                                                                                      |
|------------------|---------------------|--------------------------------------------------------------------------------------------------|
| Protocol Version | No, MAY be repeated | The list of protocol versions supported by the server ordered in decreasing order of preference. |

## 6.1.21 Encrypt

### Request Payload

| Field                                    | Required                                | Description                                                                                                              |
|------------------------------------------|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier                        | Yes                                     | The Unique Identifier of the Managed Cryptographic Object that is the key to use for the encryption operation.           |
| Cryptographic Parameters                 | No                                      | The Cryptographic Parameters (Block Cipher Mode, Padding Method, RandomIV ) corresponding to the particular encryption m |
| Data                                     | Yes for single-part. No for multi-part. | The data to be.                                                                                                          |
| IV/Counter/Nonce                         | No                                      | The initialization vector, counter or nonce to be used (where appropriate).                                              |
| Correlation Value                        | No                                      | Specifies the existing stream or by-parts cryptographic operation (as returned from a previous call to this operation).  |
| Init Indicator                           | No                                      | Initial operation as Boolean                                                                                             |
| Final Indicator                          | No                                      | Final operation as Boolean                                                                                               |
| Authenticated Encryption Additional Data | No                                      | Any additional data to be authenticated via the Authenticated Encryption Tag. If supplied in multi-part encryption, this |

### Response Payload

| Field                        | Required | Description                                                                                                              |
|------------------------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier            | Yes      | The Unique Identifier of the Managed Cryptographic Object that was the key used for the encryption operation.            |
| Data                         | No.      | The encrypted data (as a Byte String).                                                                                   |
| IV/Counter/Nonce             | No       | The value used if the Cryptographic Parameters specified Random IV and the IV/Counter/Nonce value was not provided in th |
| Correlation Value            | No       | Specifies the stream or by-parts value to be provided in subsequent calls to this operation for performing cryptographic |
| Authenticated Encryption Tag | No       | Specifies the tag that will be needed to authenticate the decrypted data (and any “additional data”). Only returned on c |

## 6.1.22 Export

### Request Payload

| Field                      | Required | Description                                                            |
|----------------------------|----------|------------------------------------------------------------------------|
| Unique Identifier          | Yes      | The Unique Identifier of the object.                                   |
| Key Format Type            | No       | Determines the key format type to be returned.                         |
| Key Wrap Type              | No       | Determines the Key Wrap Type of the returned key value.                |
| Key Compression Type       | No       | Determines the compression method for elliptic curve public keys.      |
| Key Wrapping Specification | No       | Specifies keys and other information for wrapping the returned object. |

### Response Payload

| Field                  | Required | Description                                                               |
|------------------------|----------|---------------------------------------------------------------------------|
| Object Type            | Yes      | Type of object                                                            |
| Unique Identifier      | Yes      | The Unique Identifier of the object.                                      |
| Attributes             | Yes      | All of the object’s Attributes.                                           |
| Any Object (Section 2) | Yes      | The object value being returned, in the same manner as the Get operation. |

## 6.1.23 Get

### Request Payload

| Field                      | Required | Description                                                            |
|----------------------------|----------|------------------------------------------------------------------------|
| Unique Identifier          | Yes      | The Unique Identifier of the object.                                   |
| Key Format Type            | No       | Determines the key format type to be returned.                         |
| Key Wrap Type              | No       | Determines the Key Wrap Type of the returned key value.                |
| Key Compression Type       | No       | Determines the compression method for elliptic curve public keys.      |
| Key Wrapping Specification | No       | Specifies keys and other information for wrapping the returned object. |

### Response Payload

| Field                  | Required | Description                          |
|------------------------|----------|--------------------------------------|
| Object Type            | Yes      | Type of object.                      |
| Unique Identifier      | Yes      | The Unique Identifier of the object. |
| Any Object (Section 2) | Yes      | The object being returned.           |

## 6.1.24 Get Attributes

### Request Payload

| Field               | Required            | Description                                        |
|---------------------|---------------------|----------------------------------------------------|
| Unique Identifier   | Yes                 | The Unique Identifier of the object.               |
| Attribute Reference | No, MAY be repeated | Specifies an attribute associated with the object. |

### Response Payload

| Field             | Required | Description                                          |
|-------------------|----------|------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object.                 |
| Attributes        | Yes      | The requested attributes associated with the object. |

## 6.1.25 Get Attribute List

### Request Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

### Response Payload

| Field               | Required             | Description                                |
|---------------------|----------------------|--------------------------------------------|
| Unique Identifier   | Yes                  | The Unique Identifier of the object.       |
| Attribute Reference | Yes, MAY be repeated | The attributes associated with the object. |

## 6.1.26 Get Constraints

### Request Payload

_No fields (empty payload per spec)_

### Response Payload

| Field       | Required | Description                                                      |
|-------------|----------|------------------------------------------------------------------|
| Constraints | Yes      | The set of Constraints that are being applied during operations. |

## 6.1.27 Get Usage Allocation

### Request Payload

| Field              | Required | Description                                       |
|--------------------|----------|---------------------------------------------------|
| Unique Identifier  | Yes      | The Unique Identifier of the object.              |
| Usage Limits Count | Yes      | The number of Usage Limits Units to be protected. |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 6.1.28 Hash

### Request Payload

| Field                    | Required                                | Description                                                                                                             |
|--------------------------|-----------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| Cryptographic Parameters | Yes                                     | The Cryptographic Parameters (Hashing Algorithm) corresponding to the particular hash method requested.                 |
| Data                     | Yes for single-part. No for multi-part. | The data to be hashed .                                                                                                 |
| Correlation Value        | No                                      | Specifies the existing stream or by-parts cryptographic operation (as returned from a previous call to this operation). |
| Init Indicator           | No                                      | Initial operation as Boolean                                                                                            |
| Final Indicator          | No                                      | Final operation as Boolean                                                                                              |

### Response Payload

| Field             | Required                                | Description                                                                                                              |
|-------------------|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Data              | Yes for single-part. No for multi-part. | The hashed data (as a Byte String).                                                                                      |
| Correlation Value | No                                      | Specifies the stream or by-parts value to be provided in subsequent calls to this operation for performing cryptographic |

## 6.1.29 Import

### Request Payload

| Field                  | Required                                  | Description                                                                                                              |
|------------------------|-------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier      | Yes                                       | The Unique Identifier of the object to be imported                                                                       |
| Object Type            | Yes                                       | Determines the type of object being imported.                                                                            |
| Replace Existing       | No                                        | A Boolean. If specified and true then any existing object with the same Unique Identifier SHALL be replaced by this oper |
| Key Wrap Type          | If and only if the key object is wrapped. | If Not Wrapped then the server SHALL unwrap the object before storing it, and return an error if the wrapping key is not |
| Attributes             | Yes                                       | Specifies object attributes to be associated with the new object.                                                        |
| Any Object (Section 2) | Yes                                       | The object being imported. The object and attributes MAY be wrapped.                                                     |

### Response Payload

| Field             | Required | Description                                         |
|-------------------|----------|-----------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the newly imported object. |

## 6.1.30 Interop

### Request Payload

| Field              | Required | Description                                                     |
|--------------------|----------|-----------------------------------------------------------------|
| Interop Function   | Yes      | The function to be performed                                    |
| Interop Identifier | Yes      | The identifier if the test case to be submitted as a TextString |

### Response Payload

_No fields (empty payload per spec)_

## 6.1.31 Join Split Key

### Request Payload

| Field                    | Required             | Description                                                                                                              |
|--------------------------|----------------------|--------------------------------------------------------------------------------------------------------------------------|
| Object Type              | Yes                  | Determines the type of object to be created.                                                                             |
| Unique Identifier        | Yes, MAY be repeated | Determines the Split Keys to be combined to form the object returned by the server. The minimum number of identifiers is |
| Secret Data Type         | No                   | Determines which Secret Data type the Split Keys form.                                                                   |
| Attributes               | No                   | Specifies desired object attributes.                                                                                     |
| Protection Storage Masks | No                   | Specifies all permissible Protection Storage Mask selections for the new object                                          |

### Response Payload

| Field             | Required | Description                                                               |
|-------------------|----------|---------------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object obtained by combining the Split Keys. |

## 6.1.32 Locate

### Request Payload

| Field               | Required | Description                                                                                                              |
|---------------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| Maximum Items       | No       | An Integer object that indicates the maximum number of object identifiers the server MAY return.                         |
| Offset Items        | No       | An Integer object that indicates the number of object identifiers to skip that satisfy the identification criteria speci |
| Storage Status Mask | No       | An Integer object (used as a bit mask) that indicates whether only on-line objects, only archived objects, destroyed obj |
| Object Class Mask   | No       | An Integer object (used as a bit mask) that indicates which classes of objects are to be searched. If omitted, then only |
| Attributes          | Yes      | Specifies an attribute and its value(s) that are REQUIRED to match those in a candidate object (according to the matchin |

### Response Payload

| Field             | Required            | Description                                                                                                              |
|-------------------|---------------------|--------------------------------------------------------------------------------------------------------------------------|
| Located Items     | No                  | An Integer object that indicates the number of object identifiers that satisfy the identification criteria specified in  |
| Unique Identifier | No, MAY be repeated | The Unique Identifier of the located objects.                                                                            |

## 6.1.33 Log

### Request Payload

| Field       | Required | Description        |
|-------------|----------|--------------------|
| Log Message | Yes      | The message to log |

### Response Payload

_No fields (empty payload per spec)_

## 6.1.34 Login

### Request Payload

| Field         | Required | Description                                                                  |
|---------------|----------|------------------------------------------------------------------------------|
| Lease Time    | No       | The lease time Interval or Date Time for the ticket                          |
| Request Count | No       | The integer count of the number of requests that can be made with the ticket |
| Usage Limits  | No       | The usage limits for the operations performed                                |

### Response Payload

| Field  | Required | Description                 |
|--------|----------|-----------------------------|
| Ticket | Yes      | The ticket that is returned |

## 6.1.35 Logout

### Request Payload

| Field  | Required | Description                  |
|--------|----------|------------------------------|
| Ticket | Yes      | The ticket to be invalidated |

### Response Payload

_No fields (empty payload per spec)_

## 6.1.36 MAC

### Request Payload

| Field                    | Required                                | Description                                                                                                              |
|--------------------------|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier        | Yes                                     | The Unique Identifier of the Managed Cryptographic Object that is the key to use for the MAC operation.                  |
| Cryptographic Parameters | No                                      | The Cryptographic Parameters (Cryptographic Algorithm) corresponding to the particular MAC method requested. If there ar |
| Data                     | Yes for single-part. No for multi-part. | The data to be MACed .                                                                                                   |
| Correlation Value        | No                                      | Specifies the existing stream or by-parts cryptographic operation (as returned from a previous call to this operation).  |
| Init Indicator           | No                                      | Initial operation as Boolean                                                                                             |
| Final Indicator          | No                                      | Final operation as Boolean                                                                                               |

### Response Payload

| Field             | Required                               | Description                                                                                                              |
|-------------------|----------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier | Yes                                    | The Unique Identifier of the Managed Cryptographic Object that is the key used for the MAC operation.                    |
| MAC Data          | Yes for single-part. No for multi-part | The data MACed (as a Byte String).                                                                                       |
| Correlation Value | No                                     | Specifies the stream or by-parts value to be provided in subsequent calls to this operation for performing cryptographic |

## 6.1.37 MAC Verify

### Request Payload

| Field                    | Required                                | Description                                                                                                              |
|--------------------------|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier        | Yes                                     | The Unique Identifier of the Managed Cryptographic Object that is the key to use for the MAC verify operation.           |
| Cryptographic Parameters | No                                      | The Cryptographic Parameters (Cryptographic Algorithm) corresponding to the particular MAC method requested. If there ar |
| Data                     | No                                      | The data that was MACed .                                                                                                |
| MAC Data                 | Yes for single-part. No for multi-part. | The data to be MAC verified (as a Byte String).                                                                          |
| Correlation Value        | No                                      | Specifies the existing stream or by-parts cryptographic operation (as returned from a previous call to this operation).  |
| Init Indicator           | No                                      | Initial operation as Boolean                                                                                             |
| Final Indicator          | No                                      | Final operation as Boolean                                                                                               |

### Response Payload

| Field              | Required                                | Description                                                                                                              |
|--------------------|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier  | Yes                                     | The Unique Identifier of the Managed Cryptographic Object that is the key used for the verification operation.           |
| Validity Indicator | Yes for single-part. No for multi-part. | An Enumeration object indicating whether the MAC is valid, invalid, or unknown.                                          |
| Correlation Value  | No                                      | Specifies the stream or by-parts value to be provided in subsequent calls to this operation for performing cryptographic |

## 6.1.38 Modify Attribute

### Request Payload

| Field             | Required | Description                                                                       |
|-------------------|----------|-----------------------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object.                                              |
| Current Attribute | No       | Specifies the existing attribute value associated with the object to be modified. |
| New Attribute     | Yes      | Specifies the new value for the attribute associated with the object .            |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 6.1.39 Obliterate

### Request Payload

| Field             | Required | Description                              |
|-------------------|----------|------------------------------------------|
| Unique Identifier | Yes      | Determines the object being obliterated. |

### Response Payload

_No fields (empty payload per spec)_

## 6.1.40 Obtain Lease

### Request Payload

| Field             | Required | Description                                                  |
|-------------------|----------|--------------------------------------------------------------|
| Unique Identifier | Yes      | Determines the object for which the lease is being obtained. |

### Response Payload

| Field             | Required | Description                                                                                                              |
|-------------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object.                                                                                     |
| Lease Time        | Yes      | An interval (in seconds) that specifies the amount of time that the object MAY be used until a new lease needs to be obt |
| Last Change Date  | Yes      | The date and time indicating when the latest change was made to the contents or any attribute of the specified object.   |

## 6.1.41 Ping

### Request Payload

_No fields (empty payload per spec)_

### Response Payload

_No fields (empty payload per spec)_

## 6.1.42 PKCS#11

### Request Payload

| Field                    | Required | Description                                                                                                        |
|--------------------------|----------|--------------------------------------------------------------------------------------------------------------------|
| PKCS#11 Interface        | No       | The name of the interface. If absent, the default V3.0 interface which defines the functions supported.            |
| PKCS#11 Function         | Yes      | The function to perform. An Enumeration for PKCS#11 defined functions or an Integer for vendor defined function.   |
| Correlation Value        | No       | Must be returned to the server if provided in a previous response.                                                 |
| PKCS#11 Input Parameters | No       | The parameters to the function. The format is specified in the PKCS#11 Profile and the [PKCS#11] standard document |

### Response Payload

| Field                     | Required | Description                                                                                                              |
|---------------------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| PKCS#11 Interface         | No       | The name of the interface. If absent, the default V3.0 interface is used.                                                |
| PKCS#11 Function          | Yes      | The function that was performed. An Enumeration for PKCS#11 defined functions or an Integer for vendor defined function. |
| Correlation Value         | No       | Server defined Byte String that the client must provide in the next request.                                             |
| PKCS#11 Output Parameters | No       | The parameters output from the function. The format is specified in the PKCS#11 Profile [KMIP-Prof] and the [PKCS#11] st |
| PKCS#11 Return Code       | Yes      | The PKCS#11 return code as specified in the CK_RV values in [PKCS#11]                                                    |

## 6.1.43 Poll

### Request Payload

| Field                          | Required | Description                         |
|--------------------------------|----------|-------------------------------------|
| Asynchronous Correlation Value | Yes      | Specifies the request being polled. |

### Response Payload

_No fields (empty payload per spec)_

## 6.1.44 Process

### Request Payload

| Field                          | Required | Description                                                                                 |
|--------------------------------|----------|---------------------------------------------------------------------------------------------|
| Asynchronous Correlation Value | Yes      | The value of the Asynchronous Correlation Value for the Batch Item to be made “synchronous” |

### Response Payload

_No fields (empty payload per spec)_

## 6.1.45 Query

### Request Payload

| Field          | Required             | Description                               |
|----------------|----------------------|-------------------------------------------|
| Query Function | Yes, MAY be Repeated | Determines the information being queried. |

### Response Payload

| Field                      | Required                                                             | Description                                                                                                              |
|----------------------------|----------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Operation                  | No, MAY be repeated                                                  | Specifies an Operation that is supported by the server.                                                                  |
| Object Type                | No, MAY be repeated                                                  | Specifies a Managed Object Type that is supported by the server.                                                         |
| Vendor Identification      | No                                                                   | SHALL be returned if Query Server Information is requested. The Vendor Identification SHALL be a text string that unique |
| Server Information         | No                                                                   | Contains vendor-specific information possibly be of interest to the client.                                              |
| Application Namespace      | No, MAY be repeated                                                  | Specifies an Application Namespace supported by the server.                                                              |
| Extension Information      | No, MAY be repeated                                                  | SHALL be returned if Query Extension List or Query Extension Map is requested and supported by the server.               |
| Attestation Type           | No, MAY be repeated                                                  | Specifies an Attestation Type that is supported by the server.                                                           |
| RNG Parameters             | No, MAY be repeated                                                  | Specifies the RNG that is supported by the server.                                                                       |
| Profile Information        | No, MAY be repeated                                                  | Specifies the Profiles that are supported by the server.                                                                 |
| Validation Information     | No, MAY be repeated                                                  | Specifies the validations that are supported by the server.                                                              |
| Capability Information     | No                                                                   | Specifies the capabilities that are supported by the server.                                                             |
| Client Registration Method | No, MAY be repeated                                                  | Specifies a Client Registration Method that is supported by the server.                                                  |
| Defaults Information       | No                                                                   | Specifies the defaults that the server will use if the client omits them.                                                |
| Protection Storage Masks   | Yes , if Protection Storage Mask is contained in the Query Function. | Specifies the list of Protection Storage Mask values supported by the server. A server MAY elect to provide an empty lis |
| Credential Information     | Yes, if Credential Information is contained in the Query Function    | Specifies the list of Credential types supported.                                                                        |

## 6.1.46 Query Asynchronous Requests

### Request Payload

| Field                           | Required | Description                                                       |
|---------------------------------|----------|-------------------------------------------------------------------|
| Asynchronous Correlation Values | No       | Structure containing zero or more Asynchronous Correlation Value. |
| Operations                      | No       | Structure Containing zero or more Operation .                     |

### Response Payload

| Field                | Required            | Description                                              |
|----------------------|---------------------|----------------------------------------------------------|
| Asynchronous Request | No, MAY be repeated | The details regarding a particular asynchronous request, |

## 6.1.47 Recover

### Request Payload

| Field             | Required | Description                            |
|-------------------|----------|----------------------------------------|
| Unique Identifier | Yes      | Determines the object being recovered. |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 6.1.48 Register

### Request Payload

| Field                    | Required | Description                                                                     |
|--------------------------|----------|---------------------------------------------------------------------------------|
| Object Type              | Yes      | Determines the type of object being registered.                                 |
| Attributes               | Yes      | Specifies desired object attributes to be associated with the new object.       |
| Any Object (Section 2)   | Yes      | The object being registered. The object and attributes MAY be wrapped.          |
| Protection Storage Masks | No       | Specifies all permissible Protection Storage Mask selections for the new object |

### Response Payload

| Field             | Required | Description                                           |
|-------------------|----------|-------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the newly registered object. |

## 6.1.49 Revoke

### Request Payload

| Field                      | Required | Description                                                   |
|----------------------------|----------|---------------------------------------------------------------|
| Unique Identifier          | Yes      | Determines the object being revoked.                          |
| Revocation Reason          | No       | Specifies the reason for revocation.                          |
| Compromise Occurrence Date | No       | Specifies the date on which the compromise occurred if known. |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object. |

## 6.1.50 Re-certify

### Request Payload

| Field                                 | Required | Description                                                                                                              |
|---------------------------------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier                     | Yes      | The Unique Identifier of the Certificate being renewed.                                                                  |
| Certificate Request Unique Identifier | No       | The Unique Identifier of the Certificate Request.                                                                        |
| Certificate Request Type              | No       | An Enumeration object specifying the type of certificate request. It is REQUIRED if the Certificate Request is present.  |
| Certificate Request Value             | No       | A Byte String object with the certificate request.                                                                       |
| Offset                                | No       | An Interval object indicating the difference between the Initial Date of the new certificate and the Activation Date of  |
| Attributes                            | No       | Specifies desired object attributes.                                                                                     |
| Protection Storage Masks              | No       | Specifies all permissible Protection Storage Mask selections for the new object                                          |

### Response Payload

| Field             | Required | Description                                   |
|-------------------|----------|-----------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the new certificate. |

## 6.1.51 Re-key

### Request Payload

| Field                    | Required | Description                                                                                                              |
|--------------------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier        | Yes      | Determines the existing Symmetric Key being re-keyed.                                                                    |
| Offset                   | No       | An Interval object indicating the difference between the Initial Date and the Activation Date of the replacement key to  |
| Attributes               | No       | Specifies desired object attributes.                                                                                     |
| Protection Storage Masks | No       | Specifies all permissible Protection Storage Mask selections for the new object                                          |

### Response Payload

| Field             | Required | Description                                                           |
|-------------------|----------|-----------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the newly-created replacement Symmetric Key. |

## 6.1.52 Re-key Key Pair

### Request Payload

| Field                            | Required | Description                                                                                                              |
|----------------------------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier                | Yes      | Determines the existing Asymmetric key pair to be re-keyed.                                                              |
| Offset                           | No       | An Interval object indicating the difference between the Initial Date and the Activation Date of the replacement key pai |
| Common Attributes                | No       | Specifies desired attributes that apply to both the Private and Public Key Objects.                                      |
| Private Key Attributes           | No       | Specifies attributes that apply to the Private Key Object.                                                               |
| Public Key Attributes            | No       | Specifies attributes that apply to the Public Key Object.                                                                |
| Common Protection Storage Masks  | No       | Specifies all Protection Storage Mask selections that are permissible for the new Private Key and new Public Key objects |
| Private Protection Storage Masks | No       | Specifies all Protection Storage Mask selections that are permissible for the new Private Key object.                    |
| Public Protection Storage Masks  | No       | Specifies all Protection Storage Mask selections that are permissible for the new Public Key object.                     |

### Response Payload

| Field                         | Required | Description                                                                |
|-------------------------------|----------|----------------------------------------------------------------------------|
| Private Key Unique Identifier | Yes      | The Unique Identifier of the newly created replacement Private Key object. |
| Public Key Unique Identifier  | Yes      | The Unique Identifier of the newly created replacement Public Key object.  |

## 6.1.53 Re-Provision

### Request Payload

| Field               | Required | Description                                         |
|---------------------|----------|-----------------------------------------------------|
| Certificate Request | No       | The certificate request to be signed                |
| Certificate         | No       | The certificate to replace the existing certificate |

### Response Payload

| Field             | Required | Description                                      |
|-------------------|----------|--------------------------------------------------|
| Unique Identifier | No       | The Certificate or Private Key unique identifier |

## 6.1.54 RNG Retrieve

### Request Payload

| Field       | Required | Description                                                             |
|-------------|----------|-------------------------------------------------------------------------|
| Data Length | Yes      | The amount of random number generator output to be returned (in bytes). |

### Response Payload

| Field | Required | Description                         |
|-------|----------|-------------------------------------|
| Data  | Yes      | The random number generator output. |

## 6.1.55 RNG Seed

### Request Payload

| Field | Required | Description                                                       |
|-------|----------|-------------------------------------------------------------------|
| Data  | Yes      | The data to be provided as a seed to the random number generator. |

### Response Payload

| Field       | Required | Description                              |
|-------------|----------|------------------------------------------|
| Data Length | Yes      | The amount of seed data used (in bytes). |

## 6.1.56 Set Attribute

### Request Payload

| Field             | Required | Description                                                           |
|-------------------|----------|-----------------------------------------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the object.                                  |
| New Attribute     | Yes      | Specifies the new value for the attribute associated with the object. |

### Response Payload

| Field             | Required | Description                          |
|-------------------|----------|--------------------------------------|
| Unique Identifier | Yes      | The Unique Identifier of the Object. |

## 6.1.57 Set Constraints

### Request Payload

| Field       | Required | Description                                        |
|-------------|----------|----------------------------------------------------|
| Constraints | Yes      | The set of Constraints to apply during operations. |

### Response Payload

_No fields (empty payload per spec)_

## 6.1.58 Set Defaults

### Request Payload

| Field                | Required | Description                                                                                                              |
|----------------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| Defaults Information | No       | The set of Object Defaults to begin using. If no Defaults Information is supplied, the semantic is to remove all Object  |

### Response Payload

_No fields (empty payload per spec)_

## 6.1.59 Set Endpoint Role

### Request Payload

| Field         | Required | Description                                |
|---------------|----------|--------------------------------------------|
| Endpoint Role | Yes      | The endpoint role for the server to apply. |

### Response Payload

| Field         | Required | Description                                          |
|---------------|----------|------------------------------------------------------|
| Endpoint Role | Yes      | The accepted endpoint role as applied by the server. |

## 6.1.60 Sign

### Request Payload

| Field                    | Required                                                                   | Description                                                                                                              |
|--------------------------|----------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier        | Yes                                                                        | The Unique Identifier of the Managed Cryptographic Object that is the key to use for the signature operation.            |
| Cryptographic Parameters | No                                                                         | The Cryptographic Parameters (Digital Signature Algorithm or Cryptographic Algorithm and Hashing Algorithm) correspondin |
| Data                     | Yes for single-part, unless Digested Data is supplied.. No for multi-part. | The data to be.                                                                                                          |
| Digested Data            | No                                                                         | The digested data to be signed (as a Byte String).                                                                       |
| Correlation Value        | No                                                                         | Specifies the existing stream or by-parts cryptographic operation (as returned from a previous call to this operation).  |
| Init Indicator           | No                                                                         | Initial operation as Boolean                                                                                             |
| Final Indicator          | No                                                                         | Final operation as Boolean                                                                                               |

### Response Payload

| Field             | Required                                | Description                                                                                                              |
|-------------------|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier | Yes                                     | The Unique Identifier of the Managed Cryptographic Object that is the key used for the signature operation.              |
| Signature Data    | Yes for single-part. No for multi-part. | The signed data (as a Byte String).                                                                                      |
| Correlation Value | No                                      | Specifies the stream or by-parts value to be provided in subsequent calls to this operation for performing cryptographic |

## 6.1.61 Signature Verify

### Request Payload

| Field                    | Required                                | Description                                                                                                              |
|--------------------------|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier        | Yes                                     | The Unique Identifier of the Managed Cryptographic Object that is the key to use for the signature verify operation.     |
| Cryptographic Parameters | No                                      | The Cryptographic Parameters (Digital Signature Algorithm or Cryptographic Algorithm and Hashing Algorithm) correspondin |
| Data                     | No                                      | The data that was.                                                                                                       |
| Digested Data            | No                                      | The digested data to be verified (as a Byte String)                                                                      |
| Signature Data           | Yes for single-part. No for multi-part. | The signature to be verified (as a Byte String).                                                                         |
| Correlation Value        | No                                      | Specifies the existing stream or by-parts cryptographic operation (as returned from a previous call to this operation).  |
| Init Indicator           | No                                      | Initial operation as Boolean                                                                                             |
| Final Indicator          | No                                      | Final operation as Boolean                                                                                               |

### Response Payload

| Field              | Required                                | Description                                                                                                              |
|--------------------|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Unique Identifier  | Yes                                     | The Unique Identifier of the Managed Cryptographic Object that is the key used for the verification operation.           |
| Validity Indicator | Yes for single-part. No for multi-part. | An Enumeration object indicating whether the signature is valid, invalid, or unknown.                                    |
| Data               | No                                      | The OPTIONAL recovered data (as a Byte String) for those signature algorithms where data recovery from the signature is  |
| Correlation Value  | No                                      | Specifies the stream or by-parts value to be provided in subsequent calls to this operation for performing cryptographic |

## 6.1.62 Validate

### Request Payload

| Field             | Required            | Description                                                                                                              |
|-------------------|---------------------|--------------------------------------------------------------------------------------------------------------------------|
| Certificate       | No, MAY be repeated | One or more Certificates.                                                                                                |
| Unique Identifier | No, MAY be repeated | One or more Unique Identifiers of Certificate Objects.                                                                   |
| Validity Date     | No                  | A Date-Time object indicating when the certificate chain needs to be valid. If omitted, the current date and time SHALL  |

### Response Payload

| Field              | Required | Description                                                                                   |
|--------------------|----------|-----------------------------------------------------------------------------------------------|
| Validity Indicator | Yes      | An Enumeration object indicating whether the certificate chain is valid, invalid, or unknown. |

## 6.2.1 Discover Versions

### Request Payload

| Field            | Required            | Description                                                                                      |
|------------------|---------------------|--------------------------------------------------------------------------------------------------|
| Protocol Version | No, MAY be Repeated | The list of protocol versions supported by the server ordered in decreasing order of preference. |

### Response Payload

| Field            | Required            | Description                                                                                      |
|------------------|---------------------|--------------------------------------------------------------------------------------------------|
| Protocol Version | No, MAY be repeated | The list of protocol versions supported by the client ordered in decreasing order of preference. |

## 6.2.2 Notify

### Request Payload

_No fields (empty payload per spec)_

### Response Payload

_No fields (empty payload per spec)_

## 6.2.3 Put

### Request Payload

_No fields (empty payload per spec)_

### Response Payload

_No fields (empty payload per spec)_

## 6.2.4 Query

### Request Payload

| Field          | Required             | Description                               |
|----------------|----------------------|-------------------------------------------|
| Query Function | Yes, MAY be Repeated | Determines the information being queried. |

### Response Payload

| Field                      | Required            | Description                                                                                                              |
|----------------------------|---------------------|--------------------------------------------------------------------------------------------------------------------------|
| Operation                  | No, MAY be repeated | Specifies an Operation that is supported by the client.                                                                  |
| Object Type                | No, MAY be repeated | Specifies a Managed Object Type that is supported by the client.                                                         |
| Vendor Identification      | No                  | SHALL be returned if Query Server Information is requested. The Vendor Identification SHALL be a text string that unique |
| Server Information         | No                  | Contains vendor-specific information in response to the Query.                                                           |
| Extension Information      | No, MAY be repeated | SHALL be returned if Query Extension List or Query Extension Map is requested and supported by the client.               |
| Attestation Type           | No, MAY be repeated | Specifies an Attestation Type that is supported by the client.                                                           |
| RNG Parameters             | No, MAY be repeated | Specifies the RNG that is supported by the client.                                                                       |
| Profile Information        | No, MAY be repeated | Specifies the Profiles that are supported by the client.                                                                 |
| Validation Information     | No, MAY be repeated | Specifies the validations that are supported by the client.                                                              |
| Capability Information     | No, MAY be repeated | Specifies the capabilities that are supported by the client.                                                             |
| Client Registration Method | No, MAY be repeated | Specifies a Client Registration Method that is supported by the client.                                                  |

## 6.2.5 Set Endpoint Role

### Request Payload

| Field         | Required | Description                                |
|---------------|----------|--------------------------------------------|
| Endpoint Role | Yes      | The endpoint role for the client to apply. |

### Response Payload

| Field         | Required | Description                                          |
|---------------|----------|------------------------------------------------------|
| Endpoint Role | Yes      | The accepted endpoint role as applied by the client. |

