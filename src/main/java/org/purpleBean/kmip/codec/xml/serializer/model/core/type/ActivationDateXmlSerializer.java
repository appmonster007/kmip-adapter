package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ActivationDate, OffsetDateTime> {

    public ActivationDateXmlSerializer() {
        super(ActivationDate::getValue);
    }
}