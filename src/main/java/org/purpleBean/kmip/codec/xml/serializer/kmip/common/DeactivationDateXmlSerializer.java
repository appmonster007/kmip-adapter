package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateXmlSerializer() {
        super(DeactivationDate::getValue);
    }
}