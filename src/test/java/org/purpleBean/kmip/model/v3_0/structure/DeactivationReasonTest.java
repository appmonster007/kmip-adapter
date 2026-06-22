package org.purpleBean.kmip.model.v3_0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DeactivationReason Domain Tests")
class DeactivationReasonTest extends AbstractKmipStructureTestSuite<DeactivationReason> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<DeactivationReason> type() {
        return DeactivationReason.class;
    }

    @Override
    protected DeactivationReason createDefault() {
        return DeactivationReason.builder()
                .deactivationReasonCode(DeactivationReasonCode.of(DeactivationReasonCode.Standard.UNSPECIFIED))
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    public int expectedMinComponentCount() {
        return 1;
    }

    @Override
    public void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(1);
        assertThat(values.get(0)).isInstanceOf(DeactivationReasonCode.class);
    }
}