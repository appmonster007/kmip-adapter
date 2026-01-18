package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.MessageExtension;
import org.purpleBean.kmip.model.core.type.VendorIdentification;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MessageExtension Json Serialization Tests")
class MessageExtensionJsonTest extends AbstractJsonSerializationTestSuite<MessageExtension> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<MessageExtension> type() {
        return MessageExtension.class;
    }

    @Override
    protected MessageExtension createDefault() {
        return MessageExtension.builder()
                .vendorIdentification(VendorIdentification.of("test-vendor"))
                .build();
    }

    @Override
    protected MessageExtension createVariant() {
        return MessageExtension.builder()
                .vendorIdentification(VendorIdentification.of("test-vendor-variant"))
                .build();
    }
}