package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.core.enumeration.DataEnumeration;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DataEnumeration Xml Serialization Tests")
class DataEnumerationXmlTest extends AbstractXmlSerializationTestSuite<DataEnumeration> {

    @Override
    public Class<DataEnumeration> type() {
        return DataEnumeration.class;
    }

    @Override
    public DataEnumeration createDefault() {
        return DataEnumeration.Standard.values()[0].inst();
    }

    @Override
    public DataEnumeration createVariant() {
        return DataEnumeration.Standard.values()[1].inst();
    }
}