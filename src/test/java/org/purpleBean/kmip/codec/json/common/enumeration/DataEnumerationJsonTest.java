package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("DataEnumeration JSON Serialization")
class DataEnumerationJsonTest extends AbstractJsonSerializationSuite<DataEnumeration> {
    @Override
    protected Class<DataEnumeration> type() {
        return DataEnumeration.class;
    }

    @Override
    protected DataEnumeration createDefault() {
        return new DataEnumeration(DataEnumeration.Standard.DECRYPT);
    }

    @Override
    protected DataEnumeration createVariant() {
        return new DataEnumeration(DataEnumeration.Standard.ENCRYPT);
    }
}
