package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;
import org.purpleBean.kmip.common.enumeration.LinkType;
import org.purpleBean.kmip.common.structure.Link;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Link Xml Serialization Tests")
class LinkXmlTest extends AbstractXmlSerializationTestSuite<Link> {

    @Override
    protected Class<Link> type() {
        return Link.class;
    }

    @Override
    protected Link createDefault() {
        return Link.builder()
                .linkType(LinkType.Standard.CERTIFICATE_LINK.inst())
                .linkedObjectIdentifier(LinkedObjectIdentifier.of("test-id"))
                .build();
    }
}