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
import org.purpleBean.kmip.model.v2_1.type.ServerUri;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ServerUri Xml Serialization Tests")
class ServerUriXmlTest extends AbstractXmlSerializationTestSuite<ServerUri> {

    @Override
    public Class<ServerUri> type() {
        return ServerUri.class;
    }

    @Override
    public ServerUri createDefault() {
        return ServerUri.of("default-string");
    }

    @Override
    public ServerUri createVariant() {
        return ServerUri.of("variant-string");
    }
}