package org.purpleBean.kmip.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CertifyOpRequestPayload Domain Tests")
class CertifyOpRequestPayloadTest extends AbstractKmipStructureTestSuite<CertifyOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<CertifyOpRequestPayload> type() {
        return CertifyOpRequestPayload.class;
    }

    @Override
    protected CertifyOpRequestPayload createDefault() {
        return CertifyOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-id").build())
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
        assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
    }
}