package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DataLength XML Serialization Tests")
class DataLengthXmlTest extends AbstractXmlSerializationTestSuite<DataLength> {

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
