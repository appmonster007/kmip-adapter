package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CheckOpResponsePayload Domain Tests")
class CheckOpResponsePayloadTest extends AbstractKmipStructureTestSuite<CheckOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CheckOpResponsePayload> type() {
        return CheckOpResponsePayload.class;
    }

    @Override
    protected CheckOpResponsePayload createDefault() {
        return CheckOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .usageLimitsCount(UsageLimitsCount.of(100L))
                .cryptographicUsageMask(CryptographicUsageMask.of(1))
                .leaseTime(LeaseTime.of(3600))
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 1;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(4);
        assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
        assertThat(values.get(1)).isInstanceOf(UsageLimitsCount.class);
        assertThat(values.get(2)).isInstanceOf(CryptographicUsageMask.class);
        assertThat(values.get(3)).isInstanceOf(LeaseTime.class);
    }
}
