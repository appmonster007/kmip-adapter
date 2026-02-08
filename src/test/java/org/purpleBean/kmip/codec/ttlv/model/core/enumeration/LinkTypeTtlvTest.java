package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.LinkType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("LinkType TTLV Serialization")
class LinkTypeTtlvTest extends AbstractTtlvSerializationTestSuite<LinkType> {
    @Override
    public Class<LinkType> type() {
        return LinkType.class;
    }

    @Override
    public LinkType createDefault() {
        return LinkType.Standard.CERTIFICATE_LINK.inst();
    }

    @Override
    public LinkType createVariant() {
        return LinkType.Standard.PUBLIC_KEY_LINK.inst();
    }
}
