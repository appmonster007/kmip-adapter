package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.FixedFieldLength;

public class FixedFieldLengthXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<FixedFieldLength, Integer> {

    public FixedFieldLengthXmlDeserializer() {
        super(FixedFieldLength.kmipTag, FixedFieldLength.encodingType, Integer.class, value -> FixedFieldLength.builder().value(value).build());
    }
}