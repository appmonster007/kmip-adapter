package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.TicketType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.util.Set;

@DisplayName("TicketType TTLV Serialization")
class TicketTypeTtlvTest extends AbstractTtlvSerializationSuite<TicketType> {
    @Override
    protected Class<TicketType> type() {
        return TicketType.class;
    }

    @Override
    protected TicketType createDefault() {
        return new TicketType(TicketType.Standard.LOGIN);
    }

    @Override
    protected TicketType createVariant() {
        return new TicketType(TicketType.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion)));
    }
}
