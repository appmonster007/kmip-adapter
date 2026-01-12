package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.Offset;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class OffsetXmlSerializer extends AbstractKmipXmlSerializer<Offset, Integer> {

    public OffsetXmlSerializer() {
        super(Offset::getValue);
    }
}