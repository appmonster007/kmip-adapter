package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.Fips186Variation;

public class Fips186VariationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Fips186Variation, Integer> {

    public Fips186VariationTtlvDeserializer() {
        super(Fips186Variation.kmipTag, Fips186Variation.encodingType, Integer.class, value -> new Fips186Variation(Fips186Variation.fromValue(value)));
    }
}