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
import org.purpleBean.kmip.model.v2_1.type.ServerPort;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ServerPort Xml Serialization Tests")
class ServerPortXmlTest extends AbstractXmlSerializationTestSuite<ServerPort> {

    @Override
    public Class<ServerPort> type() {
        return ServerPort.class;
    }

    @Override
    public ServerPort createDefault() {
        return ServerPort.of(123);
    }

    @Override
    public ServerPort createVariant() {
        return ServerPort.of(456);
    }
}