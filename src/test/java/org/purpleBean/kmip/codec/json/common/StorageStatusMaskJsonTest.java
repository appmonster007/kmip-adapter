package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.StorageStatusMask;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("StorageStatusMask JSON Serialization Tests")
class StorageStatusMaskJsonTest extends AbstractJsonSerializationSuite<StorageStatusMask> {

    @Override
    protected Class<StorageStatusMask> type() {
        return StorageStatusMask.class;
    }

    @Override
    protected StorageStatusMask createDefault() {
        return StorageStatusMask.builder().value(1).build();
    }

    @Override
    protected StorageStatusMask createVariant() {
        return StorageStatusMask.builder().value(2).build();
    }
}