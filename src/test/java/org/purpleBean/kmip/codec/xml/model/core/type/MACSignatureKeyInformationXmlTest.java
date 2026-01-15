package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.MACSignatureKeyInformation;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("MACSignatureKeyInformation XML Serialization Tests")
class MACSignatureKeyInformationXmlTest extends AbstractXmlSerializationTestSuite<MACSignatureKeyInformation> {

    @Override
    protected Class<MACSignatureKeyInformation> type() {
        return MACSignatureKeyInformation.class;
    }

    @Override
    protected MACSignatureKeyInformation createDefault() {
        return MACSignatureKeyInformation.builder().value("test-info").build();
    }

    @Override
    protected MACSignatureKeyInformation createVariant() {
        return MACSignatureKeyInformation.builder().value("another-info").build();
    }
}