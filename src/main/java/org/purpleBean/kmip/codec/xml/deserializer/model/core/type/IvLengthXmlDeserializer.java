package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.IvLength;

public class IvLengthXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<IvLength, Integer> {

    public IvLengthXmlDeserializer() {
        super(IvLength.kmipTag, IvLength.encodingType, Integer.class, value -> IvLength.builder().value(value).build());
    }
}