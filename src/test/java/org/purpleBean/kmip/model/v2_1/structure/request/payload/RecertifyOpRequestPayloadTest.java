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

@DisplayName("RecertifyOpRequestPayload Domain Tests")
class RecertifyOpRequestPayloadTest extends AbstractKmipStructureTestSuite<RecertifyOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<RecertifyOpRequestPayload> type() {
        return RecertifyOpRequestPayload.class;
    }

    @Override
    protected RecertifyOpRequestPayload createDefault() {
        return RecertifyOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("cert-id").build())
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