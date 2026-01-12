package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateXmlSerializer extends AbstractKmipXmlSerializer<ValidityDate, OffsetDateTime> {

    public ValidityDateXmlSerializer() {
        super(ValidityDate::getValue);
    }
}