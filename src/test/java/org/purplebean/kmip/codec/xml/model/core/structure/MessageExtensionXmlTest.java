package org.purplebean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.MessageExtension;
import org.purplebean.kmip.model.core.type.VendorIdentification;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("MessageExtension Xml Serialization Tests")
class MessageExtensionXmlTest extends AbstractXmlSerializationTestSuite<MessageExtension> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<MessageExtension> type() {
    return MessageExtension.class;
  }

  @Override
  public MessageExtension createDefault() {
    return MessageExtension
        .builder()
        .vendorIdentification(VendorIdentification.of("test-vendor"))
        .build();
  }

  @Override
  public MessageExtension createVariant() {
    return MessageExtension
        .builder()
        .vendorIdentification(VendorIdentification.of("test-vendor-variant"))
        .build();
  }
}