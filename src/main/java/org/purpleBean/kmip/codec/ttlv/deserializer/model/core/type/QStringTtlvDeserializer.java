package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.QString;

import java.nio.ByteBuffer;

public class QStringTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<QString, ByteBuffer> {

    public QStringTtlvDeserializer() {
        super(QString.kmipTag, QString.encodingType, ByteBuffer.class, value -> QString.builder().value(value).build());
    }
}