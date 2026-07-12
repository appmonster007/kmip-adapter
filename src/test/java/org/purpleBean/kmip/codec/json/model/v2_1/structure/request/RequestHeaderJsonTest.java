package org.purpleBean.kmip.codec.json.model.v2_1.structure.request;

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
import org.purpleBean.kmip.model.v2_1.structure.request.RequestHeader;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RequestHeader Json Serialization Tests")
class RequestHeaderJsonTest extends AbstractJsonSerializationTestSuite<RequestHeader> {

    @Override
    public Class<RequestHeader> type() {
        return RequestHeader.class;
    }

    @Override
    public RequestHeader createDefault() {
        return RequestHeader.builder().build();
    }

    @Override
    public RequestHeader createVariant() {
        return RequestHeader.builder().build();
    }
}