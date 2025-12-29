package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("LinkedObjectIdentifier Domain Tests")
class LinkedObjectIdentifierTest extends AbstractKmipDataTypeSuite<LinkedObjectIdentifier> {

    @Override
    protected Class<LinkedObjectIdentifier> type() {
        return LinkedObjectIdentifier.class;
    }

    @Override
    protected LinkedObjectIdentifier createDefault() {
        return LinkedObjectIdentifier.builder().value("test-linked-id").build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}