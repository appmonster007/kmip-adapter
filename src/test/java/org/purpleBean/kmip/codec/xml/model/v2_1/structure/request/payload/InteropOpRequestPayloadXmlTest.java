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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.InteropOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InteropOpRequestPayload Xml Serialization Tests")
class InteropOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<InteropOpRequestPayload> {

    @Override
    public Class<InteropOpRequestPayload> type() {
        return InteropOpRequestPayload.class;
    }

    @Override
    public InteropOpRequestPayload createDefault() {
        return InteropOpRequestPayload.builder().build();
    }

    @Override
    public InteropOpRequestPayload createVariant() {
        return InteropOpRequestPayload.builder().build();
    }
}