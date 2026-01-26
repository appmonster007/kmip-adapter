package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("StorageStatusMask XML Serialization Tests")
class StorageStatusMaskXmlTest extends AbstractXmlSerializationTestSuite<StorageStatusMask> {

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
        return StorageStatusMask.builder().value(3).build();
    }
}