package org.purpleBean.kmip.test.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.Attribute;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base domain suite for objects implementing KmipAttribute.
 * Implementors provide expectations and representative states where required.
 */
@DisplayName("Abstract KMIP Attribute Suite")
public abstract class AbstractKmipDataTypeAttributeSuite<T extends KmipAttribute> extends AbstractKmipDataTypeSuite<T> {

    // Expectations for flag-like capabilities
    protected abstract boolean expectAlwaysPresent();

    protected abstract boolean expectServerInitializable();

    protected abstract boolean expectClientInitializable();

    protected abstract boolean expectClientDeletable();

    protected abstract boolean expectMultiInstanceAllowed();

    // Representative states to exercise state-dependent methods
    protected abstract State stateForServerModifiableTrue();

    protected abstract State stateForServerModifiableFalse();

    protected abstract State stateForClientModifiableTrue();

    protected abstract State stateForClientModifiableFalse();

    @Test
    @DisplayName("Attribute: capability flags match expectations")
    protected void attribute_capabilityFlags_matchExpectations() {
        T attr = createDefault();
        assertThat(attr.isAlwaysPresent()).isEqualTo(expectAlwaysPresent());
        assertThat(attr.isServerInitializable()).isEqualTo(expectServerInitializable());
        assertThat(attr.isClientInitializable()).isEqualTo(expectClientInitializable());
        assertThat(attr.isClientDeletable()).isEqualTo(expectClientDeletable());
        assertThat(attr.isMultiInstanceAllowed()).isEqualTo(expectMultiInstanceAllowed());
    }

    @Test
    @DisplayName("Attribute: server modifiable respects state")
    protected void attribute_serverModifiable_respectsState() {
        T attr = createDefault();
        assertThat(attr.isServerModifiable(stateForServerModifiableTrue())).isTrue();
        assertThat(attr.isServerModifiable(stateForServerModifiableFalse())).isFalse();
    }

    @Test
    @DisplayName("Attribute: client modifiable respects state")
    protected void attribute_clientModifiable_respectsState() {
        T attr = createDefault();
        assertThat(attr.isClientModifiable(stateForClientModifiableTrue())).isTrue();
        assertThat(attr.isClientModifiable(stateForClientModifiableFalse())).isFalse();
    }


    @Test
    @DisplayName("Attribute: get AttributeValue and rebuild the object")
    protected void attrEnum_attributeValue_roundTrip() {
        T obj = createDefault();
        Attribute attr = Attribute.of(obj);
        T reconstructed = (T) attr.toKmipAttribute(attr);
        assertThat(obj.getAttributeValue()).isEqualTo(reconstructed.getAttributeValue());
    }
}
