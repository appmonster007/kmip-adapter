package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.Link;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("Link Json Serialization Tests")
class LinkJsonTest extends AbstractJsonSerializationSuite<Link> {

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