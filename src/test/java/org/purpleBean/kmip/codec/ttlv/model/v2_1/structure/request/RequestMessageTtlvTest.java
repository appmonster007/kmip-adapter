package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request;

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
import org.purpleBean.kmip.model.v2_1.structure.request.RequestMessage;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RequestMessage Ttlv Serialization Tests")
class RequestMessageTtlvTest extends AbstractTtlvSerializationTestSuite<RequestMessage> {

    @Override
    public Class<RequestMessage> type() {
        return RequestMessage.class;
    }

    @Override
    public RequestMessage createDefault() {
        return RequestMessage.builder().build();
    }

    @Override
    public RequestMessage createVariant() {
        return RequestMessage.builder().build();
    }
}