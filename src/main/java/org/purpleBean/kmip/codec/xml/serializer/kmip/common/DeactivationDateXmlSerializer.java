package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.DeactivationDate;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.time.OffsetDateTime;

public class DeactivationDateXmlSerializer extends AbstractKmipXmlSerializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateXmlSerializer() {
        super(DeactivationDate::getValue);
    }
}