package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("StorageStatusMask TTLV Serialization Tests")
class StorageStatusMaskTtlvTest extends AbstractTtlvSerializationTestSuite<StorageStatusMask> {

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