package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.MaskGenerator;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("MaskGenerator XML Serialization")
class MaskGeneratorXmlTest extends AbstractXmlSerializationTestSuite<MaskGenerator> {
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
    return MaskGenerator
        .register(0x80000000, "MaskGenExtension", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }
}
