package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdsaPrivateKey;
import org.purpleBean.kmip.model.core.type.D;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentEcdsaPrivateKey XML Serialization Tests")
class TransparentEcdsaPrivateKeyXmlTest extends AbstractXmlSerializationTestSuite<TransparentEcdsaPrivateKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<TransparentEcdsaPrivateKey> type() {
        return TransparentEcdsaPrivateKey.class;
    }

    @Override
    protected TransparentEcdsaPrivateKey createDefault() {
        return TransparentEcdsaPrivateKey.of(
                RecommendedCurve.Standard.P_192.inst(),
                D.of(BigInteger.valueOf(1))
        );
    }

    @Override
    protected TransparentEcdsaPrivateKey createVariant() {
        return TransparentEcdsaPrivateKey.of(
                RecommendedCurve.Standard.P_224.inst(),
                D.of(BigInteger.valueOf(2))
        );
    }
}