package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.ProcessStartDate;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeAttributeTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ProcessStartDate Domain Tests")
class ProcessStartDateTest extends AbstractKmipDataTypeAttributeTestSuite<ProcessStartDate> {

    private static final OffsetDateTime PAST_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    private static final OffsetDateTime FUTURE_TIME = OffsetDateTime.now().plusDays(1);

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ProcessStartDate> type() {
        return ProcessStartDate.class;
    }

    @Override
    protected ProcessStartDate createDefault() {
        return ProcessStartDate.builder().value(PAST_TIME).build();
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
        return State.Standard.PRE_ACTIVE.inst();
    }

    @Override
    protected State stateForServerModifiableFalse() {
        return State.Standard.DEACTIVATED.inst();
    }

    @Override
    protected State stateForClientModifiableTrue() {
        return State.Standard.PRE_ACTIVE.inst();
    }

    @Override
    protected State stateForClientModifiableFalse() {
        return State.Standard.DEACTIVATED.inst();
    }

    @Test
    @DisplayName("attribute_serverModifiable_respectsStateAndDate")
    @Override
    protected void attribute_serverModifiable_respectsState() {
        ProcessStartDate futureDate = ProcessStartDate.builder().value(FUTURE_TIME).build();
        assertThat(futureDate.isServerModifiable(stateForServerModifiableTrue())).isTrue();

        ProcessStartDate pastDate = createDefault();
        assertThat(pastDate.isServerModifiable(stateForServerModifiableTrue())).isFalse();
        assertThat(pastDate.isServerModifiable(stateForServerModifiableFalse())).isFalse();
    }

    @Test
    @DisplayName("attribute_clientModifiable_respectsStateAndDate")
    @Override
    protected void attribute_clientModifiable_respectsState() {
        ProcessStartDate futureDate = ProcessStartDate.builder().value(FUTURE_TIME).build();
        assertThat(futureDate.isClientModifiable(stateForClientModifiableTrue())).isTrue();

        ProcessStartDate pastDate = createDefault();
        assertThat(pastDate.isClientModifiable(stateForClientModifiableTrue())).isFalse();
        assertThat(pastDate.isClientModifiable(stateForClientModifiableFalse())).isFalse();
    }
}