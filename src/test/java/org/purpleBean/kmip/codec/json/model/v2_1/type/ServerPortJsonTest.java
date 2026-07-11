package org.purpleBean.kmip.codec.json.model.v2_1.type;

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
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ServerPort Json Serialization Tests")
class ServerPortJsonTest extends AbstractJsonSerializationTestSuite<ServerPort> {

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