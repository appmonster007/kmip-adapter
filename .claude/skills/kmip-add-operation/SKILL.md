---
name: kmip-add-operation
description: Add a missing KMIP operation by implementing its RequestPayload and ResponsePayload structures, plus wiring them under api/request and api/response. Trigger when the user says "add KMIP operation X", "implement Import / Export / Reactivate / Login", or picks an item from kmip-pending-implementation.md §6.
---

# Add a missing KMIP operation

End-to-end workflow for implementing one of the operations listed in `docs/kmip-pending-implementation.md` §6 — either partially implemented (§6.1, currently just `Poll` missing its response) or fully missing (§6.2, ~23 operations from `Import` onward).

## Inputs needed from user
- **Operation name** (PascalCase, e.g., `Import`, `Export`, `Login`)
- (Optional) which half to implement: request, response, or both (default: both)

## Steps

### 1. Pull spec entry
From `docs/kmip-pending-implementation.md` §6 and the OASIS KMIP spec section for that operation, extract:
- Operation hex code (e.g., `0x0000002A` for Import)
- KMIP version introduced (e.g., v1.4, v2.0, v3.0)
- Request payload field list
- Response payload field list (note: some operations return only a status with no payload — the spec says so explicitly)

Cross-reference `docs/kmip-spec/kmip-all-versions-data.json` for tags.

### 2. Read a peer operation
Look at an existing complete operation pair under `src/main/java/org/purplebean/kmip/api/request/` and `.../api/response/`. Mirror its package layout and naming:
- `<Op>RequestPayload` and `<Op>ResponsePayload` are KMIP structures
- They typically live under `api/request/<op>/` and `api/response/<op>/` as sub-packages

Then check `Operation` enum (`src/main/java/.../model/core/enumeration/Operation.java`) and confirm `<Op>` is already a `Standard` value with the right version set.

### 3. Generate each payload as a structure
Use the `kmip-add-structure` skill **twice** — once for the request, once for the response:
```bash
./scripts/generators/generate.sh structure -s api/request --all <Op>RequestPayload
./scripts/generators/generate.sh structure -s api/response --all <Op>ResponsePayload
```
(Adjust `-s` so files land under `api/request/<op>/` and `api/response/<op>/` per the existing layout — read a peer to confirm.)

### 4. Fill in fields
For each payload, populate the `@NonNull` / optional fields, builders, `getValues()`, and `validate()` per the `kmip-add-structure` recipe.

### 5. Operation enum check
Confirm `Operation.Standard` contains the operation. If missing (rare — the project covers all 79+ operations in the enum even when payloads are missing), add it before the payload work.

### 6. Wire batch-item dispatch (if applicable)
KMIP's `BatchItem` payload uses the `Operation` enum to dispatch to the correct request/response class. Check whether the dispatch table (typically a registry or switch) needs an entry — search:
```bash
grep -rn "Operation.Standard.<OP>" src/main/java/
```
If a dispatch table exists and is missing your entry, add it.

### 7. Compile + test
```bash
mvn -q compile test-compile
mvn -q test -Dtest=<Op>RequestPayloadTest,<Op>RequestPayloadTtlvTest,<Op>RequestPayloadJsonTest,<Op>RequestPayloadXmlTest,<Op>ResponsePayloadTest,<Op>ResponsePayloadTtlvTest,<Op>ResponsePayloadJsonTest,<Op>ResponsePayloadXmlTest
```

### 8. Round-trip integration test
Add an integration-level test that constructs a full `RequestMessage` containing a `BatchItem` with the new payload, serializes it through TTLV, deserializes it back, and asserts the operation type round-trips. See existing operation tests for the pattern.

### 9. Report
- Both payload classes created
- Operation enum status (already present vs. added)
- Test results
- Recommend re-running `kmip-scrape-spec` to flip the §6 row from ❌ to ✅.

## Hard rules

- **Request and response payloads are independent classes** — never collapse into one.
- **Always check the `Operation` enum first.** A payload class is useless if the enum doesn't know about the operation.
- **Some operations return no payload** (response is just status + no body). In that case, do not generate a `<Op>ResponsePayload` — instead, confirm the existing response-dispatch code handles "no payload" for this op. The pending doc's §6.2 column may say so; if not, the spec is authoritative.
- **Operations with version-scoped fields** (e.g., v3.0 adds a new optional field to an existing v2.x operation) need the field's `@NonNull` removed and `validate()` should enforce per-version presence rules. Hand back to the architect if this is ambiguous.
