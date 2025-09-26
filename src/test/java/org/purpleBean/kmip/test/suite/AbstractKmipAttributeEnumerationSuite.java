package org.purpleBean.kmip.test.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.KmipEnumeration;
import org.purpleBean.kmip.common.enumeration.State;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Combined domain suite for types that implement both KmipAttribute and KmipEnumeration.
 * <p>
 * Type bound ensures the concrete type implements both interfaces.
 * This suite combines the testing capabilities of both AbstractKmipAttributeSuite 
 * and AbstractKmipEnumerationSuite.
 */
@DisplayName("Abstract KMIP Attribute+Enumeration Suite")
public abstract class AbstractKmipAttributeEnumerationSuite<T extends KmipEnumeration & KmipAttribute>
        extends AbstractKmipEnumerationSuite<T> {

    // Expectations for flag-like capabilities (from KmipAttribute)
    protected abstract boolean expectAlwaysPresent();

    protected abstract boolean expectServerInitializable();

    protected abstract boolean expectClientInitializable();

    protected abstract boolean expectClientDeletable();

    protected abstract boolean expectMultiInstanceAllowed();

    // Representative states to exercise state-dependent methods (from KmipAttribute)
    protected abstract State stateForServerModifiableTrue();

    protected abstract State stateForServerModifiableFalse();

    protected abstract State stateForClientModifiableTrue();

    protected abstract State stateForClientModifiableFalse();

    @Test
    @DisplayName("AttrEnum: capability flags match expectations")
    void attrEnum_capabilityFlags_matchExpectations() {
        T obj = createDefault();
        assertThat(obj.isAlwaysPresent()).isEqualTo(expectAlwaysPresent());
        assertThat(obj.isServerInitializable()).isEqualTo(expectServerInitializable());
        assertThat(obj.isClientInitializable()).isEqualTo(expectClientInitializable());
        assertThat(obj.isClientDeletable()).isEqualTo(expectClientDeletable());
        assertThat(obj.isMultiInstanceAllowed()).isEqualTo(expectMultiInstanceAllowed());
    }

    @Test
    @DisplayName("AttrEnum: server modifiable respects state")
    void attrEnum_serverModifiable_respectsState() {
        T obj = createDefault();
        assertThat(obj.isServerModifiable(stateForServerModifiableTrue())).isTrue();
        assertThat(obj.isServerModifiable(stateForServerModifiableFalse())).isFalse();
    }

    @Test
    @DisplayName("AttrEnum: client modifiable respects state")
    void attrEnum_clientModifiable_respectsState() {
        T obj = createDefault();
        assertThat(obj.isClientModifiable(stateForClientModifiableTrue())).isTrue();
        assertThat(obj.isClientModifiable(stateForClientModifiableFalse())).isFalse();
    }

    @Test
    @DisplayName("AttrEnum: enumeration description is consistent with attribute behavior")
    void attrEnum_description_consistentWithAttributeBehavior() {
        T obj = createDefault();
        String desc = obj.getDescription();
        
        // Description should be non-null and non-empty (from enumeration contract)
        assertThat(desc).isNotNull();
        assertThat(desc.trim()).isNotEmpty();
        
        // Description should be consistent with KMIP tag description (attribute contract)
        String tagDesc = obj.getKmipTag().getDescription();
        assertThat(tagDesc).isNotNull();
        assertThat(tagDesc.trim()).isNotEmpty();
        
        // They should be related (often the same, but allow for variations)
        // This is a soft assertion - implementors can override if needed
        assertDescriptionConsistency(desc, tagDesc);
    }

    /**
     * Override this method if you need custom logic for validating description consistency
     * between the enumeration description and the KMIP tag description.
     * 
     * @param enumDesc the description from KmipEnumeration.getDescription()
     * @param tagDesc the description from KmipTag.getDescription()
     */
    protected void assertDescriptionConsistency(String enumDesc, String tagDesc) {
        // Default implementation: they should be equal (case-insensitive)
        assertThat(enumDesc.toLowerCase()).isEqualTo(tagDesc.toLowerCase());
    }
}
