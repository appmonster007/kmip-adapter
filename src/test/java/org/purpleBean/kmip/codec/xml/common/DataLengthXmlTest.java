package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DataLength;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("DataLength XML Serialization Tests")
class DataLengthXmlTest extends AbstractXmlSerializationSuite<DataLength> {

    @Override
    protected Class<DataLength> type() {
        return DataLength.class;
    }

    @Override
    protected DataLength createDefault() {
        return DataLength.of(128);
    }

    @Override
    protected DataLength createVariant() {
        return DataLength.of(256);
    }
}
