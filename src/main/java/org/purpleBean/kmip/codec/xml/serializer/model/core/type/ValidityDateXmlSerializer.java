package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ValidityDate, OffsetDateTime> {

    public ValidityDateXmlSerializer() {
        super(ValidityDate::getValue);
    }
}