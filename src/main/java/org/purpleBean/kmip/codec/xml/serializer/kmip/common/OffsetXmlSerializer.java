package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.Offset;

public class OffsetXmlSerializer extends AbstractKmipXmlSerializer<Offset, Integer> {

    public OffsetXmlSerializer() {
        super(Offset::getValue);
    }
}