package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.QString;

import java.nio.ByteBuffer;

public class QStringJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<QString, ByteBuffer> {

    public QStringJsonDeserializer() {
        super(QString.kmipTag, QString.encodingType, ByteBuffer.class, value -> QString.builder().value(value).build());
    }
}