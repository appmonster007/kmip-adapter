package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ValidateOpResponsePayload Domain Tests")
class ValidateOpResponsePayloadTest extends AbstractKmipStructureTestSuite<ValidateOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ValidateOpResponsePayload> type() {
        return ValidateOpResponsePayload.class;
    }

    @Override
    protected ValidateOpResponsePayload createDefault() {
        return ValidateOpResponsePayload.builder()
                .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.VALID))
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
        assertThat(values.get(0)).isInstanceOf(ValidityIndicator.class);
    }
}
