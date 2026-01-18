package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameXmlSerializer() {
        super(SubjectAlternativeName::getValue);
    }
}