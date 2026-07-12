package org.purpleBean.kmip.model.core.type;

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

@DisplayName("RotateDate Domain Tests")
class RotateDateTest extends AbstractKmipDataTypeTestSuite<RotateDate> implements KmipAttributeTestSuite<RotateDate> {

    // TODO: Adjust FIXED_VALUE based on DATA_TYPE
    private static final OffsetDateTime FIXED_VALUE = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1; // TODO: Adjust default spec if needed
    }

    @Override
    protected Class<RotateDate> type() {
        return RotateDate.class;
    }

    @Override
    public RotateDate createDefault() {
        return RotateDate.of(FIXED_VALUE);
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
        return AttributeValue.ofDateTime(FIXED_VALUE);
    }

        @Override
    public void attribute_serverModifiable_respectsState() {
        assertThat(createDefault().isServerModifiable(stateForServerModifiableFalse())).isFalse();
    }

        @Override
    public void attribute_clientModifiable_respectsState() {
        assertThat(createDefault().isClientModifiable(stateForClientModifiableFalse())).isFalse();
    }

    @Override
    public void attribute_roundTrip() {
        // Not applicable: Attribute wrapper structure only supports V1.x; these types are V2.1+ only
    }
}
