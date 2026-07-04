package org.purpleBean.kmip.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AdjustAttributeOpResponsePayload Domain Tests")
class AdjustAttributeOpResponsePayloadTest extends AbstractKmipStructureTestSuite<AdjustAttributeOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<AdjustAttributeOpResponsePayload> type() {
        return AdjustAttributeOpResponsePayload.class;
    }

    @Override
    protected AdjustAttributeOpResponsePayload createDefault() {
        return AdjustAttributeOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("adj-attr-resp-uid-1").build())
                .newAttribute(NewAttribute.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build())
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
        assertThat(values).hasSize(2);
        assertThat(values).anyMatch(v -> v instanceof UniqueIdentifier);
        assertThat(values).anyMatch(v -> v instanceof NewAttribute);
    }
}
