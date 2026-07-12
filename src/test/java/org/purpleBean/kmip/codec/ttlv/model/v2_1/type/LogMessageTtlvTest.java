package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.LogMessage;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("LogMessage Ttlv Serialization Tests")
class LogMessageTtlvTest extends AbstractTtlvSerializationTestSuite<LogMessage> {

    @Override
    public Class<LogMessage> type() {
        return LogMessage.class;
    }

    @Override
    public LogMessage createDefault() {
        return LogMessage.of("default-string");
    }

    @Override
    public LogMessage createVariant() {
        return LogMessage.of("variant-string");
    }
}