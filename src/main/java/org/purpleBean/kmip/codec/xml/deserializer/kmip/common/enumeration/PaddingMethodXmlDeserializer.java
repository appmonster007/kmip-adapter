package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;

public class PaddingMethodXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PaddingMethod, String> {

    public PaddingMethodXmlDeserializer() {
        super(PaddingMethod.kmipTag, PaddingMethod.encodingType, String.class, value -> new PaddingMethod(PaddingMethod.fromName(value)));
    }
}