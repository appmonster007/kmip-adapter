package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ActivationDate, OffsetDateTime> {

    public ActivationDateXmlSerializer() {
        super(ActivationDate::getValue);
    }
}