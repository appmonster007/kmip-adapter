package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("KeyPartIdentifier Domain Tests")
class KeyPartIdentifierTest extends AbstractKmipDataTypeSuite<KeyPartIdentifier> {

    @Override
    protected Class<KeyPartIdentifier> type() {
        return KeyPartIdentifier.class;
    }

    @Override
    protected KeyPartIdentifier createDefault() {
        return KeyPartIdentifier.builder().value(1).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}