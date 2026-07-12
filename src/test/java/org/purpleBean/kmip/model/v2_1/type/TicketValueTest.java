package org.purpleBean.kmip.model.v2_1.type;

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

import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.model.v2_1.type.TicketValue;

@DisplayName("TicketValue Domain Tests")
class TicketValueTest extends AbstractKmipDataTypeTestSuite<TicketValue> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<TicketValue> type() {
        return TicketValue.class;
    }

    @Override
    public TicketValue createDefault() {
        return TicketValue.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}