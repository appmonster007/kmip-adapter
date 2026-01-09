package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcmqvPublicKey;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentEcmqvPublicKey XML Serialization Tests")
class TransparentEcmqvPublicKeyXmlTest extends AbstractXmlSerializationSuite<TransparentEcmqvPublicKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<TransparentEcmqvPublicKey> type() {
        return TransparentEcmqvPublicKey.class;
    }

    @Override
    protected TransparentEcmqvPublicKey createDefault() {
        return TransparentEcmqvPublicKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_192),
                QString.of("test".getBytes())
        );
    }

    @Override
    protected TransparentEcmqvPublicKey createVariant() {
        return TransparentEcmqvPublicKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_224),
                QString.of("test2".getBytes())
        );
    }
}