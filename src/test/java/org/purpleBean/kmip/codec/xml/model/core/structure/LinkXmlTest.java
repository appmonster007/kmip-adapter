package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;
import org.purpleBean.kmip.model.core.enumeration.LinkType;
import org.purpleBean.kmip.model.core.structure.Link;
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