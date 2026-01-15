package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.Fips186Variation;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Fips186Variation JSON Serialization")
class Fips186VariationJsonTest extends AbstractJsonSerializationTestSuite<Fips186Variation> {
    @Override
    protected Class<Fips186Variation> type() {
        return Fips186Variation.class;
    }

    @Override
    protected Fips186Variation createDefault() {
        return Fips186Variation.Standard.UNSPECIFIED.inst();
    }

    @Override
    protected Fips186Variation createVariant() {
        return Fips186Variation.Standard.GP_X_ORIGINAL.inst();
    }
}
