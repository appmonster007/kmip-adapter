package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationAttributeSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("ObjectType Domain Tests")
class ObjectTypeTest extends AbstractKmipEnumerationAttributeSuite<ObjectType> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ObjectType> type() {
        return ObjectType.class;
    }

    @Override
    protected ObjectType createDefault() {
        return new ObjectType(ObjectType.Standard.CERTIFICATE);
    }

    @Override
    protected ObjectType createEqualToDefault() {
        return new ObjectType(ObjectType.Standard.CERTIFICATE);
    }

    @Override
    protected ObjectType createDifferentFromDefault() {
        return new ObjectType(ObjectType.Standard.SYMMETRIC_KEY);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.ENUMERATION;
    }

    @Override
    protected boolean supportsRegistryBehavior() {
        return true;
    }

    // Implementation of AbstractKmipDataTypeAttributeSuite methods
    @Override
    public boolean expectAlwaysPresent() {
        return true;
    }

    @Override
    public boolean expectServerInitializable() {
        return true;
    }

    @Override
    public boolean expectClientInitializable() {
        return false;
    }

    @Override
    public boolean expectClientDeletable() {
        return false;
    }

    @Override
    public boolean expectMultiInstanceAllowed() {
        return false;
    }

    @Override
    public State stateForServerModifiableTrue() {
        return null; // Not modifiable by server in any state
    }

    @Override
    public State stateForServerModifiableFalse() {
        return new State(State.Standard.PRE_ACTIVE); // Any state would work since it's not modifiable
    }

    @Override
    public State stateForClientModifiableTrue() {
        return null; // Not modifiable by client in any state
    }

    @Override
    public State stateForClientModifiableFalse() {
        return new State(State.Standard.ACTIVE); // Any state would work since it's not modifiable
    }

    @Override
    protected void attrEnum_serverModifiable_respectsState() {
        // Not applicable as it's not server modifiable
    }

    @Override
    protected void attrEnum_clientModifiable_respectsState() {
        // Not applicable as it's not client modifiable
    }

    @Override
    protected void assertLookupBehaviour() {
        // Register a custom value for testing
        ObjectType.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));

        // Lookup by name/value
        ObjectType.Value byName = ObjectType.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        ObjectType.Value byVal = ObjectType.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> ObjectType.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in ObjectType requires 8XXXXXXX (hex) range per implementation
        ObjectType.Value custom = ObjectType.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> ObjectType.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ObjectType.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ObjectType.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ObjectType.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

