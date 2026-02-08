package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ProcessStartDate Domain Tests")
class ProcessStartDateTest extends AbstractKmipDataTypeTestSuite<ProcessStartDate> implements KmipAttributeTestSuite<ProcessStartDate> {

    private static final OffsetDateTime FUTURE_TIME = OffsetDateTime.now(ZoneOffset.UTC).plusDays(1);

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ProcessStartDate> type() {
        return ProcessStartDate.class;
    }

    @Override
    public ProcessStartDate createDefault() {
        return ProcessStartDate.builder().value(FUTURE_TIME).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.DATE_TIME;
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
        return true;
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
        return State.Standard.PRE_ACTIVE.inst();
    }

    @Override
    public State stateForServerModifiableFalse() {
        return State.Standard.DEACTIVATED.inst();
    }

    @Override
    public State stateForClientModifiableTrue() {
        return State.Standard.PRE_ACTIVE.inst();
    }

    @Override
    public State stateForClientModifiableFalse() {
        return State.Standard.DEACTIVATED.inst();
    }

    @Override
    public AttributeValue expectedAttributeValue() {
        return AttributeValue.ofDateTime(FUTURE_TIME);
    }
}
