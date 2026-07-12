package org.purpleBean.kmip.codec.xml.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.ServerCorrelationValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ServerCorrelationValue Xml Serialization Tests")
class ServerCorrelationValueXmlTest extends AbstractXmlSerializationTestSuite<ServerCorrelationValue> {

    @Override
    public Class<ServerCorrelationValue> type() {
        return ServerCorrelationValue.class;
    }

    @Override
    public ServerCorrelationValue createDefault() {
        return ServerCorrelationValue.of("default-string");
    }

    @Override
    public ServerCorrelationValue createVariant() {
        return ServerCorrelationValue.of("variant-string");
    }
}