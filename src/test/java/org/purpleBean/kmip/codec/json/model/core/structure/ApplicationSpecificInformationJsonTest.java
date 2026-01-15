package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.ApplicationSpecificInformation;
import org.purpleBean.kmip.model.core.type.ApplicationData;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ApplicationSpecificInformation Json Serialization Tests")
class ApplicationSpecificInformationJsonTest extends AbstractJsonSerializationTestSuite<ApplicationSpecificInformation> {

    @Override
    protected Class<ApplicationSpecificInformation> type() {
        return ApplicationSpecificInformation.class;
    }

    @Override
    protected ApplicationSpecificInformation createDefault() {
        return ApplicationSpecificInformation.builder()
                .applicationNamespace(ApplicationNamespace.of("namespace"))
                .applicationData(ApplicationData.of("data"))
                .build();
    }
}