package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.TagLength;

public class TagLengthXmlSerializer extends AbstractKmipXmlSerializer<TagLength, Integer> {

    public TagLengthXmlSerializer() {
        super(TagLength::getValue);
    }
}