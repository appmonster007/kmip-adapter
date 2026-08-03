package org.purpleBean.kmip.model.v3_0.structure.response;

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

@DisplayName("ResponseBatchItem Domain Tests")
class ResponseBatchItemTest extends AbstractKmipStructureTestSuite<ResponseBatchItem> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V3_0;
    }

    @Override
    protected Class<ResponseBatchItem> type() {
        return ResponseBatchItem.class;
    }

    @Override
    protected ResponseBatchItem createDefault() {
        return ResponseBatchItem.builder()
                .operation(Operation.of(Operation.Standard.CREATE))
                .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
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
        assertThat(values.get(0)).isInstanceOf(Operation.class);
        assertThat(values.get(1)).isInstanceOf(ResultStatus.class);
    }
}