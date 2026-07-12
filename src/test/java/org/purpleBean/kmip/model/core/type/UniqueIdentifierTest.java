package org.purpleBean.kmip.model.core.type;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("UniqueIdentifier Domain Tests")
class UniqueIdentifierTest extends AbstractKmipDataTypeTestSuite<UniqueIdentifier> implements KmipAttributeTestSuite<UniqueIdentifier> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<UniqueIdentifier> type() {
        return UniqueIdentifier.class;
    }

    @Override
    public UniqueIdentifier createDefault() {
        return UniqueIdentifier.of("test-uuid");
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }

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
        return null; // Not modifiable by server
    }

    @Override
    public State stateForServerModifiableFalse() {
        return State.Standard.ACTIVE.inst(); // Never modifiable by server
    }

    @Override
    public State stateForClientModifiableTrue() {
        return null; // Not modifiable by client
    }

    @Override
    public State stateForClientModifiableFalse() {
        return State.Standard.ACTIVE.inst(); // Never modifiable by client
    }

    @Override
    public AttributeValue expectedAttributeValue() {
        return AttributeValue.ofTextString("test-uuid");
    }

        @Override
    public void attribute_serverModifiable_respectsState() {
        assertThat(createDefault().isServerModifiable(stateForServerModifiableFalse())).isFalse();
    }

        @Override
    public void attribute_clientModifiable_respectsState() {
        assertThat(createDefault().isClientModifiable(stateForClientModifiableFalse())).isFalse();
    }
}
