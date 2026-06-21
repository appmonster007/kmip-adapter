## 7. Missing and Changed Structures

### 7.1 Missing v3.0 Structures

| Structure | Tag | Description | Implemented? |
|---|---|---|---|
| PasswordCredential | `0x4201A1` | Password credential type — fields: Password Salt, Password Salt Algorithm, Salted Password | ✅ |
| OtpCredential | `0x4201A7` | One-Time Password credential — fields: OTP Algorithm/Digest/Serial/Seed/Interval/Digits/Counter | ✅ |
| HashedPasswordCredential | `0x4201AF` | Hashed password credential — fields: Hashed Username Password, Hashed Password Username | ✅ |
| DeactivationReason | `0x4201B8` | Deactivation structure — fields: Deactivation Message, Deactivation Reason Code | ✅ |
| CredentialInformation | `0x4201B2` | Credential metadata aggregate structure | ✅ |
| CertificateLink | `0x420190` | Typed link replacing generic Link | ✅ |
| ChildLink | `0x420191` | Typed link replacing generic Link | ✅ |
| DerivationObjectLink | `0x420192` | Typed link replacing generic Link | ✅ |
| DerivedObjectLink | `0x420193` | Typed link replacing generic Link | ✅ |
| NextLink | `0x420194` | Typed link replacing generic Link | ✅ |
| ParentLink | `0x420195` | Typed link replacing generic Link | ✅ |
| Pkcs12CertificateLink | `0x420196` | Typed link replacing generic Link | ✅ |
| Pkcs12PasswordLink | `0x420197` | Typed link replacing generic Link | ✅ |
| PreviousLink | `0x420198` | Typed link replacing generic Link | ✅ |
| PrivateKeyLink | `0x420199` | Typed link replacing generic Link | ✅ |
| PublicKeyLink | `0x42019A` | Typed link replacing generic Link | ✅ |
| ReplacedObjectLink | `0x42019B` | Typed link replacing generic Link | ✅ |
| ReplacementObjectLink | `0x42019C` | Typed link replacing generic Link | ✅ |
| WrappingKeyLink | `0x42019D` | Typed link replacing generic Link | ✅ |

### 7.2 Structural Breaking Changes by Version

| Object | Version | Change |
|---|---|---|
| **Custom Attribute (42002D)** | v2.0 | Tag became **Reserved**. Replaced by Vendor Extension. `CustomAttribute.java` must not emit for v2.0+. |
| **Attribute Index (420009)** | v2.0 | Tag became **Reserved**. Was an integer attribute qualifier in v1.x. |
| **Common Template-Attribute (42001F)** | v2.0 | Tag became **Reserved**. Replaced by Common Attributes (420126). |
| **Link (42004A/B/C)** | v3.0 | Generic `Link` + `Link Type` + `Linked Object Identifier` tags all **Reserved**. Replaced by 14 typed link structures at 0x420190–0x42019D. |
| **Name sub-structure** | v3.0 | `Name Type` (420054) and `Name Value` (420055) tags **Reserved**. Name (420053) carries the string value directly. |
| **Batch Count (42000D)** | v3.0 | Tag **Reserved**. Must not appear in v3.0 Request Headers. |
| **Batch Order Option (420010)** | v3.0 | Tag **Reserved**. Must not appear in v3.0 Request Headers. |
| **Unique Batch Item ID (420093)** | v3.0 | Tag **Reserved**. Must not appear in v3.0 Batch Items. |
| **Object Group (420056)** | v3.0 | Tag **Reserved**. Replaced by Object Groups aggregate (420166). |
| **Template ObjectType (0x6)** | v3.0 | ObjectType value `Template` is **Reserved** (removed) in v3.0. |
| **Credential structure** | v2.0→v3.0 | New credential value types per version: v2.0 adds OneTimePassword/HashedPassword/Ticket; v3.0 adds Password/Certificate — requires new credential value structure classes. |
| **Certificate Subject / Issuer** | v2.0 | Old CertificateSubject (42001A) and CertificateIssuer (420015) tags became **Reserved**. Replaced by CertificateSubject CN/O/OU/… and Issuer CN/O/OU/… sub-tags (0x420108–0x42011F). |
