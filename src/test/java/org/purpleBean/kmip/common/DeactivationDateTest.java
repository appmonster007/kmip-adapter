package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeAttributeTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("DeactivationDate Domain Tests")
class DeactivationDateTest extends AbstractKmipDataTypeAttributeTestSuite<DeactivationDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<DeactivationDate> type() {
        return DeactivationDate.class;
    }

    @Override
    protected DeactivationDate createDefault() {
        return DeactivationDate.builder().value(FIXED_TIME).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.DATE_TIME;
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
        return true;
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
        return new State(State.Standard.PRE_ACTIVE);
    }

    @Override
    protected State stateForServerModifiableFalse() {
        return new State(State.Standard.DEACTIVATED);
    }

    @Override
    protected State stateForClientModifiableTrue() {
        return new State(State.Standard.PRE_ACTIVE);
    }

    @Override
    protected State stateForClientModifiableFalse() {
        return new State(State.Standard.DEACTIVATED);
    }
}