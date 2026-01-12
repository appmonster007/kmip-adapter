package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateXmlSerializer extends AbstractKmipXmlSerializer<ActivationDate, OffsetDateTime> {

    public ActivationDateXmlSerializer() {
        super(ActivationDate::getValue);
    }
}