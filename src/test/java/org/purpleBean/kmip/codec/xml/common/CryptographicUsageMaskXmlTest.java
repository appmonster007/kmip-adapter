package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CryptographicUsageMask;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("CryptographicUsageMask XML Serialization Tests")
class CryptographicUsageMaskXmlTest extends AbstractXmlSerializationSuite<CryptographicUsageMask> {

    @Override
    protected Class<CryptographicUsageMask> type() {
        return CryptographicUsageMask.class;
    }

    @Override
    protected CryptographicUsageMask createDefault() {
        return CryptographicUsageMask.builder().value(10).build();
    }

    @Override
    protected CryptographicUsageMask createVariant() {
        return CryptographicUsageMask.builder().value(100).build();
    }
}
