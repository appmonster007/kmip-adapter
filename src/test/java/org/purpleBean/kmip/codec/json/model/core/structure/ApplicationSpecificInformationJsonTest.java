package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.ApplicationSpecificInformation;
import org.purplebean.kmip.model.core.type.ApplicationData;
import org.purplebean.kmip.model.core.type.ApplicationNamespace;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ApplicationSpecificInformation Json Serialization Tests")
class ApplicationSpecificInformationJsonTest
    extends AbstractJsonSerializationTestSuite<ApplicationSpecificInformation> {

  @Override
  public Class<ApplicationSpecificInformation> type() {
    return ApplicationSpecificInformation.class;
  }

  @Override
  public ApplicationSpecificInformation createDefault() {
    return ApplicationSpecificInformation
        .builder()
        .applicationNamespace(ApplicationNamespace.of("namespace"))
        .applicationData(ApplicationData.of("data"))
        .build();
  }
}