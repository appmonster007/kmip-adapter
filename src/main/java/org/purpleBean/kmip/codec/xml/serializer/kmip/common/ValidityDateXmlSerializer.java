package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ValidityDate, OffsetDateTime> {

    public ValidityDateXmlSerializer() {
        super(ValidityDate::getValue);
    }
}