package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CompromiseOccurrenceDate;

import java.time.OffsetDateTime;

public class CompromiseOccurrenceDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CompromiseOccurrenceDate, OffsetDateTime> {

    public CompromiseOccurrenceDateXmlSerializer() {
        super(CompromiseOccurrenceDate::getValue);
    }
}