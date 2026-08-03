package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.Fips186Variation;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Fips186Variation JSON Serialization")
class Fips186VariationJsonTest extends AbstractJsonSerializationTestSuite<Fips186Variation> {
  @Override
  public Class<Fips186Variation> type() {
    return Fips186Variation.class;
  }

  @Override
  public Fips186Variation createDefault() {
    return Fips186Variation.Standard.UNSPECIFIED.inst();
  }

  @Override
  public Fips186Variation createVariant() {
    return Fips186Variation.Standard.GP_X_ORIGINAL.inst();
  }
}
