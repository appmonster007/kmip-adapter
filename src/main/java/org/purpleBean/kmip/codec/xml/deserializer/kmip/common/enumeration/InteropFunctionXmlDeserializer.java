package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.InteropFunction;

public class InteropFunctionXmlDeserializer extends AbstractKmipXmlDeserializer<InteropFunction, String> {

    public InteropFunctionXmlDeserializer() {
        super(InteropFunction.kmipTag, InteropFunction.encodingType, String.class, value -> new InteropFunction(InteropFunction.fromName(value)));
    }
}