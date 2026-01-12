package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.MediaIdentifier;

public class MediaIdentifierXmlSerializer extends AbstractKmipXmlSerializer<MediaIdentifier, String> {

    public MediaIdentifierXmlSerializer() {
        super(MediaIdentifier::getValue);
    }
}