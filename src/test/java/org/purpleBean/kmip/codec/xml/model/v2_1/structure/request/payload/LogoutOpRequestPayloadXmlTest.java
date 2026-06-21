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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LogoutOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LogoutOpRequestPayload Xml Serialization Tests")
class LogoutOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<LogoutOpRequestPayload> {

    @Override
    public Class<LogoutOpRequestPayload> type() {
        return LogoutOpRequestPayload.class;
    }

    @Override
    public LogoutOpRequestPayload createDefault() {
        return LogoutOpRequestPayload.builder().build();
    }

    @Override
    public LogoutOpRequestPayload createVariant() {
        return LogoutOpRequestPayload.builder().build();
    }
}