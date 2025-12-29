package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("SplitKeyParts Domain Tests")
class SplitKeyPartsTest extends AbstractKmipDataTypeSuite<SplitKeyParts> {

    @Override
    protected Class<SplitKeyParts> type() {
        return SplitKeyParts.class;
    }

    @Override
    protected SplitKeyParts createDefault() {
        return SplitKeyParts.builder().value(2).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}