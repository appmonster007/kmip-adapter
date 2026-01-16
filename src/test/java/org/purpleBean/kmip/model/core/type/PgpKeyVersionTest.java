package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PgpKeyVersion Domain Tests")
class PgpKeyVersionTest extends AbstractKmipDataTypeTestSuite<PgpKeyVersion> {

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
        return PgpKeyVersion.of(123);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}