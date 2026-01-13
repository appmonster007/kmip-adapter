package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.PutFunction;

public class PutFunctionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PutFunction, String> {

    public PutFunctionXmlDeserializer() {
        super(PutFunction.kmipTag, PutFunction.encodingType, String.class, value -> new PutFunction(PutFunction.fromName(value)));
    }
}