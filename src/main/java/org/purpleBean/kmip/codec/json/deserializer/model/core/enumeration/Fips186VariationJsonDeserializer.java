package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Fips186Variation;

public class Fips186VariationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Fips186Variation, String> {

    public Fips186VariationJsonDeserializer() {
        super(Fips186Variation.kmipTag, Fips186Variation.encodingType, String.class, value -> Fips186Variation.fromName(value).inst());
    }
}