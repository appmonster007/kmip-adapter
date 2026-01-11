package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.IvLength;

public class IvLengthXmlDeserializer extends AbstractKmipXmlDeserializer<IvLength, Integer> {

    public IvLengthXmlDeserializer() {
        super(IvLength.kmipTag, IvLength.encodingType, Integer.class, value -> IvLength.builder().value(value).build());
    }
}