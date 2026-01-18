package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateXmlSerializer() {
        super(DeactivationDate::getValue);
    }
}