package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;

public class PaddingMethodXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PaddingMethod, String> {

    public PaddingMethodXmlDeserializer() {
        super(PaddingMethod.kmipTag, PaddingMethod.encodingType, String.class, value -> new PaddingMethod(PaddingMethod.fromName(value)));
    }
}