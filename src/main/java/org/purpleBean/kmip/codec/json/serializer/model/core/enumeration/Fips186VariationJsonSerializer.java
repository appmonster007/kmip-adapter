package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.Fips186Variation;

public class Fips186VariationJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Fips186Variation, String> {

    public Fips186VariationJsonSerializer() {
        super(Fips186Variation::getDescription);
    }
}