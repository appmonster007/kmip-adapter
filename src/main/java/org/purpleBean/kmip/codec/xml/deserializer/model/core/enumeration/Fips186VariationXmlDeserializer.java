package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Fips186Variation;

public class Fips186VariationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Fips186Variation, String> {

    public Fips186VariationXmlDeserializer() {
        super(Fips186Variation.kmipTag, Fips186Variation.encodingType, String.class, value -> Fips186Variation.fromName(value).inst());
    }
}