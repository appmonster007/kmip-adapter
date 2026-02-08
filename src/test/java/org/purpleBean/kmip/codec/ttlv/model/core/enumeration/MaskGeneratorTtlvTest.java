package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.MaskGenerator;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.util.Set;

@DisplayName("MaskGenerator TTLV Serialization")
class MaskGeneratorTtlvTest extends AbstractTtlvSerializationTestSuite<MaskGenerator> {
    @Override
    public Class<MaskGenerator> type() {
        return MaskGenerator.class;
    }

    @Override
    public MaskGenerator createDefault() {
        return MaskGenerator.Standard.MFG1.inst();
    }

    @Override
    public MaskGenerator createVariant() {
        return MaskGenerator.register(0x80000000, "MaskGenExtension", Set.of(KmipSpec.UnknownVersion)).inst();
    }
}
