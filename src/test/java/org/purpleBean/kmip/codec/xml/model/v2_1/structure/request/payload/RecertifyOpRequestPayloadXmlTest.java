package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.RecertifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RecertifyOpRequestPayload Xml Serialization Tests")
class RecertifyOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<RecertifyOpRequestPayload> {

    @Override
    public Class<RecertifyOpRequestPayload> type() {
        return RecertifyOpRequestPayload.class;
    }

    @Override
    public RecertifyOpRequestPayload createDefault() {
        return RecertifyOpRequestPayload.builder().build();
    }

    @Override
    public RecertifyOpRequestPayload createVariant() {
        return RecertifyOpRequestPayload.builder().build();
    }
}