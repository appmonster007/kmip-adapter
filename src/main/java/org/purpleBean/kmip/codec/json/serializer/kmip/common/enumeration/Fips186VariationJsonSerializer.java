package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.Fips186Variation;

public class Fips186VariationJsonSerializer extends AbstractKmipJsonSerializer<Fips186Variation, String> {

    public Fips186VariationJsonSerializer() {
        super(Fips186Variation::getDescription);
    }
}