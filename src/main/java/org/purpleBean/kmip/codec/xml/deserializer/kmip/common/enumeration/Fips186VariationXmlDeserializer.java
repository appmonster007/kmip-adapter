package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.Fips186Variation;

public class Fips186VariationXmlDeserializer extends AbstractKmipXmlDeserializer<Fips186Variation, String> {

    public Fips186VariationXmlDeserializer() {
        super(Fips186Variation.kmipTag, Fips186Variation.encodingType, String.class, value -> new Fips186Variation(Fips186Variation.fromName(value)));
    }
}