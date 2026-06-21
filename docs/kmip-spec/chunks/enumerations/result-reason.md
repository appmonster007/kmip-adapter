# Result Reason

- **Spec versions**: `v1.2` `v1.3` `v1.4` `v2.0` `v2.1` `v3.0`
- **Introduced**: v1.2

| Name | Value | Versions |
|---|---|---|
| Invalid Password | `` | `v2.0` `v2.1` `v3.0` |
| Item Not Found | `0x00000001` | `v1.2` `v1.3` `v1.4` |
| Response Too Large | `0x00000002` | `v1.2` `v1.3` `v1.4` |
| Authentication Not Successful | `0x00000003` | `v1.2` `v1.3` `v1.4` |
| Invalid Message | `0x00000004` | `v1.2` `v1.3` `v1.4` |
| Operation Not Supported | `0x00000005` | `v1.2` `v1.3` `v1.4` |
| Missing Data | `0x00000006` | `v1.2` `v1.3` `v1.4` |
| Invalid Field | `0x00000007` | `v1.2` `v1.3` `v1.4` |
| Feature Not Supported | `0x00000008` | `v1.2` `v1.3` `v1.4` |
| Operation Canceled By Requester | `0x00000009` | `v1.2` `v1.3` `v1.4` |
| Cryptographic Failure | `0x0000000A` | `v1.2` `v1.3` `v1.4` |
| Illegal Operation | `0x0000000B` | `v1.2` `v1.3` `v1.4` |
| Permission Denied | `0x0000000C` | `v1.2` `v1.3` `v1.4` |
| Object archived | `0x0000000D` | `v1.2` `v1.3` `v1.4` |
| Index Out of Bounds | `0x0000000E` | `v1.2` `v1.3` `v1.4` |
| Application Namespace Not Supported | `0x0000000F` | `v1.2` `v1.3` `v1.4` |
| Key Format Type Not Supported | `0x00000010` | `v1.2` `v1.3` `v1.4` |
| Key Compression Type Not Supported | `0x00000011` | `v1.2` `v1.3` `v1.4` |
| Encoding Option Error | `0x00000012` | `v1.2` `v1.3` `v1.4` |
| Key Value Not Present | `0x00000013` | `v1.2` `v1.3` `v1.4` |
| Attestation Required | `0x00000014` | `v1.2` `v1.3` `v1.4` |
| Attestation Failed | `0x00000015` | `v1.2` `v1.3` `v1.4` |
| Sensitive | `0x00000016` | `v1.4` |
| Not Extractable | `0x00000017` | `v1.4` |
| Object Already Exists | `0x00000018` | `v1.4` |
| General Failure | `0x00000100` | `v1.2` `v1.3` `v1.4` |
| Unknown Tag | `0x00AABEEE` | `v2.0` `v2.1` `v3.0` |
| Invalid Ticket | `0x00ECEAAD` | `v2.0` `v2.1` `v3.0` |
| Wrapping Object Not Found | `0x0ABECDEE` | `v2.0` `v2.1` `v3.0` |
| Wrapping Object Archived | `0xABECACED` | `v2.0` `v2.1` `v3.0` |
| Missing Initialization Vector | `0xEEEDFCEA` | `v2.0` `v2.1` `v3.0` |
| Invalid Correlation Value | `0xFEACACEA` | `v2.0` `v2.1` `v3.0` |
| Unknown Object Group | `<insert>` | `v2.1` |
| Circular Link Error | `A ParentLink sets up a directed acyclic relationship. Detection of a cycle in the relationship graph results in this reason code.` | `v3.0` |
| Invalid Data Type | `A data type was invalid for the requested operation` | `v2.0` `v2.1` `v3.0` |
| Key Value Not Present | `A meta data only object. The key value is not present on the server` | `v2.0` `v2.1` `v3.0` |
| Attribute Instance Not Found | `A referenced attribute was found, but the specific instance was not found` | `v2.0` `v2.1` `v3.0` |
| Attribute Not Found | `A referenced attribute was not found at all on an object` | `v2.0` `v2.1` `v3.0` |
| Object Not Found | `A requested managed object was not found or did not exist` | `v2.0` `v2.1` `v3.0` |
| Invalid Attribute | `An attribute is invalid for this object for this operation` | `v2.0` `v2.1` `v3.0` |
| Unknown Enumeration | `An enumerated value is not known by the server` | `v2.0` `v2.1` `v3.0` |
| Numeric Range | `An operation produced a number that is to large or too small to be stored in the specified data type` | `v2.0` `v2.1` |
| Numeric Range | `An operation produced a number that is too large or too small to be stored in the specified data type` | `v3.0` |
| Multi Valued Attribute | `Attempt to Set or Adjust an attribute that has multiple values` | `v2.0` `v2.1` `v3.0` |
| Attribute Single Instance | `Attempt to provide multiple values for a single instance attribute` | `v2.0` `v2.1` `v3.0` |
| Attribute Read Only | `Attempt to set a Read Only Attribute` | `v2.0` `v2.1` `v3.0` |
| Read Only Attribute | `Attempt to set a Read Only Attribute` | `v2.0` `v2.1` `v3.0` |
| Unsupported Attribute | `Attribute is valid in the specification but unsupported by the Server` | `v2.0` `v2.1` `v3.0` |
| Bad Cryptographic Parameters | `Bad Cryptographic Parameters` | `v2.0` `v2.1` `v3.0` |
| Illegal Object Type | `Check cannot be performed on this object type` | `v2.0` `v2.1` `v3.0` |
| Permission Denied | `Client is not allowed to perform the specified operation` | `v2.0` `v2.1` `v3.0` |
| Unsupported Cryptographic Parameters | `Cryptographic Parameters are valid in the specification but unsupported by the Server` | `v2.0` `v2.1` `v3.0` |
| Invalid CSR | `Invalid Certifcate Signing Request` | `v2.0` `v2.1` |
| Invalid CSR | `Invalid Certificate Signing Request` | `v3.0` |
| Object Type | `Invalid object type for the operation` | `v2.0` `v2.1` `v3.0` |
| Bad Password | `Key Format Type is PKCS#12, but missing or multiple PKCS#12 Password Links, or not Secret Data, or not Active` | `v2.0` `v2.1` `v3.0` |
| Key Wrap Type Not Supported | `Key Wrap Type Type is not supported by the server` | `v2.0` `v2.1` `v3.0` |
| Response Too Large | `Maximum Response Size has been exceeded` | `v2.0` `v2.1` `v3.0` |
| Item Not Found | `No object with the specified Unique Identifier exists` | `v2.0` `v2.1` `v3.0` |
| Invalid Asynchronous Correlation Value | `No outstanding operation with the specified Asynchronous Correlation Value exists` | `v2.0` `v2.1` `v3.0` |
| Object Destroyed | `Object exists, but has already been destroyed` | `v2.0` `v2.1` `v3.0` |
| Not Extractable | `Object is not Extractable` | `v2.0` `v2.1` `v3.0` |
| Attestation Failed | `Operation requires attestation data and the attestation data provided by the client does not validate` | `v2.0` `v2.1` `v3.0` |
| Attestation Required | `Operation requires attestation data which was not provided by the client, and the client has set the Attestation Capable indicator to True` | `v2.0` `v2.1` `v3.0` |
| Sensitive | `Sensitive keys may not be retrieved unwrapped` | `v2.0` `v2.1` `v3.0` |
| Server Limit Exceeded | `Some limit on the server such as database size has been exceeded` | `v2.0` `v2.1` `v3.0` |
| Invalid Object Type | `Specificed object is not valid for the requested operation` | `v2.0` |
| Invalid Object Type | `Specified object is not valid for the requested operation` | `v2.1` `v3.0` |
| Encoding Option Error | `The Encoding Option is not supported as specified by the Encoding Option Enumeration` | `v2.0` `v2.1` `v3.0` |
| PKCS#11 Invalid Function | `The PKCS function is not in the interface` | `v2.0` `v2.1` `v3.0` |
| Duplicate Process Request | `The asynchronous request specified was already processed` | `v2.1` `v3.0` |
| Authentication not successful | `The authentication information in the request could not be validated, or was not found` | `v2.0` `v2.1` `v3.0` |
| Incompatible Cryptographic Usage Mask | `The cryptographic algorithm or other parameters is not valid for the requested operation` | `v2.0` `v2.1` `v3.0` |
| PKCS#11 Invalid Interface | `The interface is unknown or unavailable in the server` | `v2.0` `v2.1` `v3.0` |
| Wrong Key Lifecycle State | `The key lifecycle state is invalid for the operation, for example not Active for an Encrypt operation` | `v2.0` `v2.1` `v3.0` |
| Codec Error | `The low level TTLV, XML, JSON etc. was badly formed and not understood by the server.TTLV connections should be closed as future requests might not be correctly separated` | `v2.0` `v2.1` `v3.0` |
| Object Archived | `The object SHALL be recovered from the archive before performing the operation` | `v2.0` `v2.1` `v3.0` |
| Wrapping Object Destroyed | `The object exists, but is destroyed` | `v2.0` `v2.1` `v3.0` |
| Key Compression Type Not Supported | `The object exists, but the server is unable to provide it in the desired Key Compression Type` | `v2.0` `v2.1` `v3.0` |
| Key Format Type Not Supported | `The object exists, but the server is unable to provide it in the desired Key Format Type` | `v2.0` `v2.1` `v3.0` |
| Missing data | `The operation REQUIRED additional information in the request, which was not present` | `v2.0` `v2.1` `v3.0` |
| Unsupported Protocol Version | `The operation cannot be performed with the provided protocol version` | `v2.0` `v2.1` `v3.0` |
| Protection Storage Unavailable, Private Protection Storage Unavailable, Public Protection Storage Unavailable | `The operation could not be completed with the protections requested (or defaulted).` | `v2.0` `v2.1` `v3.0` |
| Cryptographic Failure | `The operation failed due to a cryptographic error` | `v2.0` `v2.1` `v3.0` |
| Feature Not Supported | `The operation is supported, but not a specific feature specified in the request is not supported` | `v2.0` `v2.1` `v3.0` |
| Operation Not Supported | `The operation requested by the request message is not supported by the server` | `v2.0` `v2.1` `v3.0` |
| Operation canceled by requester | `The operation was asynchronous, and the operation was canceled by the Cancel operation before it completed successfully` | `v2.0` `v2.1` `v3.0` |
| Constraint Violation | `The request failed because one or more constraints were violated` | `v2.1` `v3.0` |
| General failure | `The request failed for a reason other than the defined reasons above` | `v2.0` `v2.1` `v3.0` |
| Invalid Field | `The request is syntactically valid but some data in the request (other than an attribute value) has an invalid value` | `v2.0` `v2.1` `v3.0` |
| Invalid Message | `The request message was not syntactically understood by the server. For example - the invalid use of a known tag` | `v2.0` `v2.1` `v3.0` |
| Unknown Message Extension | `The server does not support the supplied Message Extension` | `v2.0` `v2.1` `v3.0` |
| Internal Server Error | `The server had an internal error and could not process the request at this time.` | `v2.0` `v2.1` `v3.0` |
| Usage Limit Exceeded | `The usage limits or request count has been exceeded` | `v2.0` `v2.1` `v3.0` |
| Invalid Attribute Value | `The value supplied for an attribute is invalid` | `v2.0` `v2.1` `v3.0` |
| PKCS#11 Codec Error | `There is a Codec error in the Input parameter` | `v2.0` `v2.1` `v3.0` |
| Non Unique Name Attribute | `Trying to perform an operation that requests the server to break the constraint on Name attribute being unique` | `v2.0` `v2.1` `v3.0` |
| Object Already Exists | `for operations such as Import that require that no object with a specific unique identifier exists on a server` | `v2.0` `v2.1` `v3.0` |
