package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.UniqueBatchItemID;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import java.nio.ByteBuffer;

public class UniqueBatchItemIDXmlSerializer extends AbstractKmipXmlSerializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDXmlSerializer() {
        super(UniqueBatchItemID::getValue);
    }
}