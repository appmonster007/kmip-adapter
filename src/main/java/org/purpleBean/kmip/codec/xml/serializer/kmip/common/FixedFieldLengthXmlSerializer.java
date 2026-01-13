package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.FixedFieldLength;

public class FixedFieldLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<FixedFieldLength, Integer> {

    public FixedFieldLengthXmlSerializer() {
        super(FixedFieldLength::getValue);
    }
}