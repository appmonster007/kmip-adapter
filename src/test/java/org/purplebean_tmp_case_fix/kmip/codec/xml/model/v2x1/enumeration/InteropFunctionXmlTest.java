package org.purplebean.kmip.codec.xml.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.InteropFunction;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InteropFunction XML Serialization")
class InteropFunctionXmlTest extends AbstractXmlSerializationTestSuite<InteropFunction> {
  @Override
  public Class<InteropFunction> type() {
    return InteropFunction.class;
  }

  @Override
  public InteropFunction createDefault() {
    return InteropFunction.Standard.BEGIN.inst();
  }

  @Override
  public InteropFunction createVariant() {
    return InteropFunction.Standard.END.inst();
  }
}
