package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.MaskGenerator;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.util.Set;

@DisplayName("MaskGenerator TTLV Serialization")
class MaskGeneratorTtlvTest extends AbstractTtlvSerializationSuite<MaskGenerator> {
    @Override
    protected Class<MaskGenerator> type() {
        return MaskGenerator.class;
    }

    @Override
    protected MaskGenerator createDefault() {
        return new MaskGenerator(MaskGenerator.Standard.MFG1);
    }

    @Override
    protected MaskGenerator createVariant() {
        return new MaskGenerator(MaskGenerator.register(0x80000000, "MaskGenExtension", Set.of(KmipSpec.UnknownVersion)));
    }
}
