# Asynchronous Indicator

- **Spec versions**: `v2.0` `v2.1` `v3.0`
- **Introduced**: v2.0

| Name | Value | Versions |
|---|---|---|
| Optional | `The server MAY process each batch item in the request either asynchronously (returning an Asynchronous Correlation Value for a batch item) or synchronously. The method or policy by which the server determines whether or not to process an individual batch item asynchronously is a decision of the server and is outside of the scope of this protocol.` | `v2.0` `v2.1` `v3.0` |
| Prohibited | `The server SHALL NOT process any batch item asynchronously. All batch items SHALL be processed synchronously.` | `v2.0` `v2.1` `v3.0` |
