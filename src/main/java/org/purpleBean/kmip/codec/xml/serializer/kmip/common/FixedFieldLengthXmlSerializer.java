package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.FixedFieldLength;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class FixedFieldLengthXmlSerializer extends AbstractKmipXmlSerializer<FixedFieldLength, Integer> {

    public FixedFieldLengthXmlSerializer() {
        super(FixedFieldLength::getValue);
    }
}