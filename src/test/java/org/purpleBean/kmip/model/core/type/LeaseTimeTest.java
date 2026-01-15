package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeAttributeTestSuite;

@DisplayName("LeaseTime Domain Tests")
class LeaseTimeTest extends AbstractKmipDataTypeAttributeTestSuite<LeaseTime> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<LeaseTime> type() {
        return LeaseTime.class;
    }

    @Override
    protected LeaseTime createDefault() {
        return LeaseTime.builder().value(1000).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTERVAL;
    }

    @Override
    protected boolean expectAlwaysPresent() {
        return false;
    }

    @Override
    protected boolean expectServerInitializable() {
        return true;
    }

    @Override
    protected boolean expectClientInitializable() {
        return false;
    }

    @Override
    protected boolean expectClientDeletable() {
        return false;
    }

    @Override
    protected boolean expectMultiInstanceAllowed() {
        return false;
    }

    @Override
    protected State stateForServerModifiableTrue() {
        return null;
    }

    @Override
    protected State stateForServerModifiableFalse() {
        return null;
    }

    @Override
    protected State stateForClientModifiableTrue() {
        return null;
    }

    @Override
    protected State stateForClientModifiableFalse() {
        return null;
    }

    @Override
    protected void attribute_serverModifiable_respectsState() {
    }

    @Override
    protected void attribute_clientModifiable_respectsState() {
    }
}
