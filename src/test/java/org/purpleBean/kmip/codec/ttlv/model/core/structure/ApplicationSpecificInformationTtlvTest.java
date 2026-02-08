package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.ApplicationSpecificInformation;
import org.purpleBean.kmip.model.core.type.ApplicationData;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ApplicationSpecificInformation Ttlv Serialization Tests")
class ApplicationSpecificInformationTtlvTest extends AbstractTtlvSerializationTestSuite<ApplicationSpecificInformation> {

    @Override
    public Class<ApplicationSpecificInformation> type() {
        return ApplicationSpecificInformation.class;
    }

    @Override
    public ApplicationSpecificInformation createDefault() {
        return ApplicationSpecificInformation.builder()
                .applicationNamespace(ApplicationNamespace.of("namespace"))
                .applicationData(ApplicationData.of("data"))
                .build();
    }
}