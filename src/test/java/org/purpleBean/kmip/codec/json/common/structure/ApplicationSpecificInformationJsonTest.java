package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ApplicationData;
import org.purpleBean.kmip.common.ApplicationNamespace;
import org.purpleBean.kmip.common.structure.ApplicationSpecificInformation;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("ApplicationSpecificInformation Json Serialization Tests")
class ApplicationSpecificInformationJsonTest extends AbstractJsonSerializationSuite<ApplicationSpecificInformation> {

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