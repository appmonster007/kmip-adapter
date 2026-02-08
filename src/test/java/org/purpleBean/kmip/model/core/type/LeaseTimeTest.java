package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("LeaseTime Domain Tests")
class LeaseTimeTest extends AbstractKmipDataTypeTestSuite<LeaseTime> implements KmipAttributeTestSuite<LeaseTime> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<LeaseTime> type() {
        return LeaseTime.class;
    }

    @Override
    public LeaseTime createDefault() {
        return LeaseTime.of(3600);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTERVAL;
    }

    @Override
    public boolean expectAlwaysPresent() {
        return false;
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
        return State.Standard.ACTIVE.inst(); // Modifiable in any state
    }

    @Override
    public State stateForServerModifiableFalse() {
        return null; // Always modifiable
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
        return AttributeValue.ofInterval(3600);
    }

    @Override
    public void attribute_serverModifiable_respectsState() {
        // Always true
    }

    @Override
    public void attribute_clientModifiable_respectsState() {
        // Always false
    }
}
