package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ApplicationData;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;
import org.purpleBean.kmip.model.core.structure.ApplicationSpecificInformation;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ApplicationSpecificInformation Xml Serialization Tests")
class ApplicationSpecificInformationXmlTest extends AbstractXmlSerializationTestSuite<ApplicationSpecificInformation> {

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