package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.Fips186Variation;

public class Fips186VariationTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Fips186Variation, Integer> {

    public Fips186VariationTtlvSerializer() {
        super(Fips186Variation::getValue);
    }
}