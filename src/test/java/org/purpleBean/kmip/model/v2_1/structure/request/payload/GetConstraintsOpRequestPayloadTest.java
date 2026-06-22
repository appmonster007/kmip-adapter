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
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GetConstraintsOpRequestPayload Domain Tests")
class GetConstraintsOpRequestPayloadTest extends AbstractKmipStructureTestSuite<GetConstraintsOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<GetConstraintsOpRequestPayload> type() {
        return GetConstraintsOpRequestPayload.class;
    }

    @Override
    protected GetConstraintsOpRequestPayload createDefault() {
        return GetConstraintsOpRequestPayload.builder().build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    public int expectedMinComponentCount() {
        return 0;
    }

    @Override
    public void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSizeLessThanOrEqualTo(1);
    }
}