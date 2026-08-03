package org.purplebean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.ExtensionInformation;
import org.purplebean.kmip.model.core.type.ExtensionName;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ExtensionInformation Xml Serialization Tests")
class ExtensionInformationXmlTest extends AbstractXmlSerializationTestSuite<ExtensionInformation> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<ExtensionInformation> type() {
    return ExtensionInformation.class;
  }

  @Override
  public ExtensionInformation createDefault() {
    return ExtensionInformation
        .builder()
        .extensionName(ExtensionName.of("test-extension"))
        .build();
  }

  @Override
  public ExtensionInformation createVariant() {
    return ExtensionInformation
        .builder()
        .extensionName(ExtensionName.of("test-extension-variant"))
        .build();
  }
}