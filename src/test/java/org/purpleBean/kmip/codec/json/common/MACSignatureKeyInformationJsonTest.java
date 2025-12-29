package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("MACSignatureKeyInformation JSON Serialization Tests")
class MACSignatureKeyInformationJsonTest extends AbstractJsonSerializationSuite<MACSignatureKeyInformation> {

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