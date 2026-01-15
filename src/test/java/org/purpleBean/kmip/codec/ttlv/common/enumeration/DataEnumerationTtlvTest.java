package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DataEnumeration TTLV Serialization")
class DataEnumerationTtlvTest extends AbstractTtlvSerializationTestSuite<DataEnumeration> {
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
