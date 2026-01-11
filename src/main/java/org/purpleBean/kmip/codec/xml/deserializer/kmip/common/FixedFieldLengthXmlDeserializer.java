package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.FixedFieldLength;

public class FixedFieldLengthXmlDeserializer extends AbstractKmipXmlDeserializer<FixedFieldLength, Integer> {

    public FixedFieldLengthXmlDeserializer() {
        super(FixedFieldLength.kmipTag, FixedFieldLength.encodingType, Integer.class, value -> FixedFieldLength.builder().value(value).build());
    }
}