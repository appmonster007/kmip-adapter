package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ServerInformation;
import org.purpleBean.kmip.model.core.type.NameValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ServerInformation Ttlv Serialization Tests")
class ServerInformationTtlvTest extends AbstractTtlvSerializationTestSuite<ServerInformation> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ServerInformation> type() {
        return ServerInformation.class;
    }

    @Override
    protected ServerInformation createDefault() {
        return ServerInformation.builder()
                .value(NameValue.of("Test Server"))
                .build();
    }

    @Override
    protected ServerInformation createVariant() {
        return ServerInformation.builder()
                .value(NameValue.of("Variant Server"))
                .build();
    }
}
