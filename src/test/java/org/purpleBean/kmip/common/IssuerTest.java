package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("Issuer Domain Tests")
class IssuerTest extends AbstractKmipDataTypeSuite<Issuer> {

    @Override
    protected Class<Issuer> type() {
        return Issuer.class;
    }

    @Override
    protected Issuer createDefault() {
        return Issuer.builder().value("test-issuer").build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}