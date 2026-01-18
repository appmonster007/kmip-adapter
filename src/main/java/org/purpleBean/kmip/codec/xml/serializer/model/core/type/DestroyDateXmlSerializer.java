package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DestroyDate, OffsetDateTime> {

    public DestroyDateXmlSerializer() {
        super(DestroyDate::getValue);
    }
}