package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RngParameters Xml Serialization Tests")
class RngParametersXmlTest extends AbstractXmlSerializationTestSuite<RngParameters> {

    @Override
    public Class<RngParameters> type() {
        return RngParameters.class;
    }

    @Override
    public RngParameters createDefault() {
        return RngParameters.of(RngAlgorithm.Standard.UNSPECIFIED.inst());
    }

    @Override
    public RngParameters createVariant() {
        return RngParameters.of(RngAlgorithm.Standard.UNSPECIFIED.inst());
    }
}