package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.MediaIdentifier;

public class MediaIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MediaIdentifier, String> {

    public MediaIdentifierXmlSerializer() {
        super(MediaIdentifier::getValue);
    }
}