package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.MaskGenerator;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.util.Set;

@DisplayName("MaskGenerator JSON Serialization")
class MaskGeneratorJsonTest extends AbstractJsonSerializationTestSuite<MaskGenerator> {
    @Override
    protected Class<MaskGenerator> type() {
        return MaskGenerator.class;
    }

    @Override
    protected MaskGenerator createDefault() {
        return MaskGenerator.Standard.MFG1.inst();
    }

    @Override
    protected MaskGenerator createVariant() {
        return new MaskGenerator(MaskGenerator.register(0x80000000, "MaskGenExtension", Set.of(KmipSpec.UnknownVersion)));
    }
}
