package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.PutFunction;

public class PutFunctionXmlDeserializer extends AbstractKmipXmlDeserializer<PutFunction, String> {

    public PutFunctionXmlDeserializer() {
        super(PutFunction.kmipTag, PutFunction.encodingType, String.class, value -> new PutFunction(PutFunction.fromName(value)));
    }
}