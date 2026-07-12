package org.purpleBean.kmip.model.v2_1.type;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;


import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ProtectionPeriod Domain Tests")
class ProtectionPeriodTest extends AbstractKmipDataTypeTestSuite<ProtectionPeriod> implements KmipAttributeTestSuite<ProtectionPeriod> {

    // TODO: Adjust FIXED_VALUE based on DATA_TYPE
    private static final Long FIXED_VALUE = 12345L;

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1; // TODO: Adjust default spec if needed
    }

    @Override
    protected Class<ProtectionPeriod> type() {
        return ProtectionPeriod.class;
    }

    @Override
    public ProtectionPeriod createDefault() {
        return ProtectionPeriod.of(FIXED_VALUE);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.LONG_INTEGER;
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
        return false;
    }

    @Override
    public State stateForServerModifiableTrue() {
        return State.Standard.PRE_ACTIVE.inst(); // TODO: Adjust as needed
    }

    @Override
    public State stateForServerModifiableFalse() {
        return State.Standard.ACTIVE.inst(); // TODO: Adjust as needed
    }

    @Override
    public State stateForClientModifiableTrue() {
        return State.Standard.PRE_ACTIVE.inst(); // TODO: Adjust as needed
    }

    @Override
    public State stateForClientModifiableFalse() {
        return State.Standard.ACTIVE.inst(); // TODO: Adjust as needed
    }

    @Override
    public AttributeValue expectedAttributeValue() {
        return AttributeValue.ofLongInteger(FIXED_VALUE);
    }

        @Override
    public void attribute_serverModifiable_respectsState() {
        assertThat(createDefault().isServerModifiable(stateForServerModifiableFalse())).isFalse();
    }

        @Override
    public void attribute_clientModifiable_respectsState() {
        assertThat(createDefault().isClientModifiable(stateForClientModifiableTrue())).isTrue();
    }

    @Override
    public void attribute_roundTrip() {
        // Not applicable: Attribute wrapper structure only supports V1.x; these types are V2.1+ only
    }
}
