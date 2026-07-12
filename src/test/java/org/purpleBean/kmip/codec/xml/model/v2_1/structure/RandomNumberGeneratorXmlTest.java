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
import org.purpleBean.kmip.model.v2_1.structure.RandomNumberGenerator;
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RandomNumberGenerator Xml Serialization Tests")
class RandomNumberGeneratorXmlTest extends AbstractXmlSerializationTestSuite<RandomNumberGenerator> {

    @Override
    public Class<RandomNumberGenerator> type() {
        return RandomNumberGenerator.class;
    }

    @Override
    public RandomNumberGenerator createDefault() {
        return RandomNumberGenerator.of(RngParameters.of(RngAlgorithm.Standard.UNSPECIFIED.inst()));
    }

    @Override
    public RandomNumberGenerator createVariant() {
        return RandomNumberGenerator.of(RngParameters.of(RngAlgorithm.Standard.UNSPECIFIED.inst()));
    }
}