package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("StorageStatusMask Domain Tests")
class StorageStatusMaskTest extends AbstractKmipDataTypeTestSuite<StorageStatusMask> {

    @Override
    protected Class<StorageStatusMask> type() {
        return StorageStatusMask.class;
    }

    @Override
    protected StorageStatusMask createDefault() {
        return StorageStatusMask.builder().value(1).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}