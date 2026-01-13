package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDXmlSerializer extends AbstractKmipDataTypeXmlSerializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDXmlSerializer() {
        super(UniqueBatchItemID::getValue);
    }
}