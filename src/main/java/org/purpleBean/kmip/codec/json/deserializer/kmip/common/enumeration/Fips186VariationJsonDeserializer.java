package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.Fips186Variation;

public class Fips186VariationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Fips186Variation, String> {

    public Fips186VariationJsonDeserializer() {
        super(Fips186Variation.kmipTag, Fips186Variation.encodingType, String.class, value -> new Fips186Variation(Fips186Variation.fromName(value)));
    }
}