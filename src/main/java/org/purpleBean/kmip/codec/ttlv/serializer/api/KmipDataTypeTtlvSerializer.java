package org.purpleBean.kmip.codec.ttlv.serializer.api;

import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;

/**
 * Base TTLV serializer for {@link KmipDataType} objects.
 * <p>
 * This abstract class extends {@link TtlvSerializer} and serves as the base for all
 * serializers that handle KMIP data types. It provides a common type hierarchy for
 * TTLV serialization of KMIP objects.
 *
 * @param <T> The specific type of {@link KmipDataType} to serialize.
 */
public abstract class KmipDataTypeTtlvSerializer<T extends KmipDataType> extends TtlvSerializer<T> {
}
