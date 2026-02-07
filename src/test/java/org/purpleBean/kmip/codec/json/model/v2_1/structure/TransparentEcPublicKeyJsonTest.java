package org.purpleBean.kmip.codec.json.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.model.v2_1.structure.TransparentEcPublicKey;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("TransparentEcPublicKey Json Serialization Tests")
class TransparentEcPublicKeyJsonTest extends AbstractJsonSerializationTestSuite<TransparentEcPublicKey> {

    @Override
    protected Class<TransparentEcPublicKey> type() {
        return TransparentEcPublicKey.class;
    }

    @Override
    protected TransparentEcPublicKey createDefault() {
        return TransparentEcPublicKey.builder()
                .recommendedCurve(RecommendedCurve.Standard.P_192.inst())
                .qString(QString.of(new byte[]{0x01, 0x02, 0x03}))
                .build();
    }

    @Override
    protected TransparentEcPublicKey createVariant() {
        return TransparentEcPublicKey.builder()
                .recommendedCurve(RecommendedCurve.Standard.P_224.inst())
                .qString(QString.of(new byte[]{0x04, 0x05, 0x06}))
                .build();
    }
}