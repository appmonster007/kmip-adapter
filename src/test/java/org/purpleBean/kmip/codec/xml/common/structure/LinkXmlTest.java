package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;
import org.purpleBean.kmip.common.enumeration.LinkType;
import org.purpleBean.kmip.common.structure.Link;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("Link Xml Serialization Tests")
class LinkXmlTest extends AbstractXmlSerializationSuite<Link> {

    @Override
    protected Class<Link> type() {
        return Link.class;
    }

    @Override
    protected Link createDefault() {
        return Link.builder()
                .linkType(new LinkType(LinkType.Standard.CERTIFICATE_LINK))
                .linkedObjectIdentifier(LinkedObjectIdentifier.of("test-id"))
                .build();
    }
}