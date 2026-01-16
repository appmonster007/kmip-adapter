package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SplitKey;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("SplitKey Xml Serialization Tests")
class SplitKeyXmlTest extends AbstractXmlSerializationTestSuite<SplitKey> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<SplitKey> type() {
        return SplitKey.class;
    }

    @Override
    protected SplitKey createDefault() {
        return SplitKey.builder()
                .splitKeyParts(SplitKeyParts.of(1))
                .keyPartIdentifier(KeyPartIdentifier.of(1))
                .splitKeyThreshold(SplitKeyThreshold.of(1))
                .splitKeyMethod(SplitKeyMethod.Standard.XOR.inst())
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                        .build())
                .build();
    }

    @Override
    protected SplitKey createVariant() {
        return SplitKey.builder()
                .splitKeyParts(SplitKeyParts.of(2))
                .keyPartIdentifier(KeyPartIdentifier.of(2))
                .splitKeyThreshold(SplitKeyThreshold.of(2))
                .splitKeyMethod(SplitKeyMethod.Standard.POLYNOMIAL_SHARING_PRIME_FIELD.inst())
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
                        .build())
                .primeFieldSize(PrimeFieldSize.of(BigInteger.ONE))
                .build();
    }
}