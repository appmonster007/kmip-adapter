package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("ReplaceExisting Domain Tests")
class ReplaceExistingTest extends AbstractKmipDataTypeTestSuite<ReplaceExisting> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<ReplaceExisting> type() {
        return ReplaceExisting.class;
    }

    @Override
    protected ReplaceExisting createDefault() {
        return ReplaceExisting.of(Boolean.TRUE);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BOOLEAN;
    }
}
