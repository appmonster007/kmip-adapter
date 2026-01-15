package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.DataEnumeration;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DataEnumeration XML Serialization")
class DataEnumerationXmlTest extends AbstractXmlSerializationTestSuite<DataEnumeration> {
    @Override
    protected Class<DataEnumeration> type() {
        return DataEnumeration.class;
    }

    @Override
    protected DataEnumeration createDefault() {
        return DataEnumeration.Standard.DECRYPT.inst();
    }

    @Override
    protected DataEnumeration createVariant() {
        return DataEnumeration.Standard.ENCRYPT.inst();
    }
}
