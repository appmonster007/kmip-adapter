package org.purpleBean.kmip.test.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.KmipEnumeration;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.Attribute;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Combined domain suite for types that implement both KmipAttribute and KmipEnumeration.
 * <p>
 * Type bound ensures the concrete type implements both interfaces.
 * This suite combines the testing capabilities of both AbstractKmipDataTypeAttributeSuite
 * and AbstractKmipEnumerationSuite.
 */
@DisplayName("Abstract KMIP Attribute+Enumeration Suite")
public abstract class AbstractKmipEnumerationAttributeSuite<T extends KmipEnumeration & KmipAttribute>
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
    protected void attrEnum_capabilityFlags_matchExpectations() {
        T obj = createDefault();
        assertThat(obj.isAlwaysPresent()).isEqualTo(expectAlwaysPresent());
        assertThat(obj.isServerInitializable()).isEqualTo(expectServerInitializable());
        assertThat(obj.isClientInitializable()).isEqualTo(expectClientInitializable());
        assertThat(obj.isClientDeletable()).isEqualTo(expectClientDeletable());
        assertThat(obj.isMultiInstanceAllowed()).isEqualTo(expectMultiInstanceAllowed());
    }

    @Test
    @DisplayName("AttrEnum: server modifiable respects state")
    protected void attrEnum_serverModifiable_respectsState() {
        T obj = createDefault();
        assertThat(obj.isServerModifiable(stateForServerModifiableTrue())).isTrue();
        assertThat(obj.isServerModifiable(stateForServerModifiableFalse())).isFalse();
    }

    @Test
    @DisplayName("AttrEnum: client modifiable respects state")
    protected void attrEnum_clientModifiable_respectsState() {
        T obj = createDefault();
        assertThat(obj.isClientModifiable(stateForClientModifiableTrue())).isTrue();
        assertThat(obj.isClientModifiable(stateForClientModifiableFalse())).isFalse();
    }


    @Test
    @DisplayName("AttrEnum: get AttributeValue and rebuild the object")
    protected void attrEnum_attributeValue_roundTrip() {
        T obj = createDefault();
        Attribute attr = Attribute.of(obj);
        T reconstructed = (T) attr.toKmipAttribute(attr);
        assertThat(obj.getAttributeValue()).isEqualTo(reconstructed.getAttributeValue());
    }
}
