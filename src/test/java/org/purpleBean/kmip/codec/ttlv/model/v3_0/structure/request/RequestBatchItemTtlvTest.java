package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure.request;

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
import org.purpleBean.kmip.model.v3_0.structure.request.RequestBatchItem;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RequestBatchItem Ttlv Serialization Tests")
class RequestBatchItemTtlvTest extends AbstractTtlvSerializationTestSuite<RequestBatchItem> {

    @Override
    public Class<RequestBatchItem> type() {
        return RequestBatchItem.class;
    }

    @Override
    public RequestBatchItem createDefault() {
        return RequestBatchItem.builder().build();
    }

    @Override
    public RequestBatchItem createVariant() {
        return RequestBatchItem.builder().build();
    }
}