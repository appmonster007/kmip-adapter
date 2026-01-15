package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.FixedFieldLength;

public class FixedFieldLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<FixedFieldLength, Integer> {

    public FixedFieldLengthXmlSerializer() {
        super(FixedFieldLength::getValue);
    }
}