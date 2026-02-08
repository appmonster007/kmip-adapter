package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.Fips186Variation;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Fips186Variation XML Serialization")
class Fips186VariationXmlTest extends AbstractXmlSerializationTestSuite<Fips186Variation> {
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
