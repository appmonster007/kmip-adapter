package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.Offset;

public class OffsetXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Offset, Integer> {

    public OffsetXmlSerializer() {
        super(Offset::getValue);
    }
}