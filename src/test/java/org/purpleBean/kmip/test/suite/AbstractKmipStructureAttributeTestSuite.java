package org.purpleBean.kmip.test.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.KmipStructure;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.Attribute;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Provides a comprehensive test suite for KMIP data types that function as both a structure and an attribute.
 * <p>
 * This abstract test suite is designed to validate the behavior of KMIP types that implement both the
 * {@link KmipStructure} and {@link KmipAttribute} interfaces. It extends {@link AbstractKmipStructureTestSuite}
 * to inherit structure-specific tests and adds further tests to cover attribute-related functionalities.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Dual-Interface Validation:</b> Ensures that the tested type correctly implements the contracts of both
 *       {@link KmipStructure} and {@link KmipAttribute}.</li>
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
 * @param <T> The specific KMIP type being tested, which must implement both {@link KmipStructure} and
 *           {@link KmipAttribute}.
 *
 * @see AbstractKmipStructureTestSuite
 * @see KmipStructure
 * @see KmipAttribute
 * @see Attribute
 */
@DisplayName("Abstract KMIP Attribute+Structure Suite")
public abstract class AbstractKmipStructureAttributeTestSuite<T extends KmipStructure & KmipAttribute>
        extends AbstractKmipStructureTestSuite<T> {

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
    @DisplayName("Attr+Struct: capability flags match expectations")
    protected void attrStruct_capabilityFlags_matchExpectations() {
        T obj = createDefault();
        assertThat(obj.isAlwaysPresent()).isEqualTo(expectAlwaysPresent());
        assertThat(obj.isServerInitializable()).isEqualTo(expectServerInitializable());
        assertThat(obj.isClientInitializable()).isEqualTo(expectClientInitializable());
        assertThat(obj.isClientDeletable()).isEqualTo(expectClientDeletable());
        assertThat(obj.isMultiInstanceAllowed()).isEqualTo(expectMultiInstanceAllowed());
    }

    @Test
    @DisplayName("Attr+Struct: server modifiable respects state")
    protected void attrStruct_serverModifiable_respectsState() {
        T obj = createDefault();
        assertThat(obj.isServerModifiable(stateForServerModifiableTrue())).isTrue();
        assertThat(obj.isServerModifiable(stateForServerModifiableFalse())).isFalse();
    }

    @Test
    @DisplayName("Attr+Struct: client modifiable respects state")
    protected void attrStruct_clientModifiable_respectsState() {
        T obj = createDefault();
        assertThat(obj.isClientModifiable(stateForClientModifiableTrue())).isTrue();
        assertThat(obj.isClientModifiable(stateForClientModifiableFalse())).isFalse();
    }

    @Test
    @DisplayName("Attr+Struct: get AttributeValue and rebuild the object")
    protected void attrStruct_attributeValue_roundTrip() {
        T obj = createDefault();
        Attribute attr = Attribute.of(obj);
        T reconstructed = (T) Attribute.toKmipAttribute(attr);
        assertThat(obj.getAttributeValue()).isEqualTo(reconstructed.getAttributeValue());
    }
}