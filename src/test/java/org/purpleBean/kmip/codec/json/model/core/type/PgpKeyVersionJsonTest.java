package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PgpKeyVersion Json Serialization Tests")
class PgpKeyVersionJsonTest extends AbstractJsonSerializationTestSuite<PgpKeyVersion> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<PgpKeyVersion> type() {
        return PgpKeyVersion.class;
    }

    @Override
    public PgpKeyVersion createDefault() {
        return PgpKeyVersion.of(123);
    }

    @Override
    public PgpKeyVersion createVariant() {
        return PgpKeyVersion.of(456);
    }
}