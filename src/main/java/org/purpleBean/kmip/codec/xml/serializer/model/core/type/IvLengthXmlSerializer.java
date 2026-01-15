package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.IvLength;

public class IvLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<IvLength, Integer> {

    public IvLengthXmlSerializer() {
        super(IvLength::getValue);
    }
}