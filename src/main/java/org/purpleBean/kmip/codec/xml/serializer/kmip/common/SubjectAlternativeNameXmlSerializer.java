package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameXmlSerializer extends AbstractKmipXmlSerializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameXmlSerializer() {
        super(SubjectAlternativeName::getValue);
    }
}