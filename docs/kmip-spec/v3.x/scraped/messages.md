# KMIP 3.0 — Messages (§8)

Total: **6** structures

## 8.1.1 Request Message

| Field          | Required                   | Comment |
|----------------|----------------------------|---------|
| Request Header | Structure                  | Yes     |
| Batch Item     | Structure, MAY be repeated | Yes     |

## 8.1.2 Request Header

| Field                           | Required            | Comment                          |
|---------------------------------|---------------------|----------------------------------|
| Protocol Version                | Yes                 |                                  |
| Maximum Response Size           | No                  |                                  |
| Client Correlation Value        | No                  |                                  |
| Server Correlation Value        | No                  |                                  |
| Asynchronous Indicator          | No                  |                                  |
| Attestation Capable Indicator   | No                  |                                  |
| Attestation Type                | No, MAY be repeated |                                  |
| Authentication                  | No                  |                                  |
| Batch Error Continuation Option | No                  | If omitted, then Stop is assumed |
| Time Stamp                      | No                  |                                  |

## 8.1.3 Request Batch Item

| Field             | Required            | Comment                                                                                                                                                |
|-------------------|---------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| Operation         | Yes                 |                                                                                                                                                        |
| Ephemeral         | No                  | Ephemeral Enumeration. Specifies how the Response Payload should be modified prior to return to the client in terms of which fields should be omitted. |
| Request Payload   | Yes                 | Structure, contents depend on the Operation                                                                                                            |
| Message Extension | No, MAY be repeated |                                                                                                                                                        |

## 8.2.1 Response Message

| Field           | Required                   | Comment |
|-----------------|----------------------------|---------|
| Response Header | Structure                  | Yes     |
| Batch Item      | Structure, MAY be repeated | Yes     |

## 8.2.2 Response Header

| Field                    | Required                                    | Comment                                                                                                                                                                                                                  |
|--------------------------|---------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Protocol Version         | Yes                                         |                                                                                                                                                                                                                          |
| Time Stamp               | Yes                                         |                                                                                                                                                                                                                          |
| Nonce                    | No                                          |                                                                                                                                                                                                                          |
| Server Hashed Password   | Yes, if Hashed Password credential was used | Hash( Timestamp || S1 || Hash(S2)), where S1, S2 and the Hash algorithm are defined in the Hashed Password credential. The client MUST check this value is correct prior to otherwise using the results from the server. |
| Attestation Type         | No, MAY be repeated                         | REQUIRED in Attestation Required error message if client set Attestation Capable Indicator to True in the request                                                                                                        |
| Client Correlation Value | No                                          |                                                                                                                                                                                                                          |
| Server Correlation Value | No                                          |                                                                                                                                                                                                                          |

## 8.2.3 Response Batch Item

| Field                          | Required                                | Comment                                                   |
|--------------------------------|-----------------------------------------|-----------------------------------------------------------|
| Operation                      | Yes, if specified in Request Batch Item |                                                           |
| Result Status                  | Yes                                     |                                                           |
| Result Reason                  | Yes, if Result Status is Failure        | REQUIRED if Result Status is Failure , otherwise OPTIONAL |
| Result Message                 | No                                      | OPTIONAL if Result Status is not Pending or Success       |
| Asynchronous Correlation Value | No                                      | REQUIRED if Result Status is Pending                      |
| Response Payload               | Yes, if not a failure                   | Structure, contents depend on the Operation               |
| Message Extension              | No                                      |                                                           |

