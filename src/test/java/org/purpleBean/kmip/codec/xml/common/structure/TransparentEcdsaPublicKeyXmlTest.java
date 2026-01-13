package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdsaPublicKey;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentEcdsaPublicKey XML Serialization Tests")
class TransparentEcdsaPublicKeyXmlTest extends AbstractXmlSerializationTestSuite<TransparentEcdsaPublicKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<TransparentEcdsaPublicKey> type() {
        return TransparentEcdsaPublicKey.class;
    }

    @Override
    protected TransparentEcdsaPublicKey createDefault() {
        return TransparentEcdsaPublicKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_192),
                QString.of("test".getBytes())
        );
    }

    @Override
    protected TransparentEcdsaPublicKey createVariant() {
        return TransparentEcdsaPublicKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_224),
                QString.of("test2".getBytes())
        );
    }
}