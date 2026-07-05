# Story 4.4b: Complete Test Suites for Epic 2 Payloads

## 1. Story Requirements

**As a developer,**
I want full `KmipSerializationTestSuite` test suites for all payloads implemented in Epic 2 (GetConstraints, SetConstraints, SetDefaults, QueryAsyncRequests request+response, Poll, Pkcs11 request+response, Import, Export request+response, Process request+response — 11 payloads),
So that Epic 2 payload implementations are verified correct and regressions are detected.

### 1.1. Acceptance Criteria

**Given** each of the 11 Epic 2 payloads now has a full implementation
**When** its test suite is upgraded with concrete test data (minimum: one minimal instance, one fully-populated instance per payload)
**Then** TTLV, JSON, and XML serialization tests all pass for every payload

**Given** any Epic 2 test still has `// TODO validateComponents` annotations
**When** this story is complete
**Then** all such annotations are resolved

**Given** Epic 2 test suites run
**When** JaCoCo coverage is measured for Epic 2 classes
**Then** line coverage is ≥90% for all Epic 2 payload classes

## 2. Developer Context

### 2.1. Technical Requirements

- Complete the test suites for all 11 payloads from Epic 2.
- Remove all `// TODO validateComponents` annotations.
- Achieve ≥90% line coverage for these classes.

### 2.2. Architecture Compliance

- This task involves updating existing test classes to be more comprehensive.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- **Files to modify:**
    - Test classes for all Epic 2 payloads.

### 2.5. Testing Requirements

- This story is all about testing.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
