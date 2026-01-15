package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.LinkType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("LinkType TTLV Serialization")
class LinkTypeTtlvTest extends AbstractTtlvSerializationTestSuite<LinkType> {
    @Override
    protected Class<LinkType> type() {
        return LinkType.class;
    }

    @Override
    protected LinkType createDefault() {
        return LinkType.Standard.CERTIFICATE_LINK.inst();
    }

    @Override
    protected LinkType createVariant() {
        return LinkType.Standard.PUBLIC_KEY_LINK.inst();
    }
}
