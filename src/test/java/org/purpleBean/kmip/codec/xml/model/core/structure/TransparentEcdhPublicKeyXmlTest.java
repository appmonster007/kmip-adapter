package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdhPublicKey;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentEcdhPublicKey XML Serialization Tests")
class TransparentEcdhPublicKeyXmlTest extends AbstractXmlSerializationTestSuite<TransparentEcdhPublicKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    public Class<TransparentEcdhPublicKey> type() {
        return TransparentEcdhPublicKey.class;
    }

    @Override
    public TransparentEcdhPublicKey createDefault() {
        return TransparentEcdhPublicKey.of(
                RecommendedCurve.Standard.P_192.inst(),
                QString.of("test".getBytes())
        );
    }

    @Override
    public TransparentEcdhPublicKey createVariant() {
        return TransparentEcdhPublicKey.of(
                RecommendedCurve.Standard.P_224.inst(),
                QString.of("test2".getBytes())
        );
    }
}