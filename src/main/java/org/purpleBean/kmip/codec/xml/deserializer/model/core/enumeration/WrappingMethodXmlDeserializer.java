package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;

public class WrappingMethodXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<WrappingMethod, String> {

    public WrappingMethodXmlDeserializer() {
        super(WrappingMethod.kmipTag, WrappingMethod.encodingType, String.class, value -> new WrappingMethod(WrappingMethod.fromName(value)));
    }
}