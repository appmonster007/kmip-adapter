package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.LinkType;
import org.purpleBean.kmip.model.core.structure.Link;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Link Json Serialization Tests")
class LinkJsonTest extends AbstractJsonSerializationTestSuite<Link> {

    @Override
    public Class<Link> type() {
        return Link.class;
    }

    @Override
    public Link createDefault() {
        return Link.builder()
                .linkType(LinkType.Standard.CERTIFICATE_LINK.inst())
                .linkedObjectIdentifier(LinkedObjectIdentifier.of("test-id"))
                .build();
    }
}