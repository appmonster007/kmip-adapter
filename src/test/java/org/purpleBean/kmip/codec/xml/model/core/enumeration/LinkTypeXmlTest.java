package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.LinkType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LinkType XML Serialization")
class LinkTypeXmlTest extends AbstractXmlSerializationTestSuite<LinkType> {
    @Override
    protected Class<LinkType> type() {
        return LinkType.class;
    }

    @Override
    protected LinkType createDefault() {
        return  LinkType.Standard.CERTIFICATE_LINK.inst();
    }

    @Override
    protected LinkType createVariant() {
        return LinkType.Standard.PUBLIC_KEY_LINK.inst();
    }
}
