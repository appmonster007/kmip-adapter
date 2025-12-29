package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("MACSignatureKeyInformation TTLV Serialization Tests")
class MACSignatureKeyInformationTtlvTest extends AbstractTtlvSerializationSuite<MACSignatureKeyInformation> {

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