# KMIP 1.2 — Bit Masks

Total: **2** bit mask types

## Cryptographic Usage Mask

| Bit Name                             | Hex      |
|--------------------------------------|----------|
| Sign                                 | 00000001 |
| Verify                               | 00000002 |
| Encrypt                              | 00000004 |
| Decrypt                              | 00000008 |
| Wrap Key                             | 00000010 |
| Unwrap Key                           | 00000020 |
| Export                               | 00000040 |
| MAC Generate                         | 00000080 |
| MAC Verify                           | 00000100 |
| Derive Key                           | 00000200 |
| Content Commitment (Non Repudiation) | 00000400 |
| Key Agreement                        | 00000800 |
| Certificate Sign                     | 00001000 |
| CRL Sign                             | 00002000 |
| Generate Cryptogram                  | 00004000 |
| Validate Cryptogram                  | 00008000 |
| Translate Encrypt                    | 00010000 |
| Translate Decrypt                    | 00020000 |
| Translate Wrap                       | 00040000 |
| Translate Unwrap                     | 00080000 |

## Storage Status Mask

| Bit Name                        | Hex                                                                                                                               |
|---------------------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| On-line storage                 | 00000001                                                                                                                          |
| Archival storage                | 00000002                                                                                                                          |
| Protocol major version mismatch | Response message containing a header and a Batch Item without Operation, but with the Result Status field set to Operation Failed |
| Message cannot be parsed        | Response message containing a header and a Batch Item without Operation, but with the Result Status field set to Operation Failed |
| draft-01                        | 2013-03-28                                                                                                                        |
| draft-02                        | 2013-05-10                                                                                                                        |
| draft-03                        | 2013-05-12                                                                                                                        |
| draft-04                        | 2013-06-04                                                                                                                        |
| draft-05                        | 2013-06-10                                                                                                                        |
| draft-06                        | 2013-06-17                                                                                                                        |
| draft-07                        | 2013-08-08                                                                                                                        |
| draft-08                        | 2013-08-21                                                                                                                        |
| csd-01                          | 2013-09-12                                                                                                                        |
| csd-01 (revised)                | 2013-10-31                                                                                                                        |
| csd-01review1                   | 2014-06-03                                                                                                                        |
| csd-01review3                   | 2014-06-16                                                                                                                        |

