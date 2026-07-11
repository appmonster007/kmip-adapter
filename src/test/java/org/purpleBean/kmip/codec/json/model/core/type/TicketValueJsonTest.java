package org.purpleBean.kmip.codec.json.model.core.type;

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
import org.purpleBean.kmip.model.core.type.TicketValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("TicketValue Json Serialization Tests")
class TicketValueJsonTest extends AbstractJsonSerializationTestSuite<TicketValue> {

    @Override
    public Class<TicketValue> type() {
        return TicketValue.class;
    }

    @Override
    public TicketValue createDefault() {
        return TicketValue.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    public TicketValue createVariant() {
        return TicketValue.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}