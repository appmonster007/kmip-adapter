package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateXmlSerializer extends AbstractKmipXmlSerializer<DestroyDate, OffsetDateTime> {

    public DestroyDateXmlSerializer() {
        super(DestroyDate::getValue);
    }
}