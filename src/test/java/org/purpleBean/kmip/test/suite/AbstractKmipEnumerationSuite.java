package org.purpleBean.kmip.test.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipEnumeration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base domain suite for objects implementing KmipEnumeration.
 * Extend this and provide canonical, equal, and different instances as needed.
 */
@DisplayName("Abstract KMIP Enumeration Suite")
public abstract class AbstractKmipEnumerationSuite<T extends KmipEnumeration> extends AbstractKmipDataTypeSuite<T> {

    /**
     * Provide an instance that should be equal to {@link #createDefault()}.
     * If null, equality test is skipped.
     */
    protected T createEqualToDefault() {
        return null;
    }

    /**
     * Provide an instance that should not be equal to {@link #createDefault()}.
     * If null, inequality test is skipped.
     */
    protected T createDifferentFromDefault() {
        return null;
    }

    /**
     * Override and return true if this enumeration supports runtime registry behavior (e.g., custom value registration).
     */
    protected boolean supportsRegistryBehavior() {
        return false;
    }

    /**
     * Override to assert registry behavior for the specific enumeration (positive cases: register, lookup by value/name).
     */
    protected void assertEnumerationRegistryBehaviorPositive() { /* no-op by default */ }

    /**
     * Override to assert negative registry behavior (invalid range, empty description, empty versions, etc.).
     */
    protected void assertEnumerationRegistryBehaviorNegative() { /* no-op by default */ }

    @Test
    @DisplayName("Enumeration: description is non-null and non-empty")
    void enumeration_description_present() {
        T obj = createDefault();
        String desc = obj.getDescription();
        assertThat(desc).isNotNull();
        assertThat(desc.trim()).isNotEmpty();
    }

    @Test
    @DisplayName("Enumeration: equals/hashCode contract for equal instances")
    void enumeration_equalsAndHashCode_equalInstances() {
        T a = createDefault();
        T b = createEqualToDefault();
        if (b == null) return; // implementor didn't supply; skip
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @Test
    @DisplayName("Enumeration: not equal for differing instances")
    void enumeration_notEqual_forDifferent() {
        T a = createDefault();
        T c = createDifferentFromDefault();
        if (c == null) return; // implementor didn't supply; skip
        assertThat(a).isNotEqualTo(c);
    }

    @Test
    @DisplayName("Enumeration: registry behavior (positive) (opt-in)")
    void enumeration_registry_behavior_positive() {
        if (supportsRegistryBehavior()) {
            assertEnumerationRegistryBehaviorPositive();
        }
    }

    @Test
    @DisplayName("Enumeration: registry behavior (negative) (opt-in)")
    void enumeration_registry_behavior_negative() {
        if (supportsRegistryBehavior()) {
            assertEnumerationRegistryBehaviorNegative();
        }
    }
}
