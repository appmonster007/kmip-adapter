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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ProcessOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProcessOpResponsePayload Xml Serialization Tests")
class ProcessOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<ProcessOpResponsePayload> {

    @Override
    public Class<ProcessOpResponsePayload> type() {
        return ProcessOpResponsePayload.class;
    }

    @Override
    public ProcessOpResponsePayload createDefault() {
        return ProcessOpResponsePayload.builder().build();
    }

    @Override
    public ProcessOpResponsePayload createVariant() {
        return ProcessOpResponsePayload.builder().build();
    }
}