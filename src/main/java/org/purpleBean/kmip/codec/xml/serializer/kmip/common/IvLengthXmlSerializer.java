package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.IvLength;

public class IvLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<IvLength, Integer> {

    public IvLengthXmlSerializer() {
        super(IvLength::getValue);
    }
}