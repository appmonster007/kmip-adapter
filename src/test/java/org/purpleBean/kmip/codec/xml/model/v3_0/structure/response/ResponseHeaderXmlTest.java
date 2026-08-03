package org.purpleBean.kmip.codec.xml.model.v3_0.structure.response;

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
import org.purpleBean.kmip.model.v3_0.structure.response.ResponseHeader;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ResponseHeader Xml Serialization Tests")
class ResponseHeaderXmlTest extends AbstractXmlSerializationTestSuite<ResponseHeader> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V3_0;
    }

    @Override
    public Class<ResponseHeader> type() {
        return ResponseHeader.class;
    }

    @Override
    public ResponseHeader createDefault() {
        return ResponseHeader.builder()
                .protocolVersion(ProtocolVersion.of(3, 0))
                .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
                .build();
    }

    @Override
    public ResponseHeader createVariant() {
        return ResponseHeader.builder()
                .protocolVersion(ProtocolVersion.of(3, 0))
                .timeStamp(TimeStamp.of(OffsetDateTime.of(2025, 6, 15, 0, 0, 0, 0, ZoneOffset.UTC)))
                .build();
    }
}