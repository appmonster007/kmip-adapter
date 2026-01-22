package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CancelOpRequestPayload Domain Tests")
class CancelOpRequestPayloadTest extends AbstractKmipStructureTestSuite<CancelOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CancelOpRequestPayload> type() {
        return CancelOpRequestPayload.class;
    }

    @Override
    protected CancelOpRequestPayload createDefault() {
        return CancelOpRequestPayload.builder()
                .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[]{1, 2, 3}))
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
        assertThat(values).hasSize(1);
        assertThat(values.get(0)).isInstanceOf(AsynchronousCorrelationValue.class);
    }
}
