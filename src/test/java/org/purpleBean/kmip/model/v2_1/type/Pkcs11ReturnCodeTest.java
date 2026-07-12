package org.purpleBean.kmip.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("Pkcs11ReturnCode Domain Tests")
class Pkcs11ReturnCodeTest extends AbstractKmipDataTypeTestSuite<Pkcs11ReturnCode> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<Pkcs11ReturnCode> type() {
        return Pkcs11ReturnCode.class;
    }

    @Override
    protected Pkcs11ReturnCode createDefault() {
        return Pkcs11ReturnCode.of(0);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}
