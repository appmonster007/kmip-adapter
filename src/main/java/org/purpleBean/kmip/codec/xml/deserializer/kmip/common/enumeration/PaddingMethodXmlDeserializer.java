package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;

public class PaddingMethodXmlDeserializer extends AbstractKmipXmlDeserializer<PaddingMethod, String> {

    public PaddingMethodXmlDeserializer() {
        super(PaddingMethod.kmipTag, PaddingMethod.encodingType, String.class, value -> new PaddingMethod(PaddingMethod.fromName(value)));
    }
}