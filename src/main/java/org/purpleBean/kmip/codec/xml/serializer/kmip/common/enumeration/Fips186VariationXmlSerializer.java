package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.Fips186Variation;

public class Fips186VariationXmlSerializer extends AbstractKmipXmlSerializer<Fips186Variation, String> {

    public Fips186VariationXmlSerializer() {
        super(Fips186Variation::getDescription);
    }
}