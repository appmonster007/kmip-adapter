package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.MessageExtension;
import org.purpleBean.kmip.model.core.type.VendorIdentification;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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
        return MessageExtension.builder()
                .vendorIdentification(VendorIdentification.of("test-vendor"))
                .build();
    }

    @Override
    public MessageExtension createVariant() {
        return MessageExtension.builder()
                .vendorIdentification(VendorIdentification.of("test-vendor-variant"))
                .build();
    }
}