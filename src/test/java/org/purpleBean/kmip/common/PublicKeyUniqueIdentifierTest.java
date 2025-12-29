package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("PublicKeyUniqueIdentifier Domain Tests")
class PublicKeyUniqueIdentifierTest extends AbstractKmipDataTypeSuite<PublicKeyUniqueIdentifier> {

    @Override
    protected Class<PublicKeyUniqueIdentifier> type() {
        return PublicKeyUniqueIdentifier.class;
    }

    @Override
    protected PublicKeyUniqueIdentifier createDefault() {
        return PublicKeyUniqueIdentifier.builder().value("test-key-id").build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}