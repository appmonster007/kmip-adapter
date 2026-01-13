package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

public class WrappingMethodXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<WrappingMethod, String> {

    public WrappingMethodXmlDeserializer() {
        super(WrappingMethod.kmipTag, WrappingMethod.encodingType, String.class, value -> new WrappingMethod(WrappingMethod.fromName(value)));
    }
}