package org.purpleBean.kmip.codec.xml.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.NistSecurityCategory;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NistSecurityCategory Xml Serialization Tests")
class NistSecurityCategoryXmlTest extends AbstractXmlSerializationTestSuite<NistSecurityCategory> {

    @Override
    public Class<NistSecurityCategory> type() {
        return NistSecurityCategory.class;
    }

    @Override
    public NistSecurityCategory createDefault() {
        return NistSecurityCategory.of(123);
    }

    @Override
    public NistSecurityCategory createVariant() {
        return NistSecurityCategory.of(456);
    }
}