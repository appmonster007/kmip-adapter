package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.MediaIdentifier;

public class MediaIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MediaIdentifier, String> {

    public MediaIdentifierTtlvSerializer() {
        super(MediaIdentifier::getValue);
    }
}