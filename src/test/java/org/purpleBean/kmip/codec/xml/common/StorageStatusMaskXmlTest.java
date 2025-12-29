package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.StorageStatusMask;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("StorageStatusMask XML Serialization Tests")
class StorageStatusMaskXmlTest extends AbstractXmlSerializationSuite<StorageStatusMask> {

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