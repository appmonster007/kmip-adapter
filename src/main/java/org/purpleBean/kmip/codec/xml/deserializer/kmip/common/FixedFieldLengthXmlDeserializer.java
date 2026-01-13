package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.FixedFieldLength;

public class FixedFieldLengthXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<FixedFieldLength, Integer> {

    public FixedFieldLengthXmlDeserializer() {
        super(FixedFieldLength.kmipTag, FixedFieldLength.encodingType, Integer.class, value -> FixedFieldLength.builder().value(value).build());
    }
}