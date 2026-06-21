package org.purpleBean.kmip.codec.xml.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.InteropOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InteropOpResponsePayload Xml Serialization Tests")
class InteropOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<InteropOpResponsePayload> {

    @Override
    public Class<InteropOpResponsePayload> type() {
        return InteropOpResponsePayload.class;
    }

    @Override
    public InteropOpResponsePayload createDefault() {
        return InteropOpResponsePayload.builder().build();
    }

    @Override
    public InteropOpResponsePayload createVariant() {
        return InteropOpResponsePayload.builder().build();
    }
}