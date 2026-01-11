package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

public class WrappingMethodXmlDeserializer extends AbstractKmipXmlDeserializer<WrappingMethod, String> {

    public WrappingMethodXmlDeserializer() {
        super(WrappingMethod.kmipTag, WrappingMethod.encodingType, String.class, value -> new WrappingMethod(WrappingMethod.fromName(value)));
    }
}