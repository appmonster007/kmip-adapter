# Story 4.6: Unify Remaining Attribute Test Suites to Interface Pattern

## 1. Story Requirements

**As a developer,**
I want any remaining attribute test classes still extending abstract base classes migrated to the `KmipSerializationTestSuite` interface pattern,
So that the attribute test suite is consistent with the rest of the library's test approach.

### 1.1. Acceptance Criteria

**Given** all attribute test classes are audited for abstract base class usage
**When** any are found that do not implement `KmipSerializationTestSuite` (or its typed specialization)
**Then** they are migrated to the interface pattern following the established convention from commit `f778fbf3`

**Given** the migration is complete
**When** all tests run
**Then** no test regressions are introduced and JaCoCo coverage does not decrease

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to refactor the remaining attribute test suites.

The `generate.sh` script's test generation capabilities will be used to audit and update the attribute test classes.

The process will be:
1.  Use the `bmad-generate-kmip-code` skill to identify all attribute test classes that do not implement the `KmipSerializationTestSuite` interface.
2.  For each identified test class, use the `generate.sh` script to regenerate the test suite using the interface pattern. For example:

```bash
./scripts/generators/generate.sh attribute --name <AttributeName> --with-tests
```

This will ensure that all attribute test suites are migrated to the `KmipSerializationTestSuite` interface pattern, improving consistency across the library's test suite and fulfilling all acceptance criteria.
