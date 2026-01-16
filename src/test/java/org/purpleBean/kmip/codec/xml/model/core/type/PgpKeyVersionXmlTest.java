package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PgpKeyVersion Xml Serialization Tests")
class PgpKeyVersionXmlTest extends AbstractXmlSerializationTestSuite<PgpKeyVersion> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<PgpKeyVersion> type() {
        return PgpKeyVersion.class;
    }

    @Override
    protected PgpKeyVersion createDefault() {
        return PgpKeyVersion.of(123);  // TODO: Create a default instance
    }

    @Override
    protected PgpKeyVersion createVariant() {
        return PgpKeyVersion.of(456);  // TODO: Create a variant instance
    }
}