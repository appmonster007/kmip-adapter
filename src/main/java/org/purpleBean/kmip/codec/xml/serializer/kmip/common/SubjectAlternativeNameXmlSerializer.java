package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameXmlSerializer() {
        super(SubjectAlternativeName::getValue);
    }
}