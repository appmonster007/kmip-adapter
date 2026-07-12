package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.structure.CurrentAttribute;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.purpleBean.kmip.model.v2_1.enumeration.AdjustmentType;

@DisplayName("AdjustAttributeOpRequestPayload Domain Tests")
class AdjustAttributeOpRequestPayloadTest extends AbstractKmipStructureTestSuite<AdjustAttributeOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<AdjustAttributeOpRequestPayload> type() {
        return AdjustAttributeOpRequestPayload.class;
    }

    @Override
    protected AdjustAttributeOpRequestPayload createDefault() {
        return AdjustAttributeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("adj-attr-uid-1").build())
                .currentAttribute(CurrentAttribute.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build())
                .adjustmentType(AdjustmentType.Standard.INCREMENT.inst())
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    public int expectedMinComponentCount() {
        return 2;
    }

    @Override
    public void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSizeGreaterThanOrEqualTo(2);
        assertThat(values).anyMatch(v -> v instanceof CurrentAttribute);
        assertThat(values).anyMatch(v -> v instanceof AdjustmentType);
    }
}
