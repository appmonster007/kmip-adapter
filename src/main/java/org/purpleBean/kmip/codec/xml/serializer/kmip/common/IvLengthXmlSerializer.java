package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.IvLength;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class IvLengthXmlSerializer extends AbstractKmipXmlSerializer<IvLength, Integer> {

    public IvLengthXmlSerializer() {
        super(IvLength::getValue);
    }
}