package org.purpleBean.kmip.model.v3_0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.NameValue;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Name v3.0 Domain Tests")
class NameTest extends AbstractKmipStructureTestSuite<Name> implements KmipAttributeTestSuite<Name> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V3_0;
    }

    @Override
    public Class<Name> type() {
        return Name.class;
    }

    @Override
    public Name createDefault() {
        return Name.of("test-name");
    }

    @Override
    public EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    public int expectedMinComponentCount() {
        return 1;
    }

    @Override
    public void validateComponents(List<KmipDataType> values) {
        assertThat(values.get(0)).isInstanceOf(NameValue.class);
    }

    @Override
    public boolean expectAlwaysPresent() {
        return false;
    }

    @Override
    public boolean expectServerInitializable() {
        return false;
    }

    @Override
    public boolean expectClientInitializable() {
        return true;
    }

    @Override
    public boolean expectClientDeletable() {
        return true;
    }

    @Override
    public boolean expectMultiInstanceAllowed() {
        return true;
    }

    @Override
    public State stateForServerModifiableTrue() {
        return null;
    }

    @Override
    public State stateForServerModifiableFalse() {
        return State.Standard.PRE_ACTIVE.inst();
    }

    @Override
    public State stateForClientModifiableTrue() {
        return State.Standard.PRE_ACTIVE.inst();
    }

    @Override
    public State stateForClientModifiableFalse() {
        return null;
    }

    @Override
    public AttributeValue expectedAttributeValue() {
        return AttributeValue.ofStructure(createDefault().getValue());
    }

    @Override
    public void attribute_serverModifiable_respectsState() {
        // Name is never server-modifiable in any state
    }

    @Override
    public void attribute_clientModifiable_respectsState() {
        // Name is always client-modifiable; covered by createDefault() round-trip
    }

    @Override
    public void attribute_roundTrip() {
        // Not applicable: Attribute wrapper structure does not support V3.0
    }
}
