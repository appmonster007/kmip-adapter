package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.TicketType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.util.Set;

@DisplayName("TicketType XML Serialization")
class TicketTypeXmlTest extends AbstractXmlSerializationTestSuite<TicketType> {
    @Override
    public Class<TicketType> type() {
        return TicketType.class;
    }

    @Override
    public TicketType createDefault() {
        return TicketType.Standard.LOGIN.inst();
    }

    @Override
    public TicketType createVariant() {
        return TicketType.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion)).inst();
    }
}
