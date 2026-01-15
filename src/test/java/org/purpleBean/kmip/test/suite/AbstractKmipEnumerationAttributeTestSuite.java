package org.purpleBean.kmip.test.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipEnumeration;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.structure.Attribute;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Provides a comprehensive test suite for KMIP data types that function as both an enumeration and an attribute.
 * <p>
 * This abstract test suite is designed to validate the behavior of KMIP types that implement both the
 * {@link KmipEnumeration} and {@link KmipAttribute} interfaces. It extends {@link AbstractKmipEnumerationTestSuite}
 * to inherit enumeration-specific tests and adds further tests to cover attribute-related functionalities.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Dual-Interface Validation:</b> Ensures that the tested type correctly implements the contracts of both
 *       {@link KmipEnumeration} and {@link KmipAttribute}.</li>
 *   <li><b>Capability Flag Testing:</b> Verifies that attribute capability flags (e.g., {@code isAlwaysPresent},
 *       {@code isServerInitializable}) match the expected values defined in the concrete test class.</li>
 *   <li><b>State-Dependent Behavior:</b> Tests state-dependent methods like {@code isServerModifiable} and
 *       {@code isClientModifiable} using representative {@link State} objects.</li>
 *   <li><b>Attribute Round-Trip:</b> Confirms that an object can be converted to its generic {@link Attribute}
 *       representation and then reconstructed back into its original type without loss of information.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * To use this suite, create a concrete test class that extends this class and provides implementations for the
 * abstract methods. These methods define the expected behavior and provide necessary test instances.
 *
 * @param <T> The specific KMIP type being tested, which must implement both {@link KmipEnumeration} and
 *            {@link KmipAttribute}.
 * @see AbstractKmipEnumerationTestSuite
 * @see KmipEnumeration
 * @see KmipAttribute
 * @see Attribute
 */
@DisplayName("Abstract KMIP Attribute+Enumeration Suite")
public abstract class AbstractKmipEnumerationAttributeTestSuite<T extends KmipEnumeration & KmipAttribute>
        extends AbstractKmipEnumerationTestSuite<T> {

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
        T reconstructed = (T) Attribute.toKmipAttribute(attr);
        assertThat(obj.getAttributeValue()).isEqualTo(reconstructed.getAttributeValue());
    }
}