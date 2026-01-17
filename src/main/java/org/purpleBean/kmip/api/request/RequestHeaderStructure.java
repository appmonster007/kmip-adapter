package org.purpleBean.kmip.api.request;

import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents the header of a KMIP Request Message.
 * <p>
 * This interface defines the essential components of a KMIP request header, which
 * contains metadata about the request itself. The primary piece of information in the
 * request header is the protocol version being used for the communication.
 *
 * <p><b>Key Components:</b></p>
 * <ul>
 *   <li><b>Protocol Version:</b> Specifies the version of the KMIP protocol that the
 *       client is using to format the request. This allows the server to correctly
 *       interpret the message and respond appropriately.</li>
 * </ul>
 *
 * <p>Implementations of this interface will provide a concrete representation of the
 * KMIP request header, facilitating the serialization and deserialization of this
 * critical part of a KMIP request.</p>
 *
 * @see KmipStructure
 * @see RequestMessageStructure
 * @see ProtocolVersion
 */
public interface RequestHeaderStructure extends KmipStructure {
    /**
     * The standard KMIP tag for a Request header, which is always {@link KmipTag.Standard#REQUEST_HEADER}.
     */
    KmipTag kmipTag = KmipTag.Standard.REQUEST_HEADER.inst();

    Map<RegistryKey, Class<? extends RequestHeaderStructure>> REGISTRY = new ConcurrentHashMap<>();
    Map<RegistryKey, Function<List<KmipDataType>, ? extends RequestHeaderStructure>> BUILDER_REGISTRY = new ConcurrentHashMap<>();

    static void register(
            KmipSpec spec,
            EncodingType encodingType,
            Class<? extends RequestHeaderStructure> clazz,
            Function<List<KmipDataType>, ? extends RequestHeaderStructure> builder
    ) {
        REGISTRY.put(new RegistryKey(spec, encodingType), clazz);
        BUILDER_REGISTRY.put(new RegistryKey(spec, encodingType), builder);
    }

    static Class<? extends RequestHeaderStructure> getClassFromRegistry(KmipSpec spec, EncodingType encodingType) {
        return REGISTRY.get(new RegistryKey(spec, encodingType));
    }

    static Function<List<KmipDataType>, ? extends RequestHeaderStructure> getBuilderFromRegistry(KmipSpec spec, EncodingType encodingType) {
        return BUILDER_REGISTRY.get(new RegistryKey(spec, encodingType));
    }

    static RequestHeaderStructure of(List<KmipDataType> values) {
        KmipSpec spec = KmipContext.getSpec();
        return getBuilderFromRegistry(spec, encodingType).apply(values);
    }

    /**
     * Retrieves the {@link ProtocolVersion} from the request header.
     * <p>
     * The protocol version indicates which version of the KMIP specification the
     * request conforms to.
     *
     * @return The {@link ProtocolVersion} of the request.
     */
    ProtocolVersion getProtocolVersion();

    record RegistryKey(KmipSpec spec, EncodingType encodingType) {
    }
}
