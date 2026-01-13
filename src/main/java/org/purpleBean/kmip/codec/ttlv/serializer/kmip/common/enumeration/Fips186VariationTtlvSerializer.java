package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.Fips186Variation;

public class Fips186VariationTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Fips186Variation, Integer> {

    public Fips186VariationTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}