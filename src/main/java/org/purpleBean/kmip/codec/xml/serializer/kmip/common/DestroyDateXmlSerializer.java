package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DestroyDate, OffsetDateTime> {

    public DestroyDateXmlSerializer() {
        super(DestroyDate::getValue);
    }
}