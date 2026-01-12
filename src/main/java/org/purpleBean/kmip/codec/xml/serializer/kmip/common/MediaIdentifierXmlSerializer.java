package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.MediaIdentifier;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class MediaIdentifierXmlSerializer extends AbstractKmipXmlSerializer<MediaIdentifier, String> {

    public MediaIdentifierXmlSerializer() {
        super(MediaIdentifier::getValue);
    }
}