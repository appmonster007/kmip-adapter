package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response;

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
import org.purpleBean.kmip.model.v2_1.structure.response.ResponseBatchItem;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ResponseBatchItem Ttlv Serialization Tests")
class ResponseBatchItemTtlvTest extends AbstractTtlvSerializationTestSuite<ResponseBatchItem> {

    @Override
    public Class<ResponseBatchItem> type() {
        return ResponseBatchItem.class;
    }

    @Override
    public ResponseBatchItem createDefault() {
        return ResponseBatchItem.builder().build();
    }

    @Override
    public ResponseBatchItem createVariant() {
        return ResponseBatchItem.builder().build();
    }
}