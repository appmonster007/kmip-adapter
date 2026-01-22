package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CancelOpResponsePayload Domain Tests")
class CancelOpResponsePayloadTest extends AbstractKmipStructureTestSuite<CancelOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CancelOpResponsePayload> type() {
        return CancelOpResponsePayload.class;
    }

    @Override
    protected CancelOpResponsePayload createDefault() {
        return CancelOpResponsePayload.builder()
                .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[]{1, 2, 3}))
                .cancellationResult(CancellationResult.of(CancellationResult.Standard.CANCELED))
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 2;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(2);
        assertThat(values.get(0)).isInstanceOf(AsynchronousCorrelationValue.class);
        assertThat(values.get(1)).isInstanceOf(CancellationResult.class);
    }
}
