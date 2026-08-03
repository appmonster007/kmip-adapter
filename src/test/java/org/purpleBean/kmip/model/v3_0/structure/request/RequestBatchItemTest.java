package org.purpleBean.kmip.model.v3_0.structure.request;

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

@DisplayName("RequestBatchItem Domain Tests")
class RequestBatchItemTest extends AbstractKmipStructureTestSuite<RequestBatchItem> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V3_0;
    }

    @Override
    protected Class<RequestBatchItem> type() {
        return RequestBatchItem.class;
    }

    @Override
    protected RequestBatchItem createDefault() {
        return RequestBatchItem.builder()
                .operation(Operation.of(Operation.Standard.CREATE))
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
        assertThat(values.size()).isGreaterThanOrEqualTo(1);
        assertThat(values.get(0)).isInstanceOf(Operation.class);
    }
}